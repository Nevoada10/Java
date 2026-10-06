# WireGuard Easy (wg-easy) — Installation & Configuration Guide
**Target:** AWS EC2 · Ubuntu 22.04 LTS · Docker · Direct port exposure

---

## Table of Contents

1. [Prerequisites](#1-prerequisites)
2. [AWS Security Groups](#2-aws-security-groups)
3. [Install Docker & Docker Compose](#3-install-docker--docker-compose)
4. [Deploy wg-easy](#4-deploy-wg-easy)
5. [Verify the Container](#5-verify-the-container)
6. [Access the Web UI](#6-access-the-web-ui)
7. [Connect from Your Phone](#7-connect-from-your-phone)
8. [Useful Management Commands](#8-useful-management-commands)
9. [Troubleshooting](#9-troubleshooting)

---

## 1. Prerequisites

Before starting, make sure you have:

- An **AWS EC2 instance** running **Ubuntu 22.04 LTS** (already launched).
- A **key pair** (`.pem` file) to SSH into it.
- Your instance's **public IPv4 address** (visible in the EC2 console).
- Your EC2 instance's **Security Group ID** (you'll edit it in Step 2).

> **Note:** wg-easy requires the Linux kernel module `wireguard`. Ubuntu 22.04 ships it by default — no extra installation needed.

---

## 2. AWS Security Groups

You need to open **three ports** in your EC2 instance's Security Group.

### 2.1 Open the Security Group in the AWS Console

1. Go to **EC2 → Instances** and click your instance.
2. In the **Security** tab, click the Security Group link.
3. Click **Edit inbound rules → Add rule** for each of the following:

### 2.2 Required Inbound Rules

| Type | Protocol | Port | Source | Purpose |
|---|---|---|---|---|
| Custom UDP | UDP | **51820** | `0.0.0.0/0` | WireGuard VPN tunnel |
| Custom TCP | TCP | **51821** | `My IP` (recommended) | wg-easy Web UI |
| SSH | TCP | **22** | `My IP` | SSH access (already exists) |

> **Security tip:** Restrict port `51821` (the web UI) to your IP only (`My IP`). Anyone who reaches it can manage your VPN. Port `51820` must be open to all (`0.0.0.0/0`) so clients can connect.

4. Click **Save rules**.

---

## 3. Install Docker & Docker Compose

SSH into your EC2 instance first:

```bash
ssh -i /path/to/your-key.pem ubuntu@<YOUR_EC2_PUBLIC_IP>
```

### 3.1 Update the system

```bash
sudo apt update && sudo apt upgrade -y
```

### 3.2 Install Docker

```bash
# Install dependencies
sudo apt install -y ca-certificates curl gnupg lsb-release

# Add Docker's official GPG key
sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | \
  sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg

# Add Docker repository
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] \
  https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

# Install Docker Engine
sudo apt update
sudo apt install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

### 3.3 Add your user to the Docker group (avoid using sudo every time)

```bash
sudo usermod -aG docker ubuntu
newgrp docker
```

### 3.4 Verify Docker works

```bash
docker --version
docker run hello-world
```

Expected output includes: `Hello from Docker!`

---

## 4. Deploy wg-easy

### 4.1 Create the working directory

```bash
mkdir -p ~/wg-easy && cd ~/wg-easy
```

### 4.2 Generate a password hash

wg-easy requires a **bcrypt-hashed** password for the web UI. Generate one with:

```bash
docker run --rm ghcr.io/wg-easy/wg-easy wgpw 'YourPasswordHere'
```

> Replace `YourPasswordHere` with your actual password. The command outputs something like:
> `$2b$12$...` — copy the entire hash, you'll need it next.

**Important:** If your hash contains `$` characters (it will), you must escape them as `$$` inside the `docker-compose.yml` environment block.
```bash
> ~/wg-easy$ docker run --rm ghcr.io/wg-easy/wg-easy wgpw 'admin123'
PASSWORD_HASH='$2a$12$F5VbuLa4oACp5a2s7otl5ONE2epRdIfOWMKnjn1dh5F5D.OleauQ6'

- PASSWORD_HASH=$$2a$$12$$F5VbuLa4oACp5a2s7otl5ONE2epRdIfOWMKnjn1dh5F5D.OleauQ6
```

Example: `$2b$12$abc` → `$$2b$$12$$abc`

### 4.3 Create the Docker Compose file

```bash
nano docker-compose.yml
```

Paste the following, replacing the placeholder values:

```yaml
volumes:
  etc_wireguard:

services:
  wg-easy:
    environment:
      # ===========================
      # REQUIRED — change these:
      # ===========================

      # Your EC2 public IP or domain name
      - WG_HOST=<YOUR_EC2_PUBLIC_IP>

      # Bcrypt hash of your password (escape $ as $$)
      - PASSWORD_HASH=$$2b$$12$$YourHashHere

      # ===========================
      # Optional but recommended:
      # ===========================

      # WireGuard tunnel port (must match Security Group)
      - WG_PORT=51820

      # Web UI port
      - PORT=51821

      # DNS server pushed to VPN clients
      - WG_DEFAULT_DNS=1.1.1.1

      # IP range for VPN clients
      - WG_DEFAULT_ADDRESS=10.8.0.x

    image: ghcr.io/wg-easy/wg-easy
    container_name: wg-easy
    volumes:
      - etc_wireguard:/etc/wireguard
    ports:
      - "51820:51820/udp"
      - "51821:51821/tcp"
    restart: unless-stopped
    cap_add:
      - NET_ADMIN
      - SYS_MODULE
    sysctls:
      - net.ipv4.ip_forward=1
      - net.ipv4.conf.all.src_valid_mark=1
```

Final docker-compose.yml example with placeholders replaced:
```yaml
volumes:
  etc_wireguard:

services:
  wg-easy:
    environment:
      - WG_HOST=32.193.176.159
      - PASSWORD_HASH=$$2a$$12$$zgRidEVczEs1R1jd26/Deufp1tb4kPqSWtQ9L/cSHkUcosaXCEDUW
      - WG_PORT=51820
      - PORT=51821
      - WG_DEFAULT_DNS=1.1.1.1
      - WG_DEFAULT_ADDRESS=10.8.0.x

    image: ghcr.io/wg-easy/wg-easy
    container_name: wg-easy
    volumes:
      - etc_wireguard:/etc/wireguard
    ports:
      - "51820:51820/udp"
      - "51821:51821/tcp"
    restart: unless-stopped
    cap_add:
      - NET_ADMIN
      - SYS_MODULE
    sysctls:
      - net.ipv4.ip_forward=1
      - net.ipv4.conf.all.src_valid_mark=1
```

Save and exit: `Ctrl+O` → `Enter` → `Ctrl+X`

### 4.4 Start the container

```bash
docker compose up -d
```

---

## 5. Verify the Container

### Check it's running

```bash
docker ps
```

You should see `wg-easy` with status `Up`.

### Check the logs (live)

```bash
docker compose logs -f
```

Look for lines like:
```
WireGuard started
Web UI started on port 51821
```

Press `Ctrl+C` to exit log view.

### Check WireGuard is listening

```bash
sudo wg show
```

If WireGuard is active, you'll see interface info. If the command returns nothing yet, it's normal until the first client connects.

---

## 6. Access the Web UI

Open a browser and navigate to:

```
http://<YOUR_EC2_PUBLIC_IP>:51821
```

You'll be prompted for the **password** you set in Step 4.2 (the plain text one, not the hash).

From the web UI you can:
- Create new VPN clients (e.g., `phone`, `laptop`)
- Download client config files or scan QR codes
- Enable/disable or delete clients

---

## 7. Connect from Your Phone

### 7.1 Install the WireGuard app

- **Android:** [WireGuard on Google Play](https://play.google.com/store/apps/details?id=com.wireguard.android)
- **iOS:** [WireGuard on App Store](https://apps.apple.com/app/wireguard/id1441195209)

### 7.2 Create a client in the Web UI

1. In the wg-easy web UI, click **+ New client**.
2. Enter a name (e.g., `phone`) and click **Create**.
3. A new client card appears with a **QR code** icon.

### 7.3 Scan the QR code

1. Open the WireGuard app on your phone.
2. Tap the **+** button → **Scan QR code**.
3. Point the camera at the QR code on screen.
4. Give the tunnel a name (e.g., `Home VPN`) and tap **Add tunnel**.

### 7.4 Activate the tunnel

Toggle the tunnel on in the app. You should see your phone's traffic routing through the VPN within a few seconds.

> **Verify it works:** With the tunnel active, visit [https://whatismyip.com](https://whatismyip.com) from your phone — it should show your EC2's public IP.

---

## 8. Useful Management Commands

### Stop wg-easy

```bash
cd ~/wg-easy
docker compose down
```

### Start wg-easy

```bash
docker compose up -d
```

### Restart wg-easy

```bash
docker compose restart
```

### Update wg-easy to latest version

```bash
docker compose pull
docker compose up -d
```

### View real-time logs

```bash
docker compose logs -f wg-easy
```

### Check connected peers (active tunnels)

```bash
docker exec -it wg-easy wg show
```

---

## 9. Troubleshooting

### Web UI unreachable

- Double-check the Security Group has TCP port `51821` open to your IP.
- Verify the container is running: `docker ps`
- Check logs for errors: `docker compose logs`

### Phone can't connect / no traffic through VPN

- Verify UDP port `51820` is open to `0.0.0.0/0` in the Security Group.
- Make sure `WG_HOST` in `docker-compose.yml` is set to the correct public IP.
- Run `sudo wg show` inside the EC2 instance to see if the peer appears after connecting.

### Password hash not working

- Ensure you escaped every `$` as `$$` in the `docker-compose.yml` file.
- Regenerate the hash and try again: `docker run --rm ghcr.io/wg-easy/wg-easy wgpw 'NewPassword'`

### Container exits immediately

- Check logs: `docker compose logs wg-easy`
- Make sure `NET_ADMIN` and `SYS_MODULE` capabilities are not blocked by your instance profile.
- Ubuntu 22.04 supports WireGuard natively — no additional kernel modules should be needed.

---

*Guide written for wg-easy · AWS EC2 · Ubuntu 22.04 LTS · Docker Compose V2*