# Spring Boot Backend CI with GitHub Actions

This repository contains a **Spring Boot backend application** and a **GitHub Actions workflow** that automates building, containerizing, and running the application using **Maven** and **Docker**.

## 🚀 Features
- **Spring Boot** backend application (Java 21)
- **GitHub Actions CI pipeline**:
  - Builds project with Maven
  - Caches dependencies to speed up builds
  - Creates & pushes Docker images to **Docker Hub**

---

## 🛠️ Tech Stack
- **Java 21**
- **Spring Boot**
- **Maven**
- **Docker**
- **GitHub Actions**

---

## 📂 Repository Structure
.
├── src/ # Application source code
├── Dockerfile # Docker build configuration
├── pom.xml # Maven configuration
└── .github/workflows/ # GitHub Actions workflow

🔑 Secrets Required
Make sure to configure these secrets in your GitHub repository settings:

DOCKER_USERNAME → Your Docker Hub username
DOCKER_PASSWORD → Your Docker Hub password or access token



---

## ⚙️ GitHub Actions Workflow

The workflow (`.github/workflows/cicd-github-actions.yml`) triggers on every **push** or **pull request** to the `master` branch.

**Pipeline Steps:**
1. **Checkout code**  
   Uses `actions/checkout` to fetch repository code.
2. **Set up JDK 21**  
   Configures Java environment using `actions/setup-java`.
3. **Build with Maven**  
   Runs `mvn clean package -DskipTests`.
4. **Build & push Docker image**  
   Builds image and pushes to Docker Hub.

---

## 🔧 How to Run Locally
```
# Clone repo
git clone https://github.com/TAHAB-001/github-actions.git
cd github-actions

# Build application
mvn clean package -DskipTests

# Build & run Docker container
docker build -t springboot-app .
docker run -p 9898:9898 springboot-app

Access the app at:
http://localhost:9898/get-data



------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

### deploy your Dockerized Spring Boot app on AWS Ubuntu easily.###

# Deploy Dockerized Spring Boot App on AWS Ubuntu

This guide explains how to deploy a Dockerized Spring Boot application on an **AWS Ubuntu EC2 instance** using **Git clone** and **Docker Hub**.

---

## Prerequisites
- AWS account
- Dockerized Spring Boot image pushed to Docker Hub
- GitHub repository containing project code (optional for deployment, required if cloning source)
- SSH access to EC2 instance
- `.pem` key for your EC2

---

## 1. Launch EC2 Instance
1. Go to AWS EC2 console → **Launch Instance**.
2. Select **Ubuntu 22.04 LTS**.
3. Choose instance type (e.g., `t2.micro` for testing).
4. Configure **Security Group**:
   - Allow **22** (SSH)
   - Allow **8080** (Spring Boot default port)
5. Launch and download your `.pem` key.

---

## 2. Connect to EC2
```bash
ssh -i your-key.pem ubuntu@<EC2_PUBLIC_IP>

3. One-Command Deploy 🚀
Run the following command on your EC2 instance

This script will:

Install Docker & Git

Clone your GitHub repo

Pull your Docker image from Docker Hub

Run your Spring Boot container




Install Docker

sudo apt update
sudo apt install -y docker.io git
sudo systemctl enable docker
sudo systemctl start docker
sudo usermod -aG docker ubuntu
Note: Log out and log back in for group changes to take effect.



Clone Git Repository

git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>
Pull Docker Image from Docker Hub

docker pull <your-dockerhub-username>/<your-image>:latest
Check:


docker images
Run Docker Container

docker run -d --name springboot-app -p 80:9898 <your-dockerhub-username>/<your-image>:latest

5. Test Application
Get EC2 public IP:


curl http://<EC2_PUBLIC_IP>:9898
Or open in browser:


http://<EC2_PUBLIC_IP>:9898
6. Stop & Remove Container

docker stop springboot-app
docker rm springboot-app
