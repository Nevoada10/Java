# CKU Restaurant Management System
## NFS + PostgreSQL Infrastructure Setup
### Documentation — May 2026

---

## Architecture

```
Personal laptop (Java application)
    │
    ├── 172.31.34.145  — AWS Machine 1
    │       ├── OpenLDAP Docker container    (port 389)
    │       ├── phpLDAPadmin Docker container (port 8080)
    │       └── PostgreSQL 16 Docker container (port 5432)
    │                 │
    │                 └── database files stored remotely on ↓
    │
    └── 172.31.39.149  — AWS Machine 2
            └── NFS Server
                    └── /srv/nfs_compartido/postgres_data
```

**What this means:** PostgreSQL runs on Machine 1 but its data files physically live on Machine 2 (the NFS server). If Machine 1 is destroyed, the data survives on Machine 2.

---

## Part 1 — NFS Server Setup (172.31.39.149)

### Step 1 — Install NFS server

```bash
sudo apt update
sudo apt install -y nfs-kernel-server
```

### Step 2 — Create the shared folder

```bash
sudo mkdir -p /srv/nfs_compartido/postgres_data
sudo chown -R 999:999 /srv/nfs_compartido/postgres_data
sudo chmod 755 /srv/nfs_compartido/postgres_data
```

> UID 999 is the user PostgreSQL runs as inside its Docker container.

### Step 3 — Configure the export

```bash
sudo tee /etc/exports <<EOF
/srv/nfs_compartido 172.31.34.145(rw,sync,no_subtree_check,no_root_squash)
EOF
```

### Step 4 — Apply and enable

```bash
sudo exportfs -a
sudo systemctl restart nfs-kernel-server
sudo systemctl enable nfs-kernel-server
```

### Step 5 — Verify ✓

```bash
showmount -e localhost
```

**Expected output:**
```
Export list for localhost:
/srv/nfs_compartido 172.31.34.145
```

---

## Part 2 — AWS Security Group Configuration

Open inbound rules on the NFS machine security group:

| Type | Protocol | Port | Source |
|---|---|---|---|
| NFS | TCP | 2049 | 0.0.0.0/0 |
| Custom TCP | TCP | 111 | 0.0.0.0/0 |

> Port 2049 is the NFS data port. Port 111 is the portmapper used for connection negotiation.

---

## Part 3 — NFS Client Setup (172.31.34.145)

### Step 6 — Install NFS client

```bash
sudo apt update
sudo apt install -y nfs-common
```

### Step 7 — Create mount point

```bash
sudo mkdir -p /mnt/nfs/postgres_data
```

### Step 8 — Mount the NFS folder

```bash
sudo mount -t nfs4 172.31.39.149:/srv/nfs_compartido/postgres_data /mnt/nfs/postgres_data
```

### Step 9 — Make mount permanent

```bash
echo "172.31.39.149:/srv/nfs_compartido/postgres_data /mnt/nfs/postgres_data nfs4 rw,sync,hard,intr 0 0" | sudo tee -a /etc/fstab
```

### Step 10 — Verify mount ✓

```bash
df -h | grep nfs
```

**Expected output:**
```
172.31.39.149:/srv/nfs_compartido/postgres_data  ...  /mnt/nfs/postgres_data
```

---

## Part 4 — PostgreSQL Docker with NFS Volume

### docker-compose.yml

```yaml
services:
  postgres:
    image: postgres:16
    container_name: postgres_server
    environment:
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: 123456
      POSTGRES_DB: template1
    ports:
      - "5432:5432"
    working_dir: /workspace/sql
    volumes:
      - pgdata:/var/lib/postgresql/data
      - ./sql:/workspace/sql

volumes:
  pgdata:
    driver_opts:
      type: "nfs"
      o: "addr=172.31.39.149,rw,nfsvers=4,noatime"
      device: ":/srv/nfs_compartido/postgres_data"
```

> The `volumes` section points Docker to store PostgreSQL data files on the NFS server instead of locally.

### Start the container

```bash
docker compose up postgres -d
docker logs postgres_server
```

**Expected output (last lines):**
```
PostgreSQL init process complete; ready for start up.
database system is ready to accept connections
```

---

## Part 5 — Database Creation

Connect to PostgreSQL and create the project database:

```bash
docker exec -it postgres_server psql -U admin -d template1
```

```sql
CREATE DATABASE fpdatabase OWNER admin;
\c fpdatabase

CREATE TABLE dishes ( ... );
CREATE TABLE reservations ( ... );
CREATE TABLE orders ( ... );
CREATE TABLE order_items ( ... );

INSERT 0 4   -- dishes
INSERT 0 12  -- reservations
INSERT 0 10  -- orders
INSERT 0 5   -- order items
INSERT 0 3
INSERT 0 8
```

---

## Part 6 — Verification

### Verify PostgreSQL is running ✓

```bash
docker logs postgres_server
```

**Output:**
```
database system is ready to accept connections
```

### Verify data is stored on NFS ✓

On the NFS machine (172.31.39.149):

```bash
sudo ls -la /srv/nfs_compartido/postgres_data/base
```

**Output:**
```
total 32
drwx------  6 999 systemd-journal  4096 May 13 09:36 .
drwx------ 19 999 systemd-journal  4096 May 13 09:26 ..
drwx------  2 999 systemd-journal  4096 May 13 09:27 1
drwx------  2 999 systemd-journal 12288 May 13 09:36 16386
drwx------  2 999 systemd-journal  4096 May 13 09:26 4
drwx------  2 999 systemd-journal  4096 May 13 09:27 5
```

**Added a second test database**

**Output:**
```
drwx------  7 999 systemd-journal  4096 May 13 10:06 .
drwx------ 19 999 systemd-journal  4096 May 13 09:26 ..
drwx------  2 999 systemd-journal  4096 May 13 10:02 1
drwx------  2 999 systemd-journal 12288 May 13 09:36 16386 (database 1)
drwx------  2 999 systemd-journal 12288 May 13 10:06 16456 (database 2, test)
drwx------  2 999 systemd-journal  4096 May 13 09:26 4
drwx------  2 999 systemd-journal  4096 May 13 09:27 5
```

> Observation: The timezone of the NFS machine is 2 hours before the timezone of the host machine(Spain, Barcelona).

> Folder `16386` is `fpdatabase` — the large size confirms it contains the project tables and data.
> Folder `163456` is `gabinetdatabase` — which is a test database we used on exercises.

---

## Summary

| Component | Machine | Status |
|---|---|---|
| OpenLDAP | 172.31.34.145 | ✓ Running |
| phpLDAPadmin | 172.31.34.145 | ✓ Running |
| PostgreSQL 16 | 172.31.34.145 | ✓ Running |
| NFS Server | 172.31.39.149 | ✓ Running |
| DB data on NFS | 172.31.39.149 | ✓ Verified |
| fpdatabase | PostgreSQL | ✓ Created with tables and data |

---

*CKU Project — Carles Conesa, Kadiatou Diallo, Uriel Neves Silva*
*Escola del Treball — DAM 2025/2026*
