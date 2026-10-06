#!/bin/bash
set -e  # This makes the script stop if any command fails

echo "1️⃣ Updating the system..."
sudo apt update && sudo apt upgrade -y

echo "2️⃣ Installing required dependencies..."
sudo apt install ca-certificates curl gnupg lsb-release -y

echo "3️⃣ Adding Docker's official GPG key..."
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | \
sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg

echo "4️⃣ Adding the official Docker repository..."
echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | \
sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

echo "5️⃣ Installing Docker..."
sudo apt update
sudo apt install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y

echo "6️⃣ Testing the installation..."
sudo docker run hello-world

echo "✅ Docker installed and tested successfully"

echo "7️⃣ Adding the current user to the Docker group..."
sudo usermod -aG docker $USER