# LDAP Final Project — CKU Restaurant

---

## STEP 1 — Docker Compose

### What
Create LDAP server + phpLDAPadmin UI

### Why
- **LDAP server** = database
- **phpLDAPadmin** = visual interface

### Docker Compose Configuration

```yaml
services:
  cku-openldap:
    image: osixia/openldap:1.5.0
    container_name: cku-openldap
    restart: unless-stopped
    environment:
      LDAP_ORGANISATION: "CKU Restaurant"
      LDAP_DOMAIN: "cku.local"
      LDAP_BASE_DN: "dc=cku,dc=local"
      LDAP_ADMIN_PASSWORD: "admin123"
      LDAP_CONFIG_PASSWORD: "config123"
      LDAP_READONLY_USER: "true"
      LDAP_READONLY_USER_USERNAME: "readonly"
      LDAP_READONLY_USER_PASSWORD: "readonly123"
      LDAP_TLS: "false"
      LDAP_REPLICATION: "false"
      KEEP_EXISTING_CONFIG: "false"
      LDAP_REMOVE_CONFIG_AFTER_SETUP: "true"
    volumes:
      - ./ldap_data:/var/lib/ldap
      - ./ldap_config:/etc/ldap/slapd.d
    ports:
      - "389:389"
      - "636:636"
    networks:
      - cku_net

  cku-phpldapadmin:
    image: osixia/phpldapadmin:0.9.0
    container_name: cku-phpldapadmin
    restart: unless-stopped
    environment:
      PHPLDAPADMIN_LDAP_HOSTS: "cku-openldap"
      PHPLDAPADMIN_HTTPS: "false"
    ports:
      - "8080:80"
    depends_on:
      - cku-openldap
    networks:
      - cku_net

networks:
  cku_net:
    driver: bridge
```

---

## STEP 2 — Start Containers

### What
Start the LDAP server

### Why
LDAP must be running before adding users

### How

```bash
docker compose up -d
```

Check running containers:

```bash
docker ps
```

---

## STEP 3 — Access phpLDAPadmin

Open your browser at:

```
http://localhost:8080
```

Login credentials:

```
Login DN:  cn=admin,dc=cku,dc=local
Password:  admin123
```

---

## STEP 4 — Create LDIF File

### What
An LDIF file defines the directory structure and users.

### Why
Bulk creation is faster and cleaner than using the UI.

### How

```bash
nano cku.ldif
```

> **Note:** All users' password is: `Aqswdef1234`

### The Starting Point: `cku.ldif`
The file [cku.ldif](cku.ldif) served as our initial template for the LDAP structure. While the current state of our directory may have evolved through manual changes and updates, this file represents our starting point and the foundation upon which the system was built.

### Password Encryption
To ensure security, passwords in the LDIF were encrypted using the following command based on Moodle theory:

```bash
echo -n "{MD5}$(echo -n 'password' | openssl md5 -binary | base64)" | base64
```

This command generates a base64-encoded string of the MD5 hash of the password, prefixed with `{MD5}`, which is then base64-encoded again for use in the LDIF file.

---

## STEP 5 — CKU LDAP Structure

Your project directory should look like this:

```
isard@ubuntu:~/Desktop/LDAP$  tree
.
├── cku.ldif
├── docker-compose.yml
├── ldap_config
│   ├── cn=config  [error opening dir]
│   ├── cn=config.ldif
│   └── docker-openldap-was-admin-password-set
└── ldap_data
    ├── data.mdb
    └── lock.mdb
```

---

## STEP 6 — Copy LDIF Into Container

```bash
docker cp cku.ldif cku-openldap:/cku.ldif
```

---

## STEP 7 — Enter Container

```bash
docker exec -it cku-openldap bash
```

---

## STEP 8 — Import LDAP Data

```bash
ldapadd -x -D "cn=admin,dc=cku,dc=local" -w admin123 -f /cku.ldif
```

Expected output:

```
adding new entry...
adding new entry...
adding new entry...
```

> No errors = success 🎯

---

## STEP 9 — Verify

Go back to your browser:

```
http://localhost:8080
http://32.193.176.159:8080
```

Click **refresh**. You should see the following directory tree:

```
dc=cku, dc=local (4)
├── cn=readonly
├── ou=Customers (1)
├── ou=Managers (1)
└── ou=Waiters (1)
```

After full population, the tree should look like:

```
dc=cku, dc=local (4)
├── cn=readonly
├── ou=Clients (2)
│   ├── ou=Groups (1)
│   │   └── cn=Clients
│   └── ou=Users (9)
│       ├── cn=client01
│       ├── cn=client02
│       ├── cn=client03
│       ├── cn=client04
│       ├── cn=client05
│       ├── cn=client06
│       ├── cn=client07
│       ├── cn=client08
│       └── cn=client09
├── ou=Managers (2)
│   ├── ou=Groups (1)
│   │   └── cn=Managers
│   └── ou=Users (2)
└── ou=Waiters (2)
    ├── ou=Groups (1)
    └── ou=Users (5)
```

---

## STEP 10 — Delete Isolated `ou` (Cleanup)

---

## Optional — Delete LDAP Content

Remove all LDAP data:

```bash
ldapdelete -x -D "cn=admin,dc=cku,dc=local" -w admin123 -r "dc=cku,dc=local"
```

Stop and remove all Docker containers:

```bash
docker stop $(docker ps -aq) && docker rm $(docker ps -aq)
```

Delete the project directory:

```bash
sudo rm -rf LDAP
```

