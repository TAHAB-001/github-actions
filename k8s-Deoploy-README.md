# 🌐 Spring Boot + MySQL on Kubernetes with Jenkins CI/CD
**🚀 Automated Deployment using Minikube on AWS EC2 (Ubuntu Server)**

---

## 📖 Overview
This project demonstrates a **full DevOps pipeline**:
- **Spring Boot Application** deployed in **Kubernetes (Minikube)**
- **MySQL Database** with **Persistent Storage**
- **Jenkins CI/CD pipeline** for automated build, Docker image push, and Kubernetes deployment
- Running inside an **AWS EC2 Ubuntu Server**

---

## 🛠️ Tech Stack
- ☸️ **Kubernetes (Minikube on EC2)**
- 🐳 **Docker & Docker Hub**
- ⚙️ **Jenkins Pipeline**
- 🏗️ **Maven** (build automation)
- 🗄️ **MySQL 8.0**
- ☕ **Spring Boot (Java)**

---

## 📂 Repository Contents
| File | Description |
|------|-------------|
| `springboot-mysql.yaml` | Kubernetes manifest (Spring Boot + MySQL + PVC + Services) |
| `Jenkinsfile` | Jenkins CI/CD pipeline definition |
| `src/` | Spring Boot source code (from your repo) |

---

## ⚡ Architecture Flow
```mermaid
graph TD
    A[👨‍💻 Developer Push Code to GitHub] --> B[🔄 Jenkins Poll SCM]
    B --> C[⚙️ Build with Maven]
    C --> D[🐳 Build Docker Image]
    D --> E[☁️ Push to Docker Hub]
    E --> F[☸️ Deploy to Minikube on EC2]
    F --> G[🌍 Expose Spring Boot App on NodePort 30080]
    F --> H[🗄️ MySQL Deployment + PVC]


🚀 Setup Instructions

1️⃣ EC2 Server Setup
Launch Ubuntu EC2 instance (t2.medium or higher).
Open security group ports:
22 → SSH
8080 → Jenkins
30080 → Spring Boot app

Install dependencies:

sudo apt-get update -y
sudo apt-get install -y docker.io openjdk-17-jdk

2️⃣ Minikube & kubectl
# Install Minikube
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube

# Install kubectl
sudo snap install kubectl --classic

# Start Minikube
minikube start --driver=docker
kubectl get nodes

⚙️ Jenkins Setup

Install Jenkins:

sudo apt-get install -y jenkins
sudo systemctl enable jenkins
sudo systemctl start jenkins

Install Jenkins Plugins:

✅ Pipeline
✅ Docker Pipeline
✅ Kubernetes CLI

Add Jenkins Credentials:
🔑 github-creds → GitHub access
🔑 dockerhub-creds → Docker Hub user & password
🔑 kubeconfig-cred → Upload your ~/.kube/config as Secret File

🐳 Docker Hub

Application image:

tahabohra001/springboot-demo:latest

Jenkins builds & pushes automatically on each commit.

📜 Kubernetes Deployment

Apply manifests manually (optional):
kubectl apply -f springboot-mysql.yaml

Created resources:

🌱 Spring Boot Deployment (3 replicas)
🌱 Spring Boot Service (NodePort: 300800
🌱 MySQL Deployment (1 replica + PVC)
🌱 MySQL Service (ClusterIP: 3306)

Check status:

kubectl get pods
kubectl get svc

🌍 Accessing the Application

Get your EC2 Public IP:
curl ifconfig.me


Example: 3.110.xxx.xxx

Open Spring Boot app in browser:
http://<EC2_PUBLIC_IP>:30080

🔄 Jenkins CI/CD Pipeline Flow

Checkout Code → Pulls source from GitHub
Maven Build → Packages Spring Boot app
Docker Build & Push → Pushes image to Docker Hub
Kubernetes Deploy → Applies springboot-mysql.yaml & updates image
Rollout Status → Ensures deployment is healthy

✅ Each code commit triggers automatic redeployment!

🔍 Debugging & Logs

Spring Boot logs:
kubectl logs -f deployment/springboot-demo

MySQL logs:
kubectl logs -f deployment/mysql

Describe services:
kubectl describe svc springboot-service

🛑 Cleanup
kubectl delete -f springboot-mysql.yaml
minikube stop

🚧 Improvements & Next Steps
🔐 Use Kubernetes Secrets for DB credentials.
🌐 Setup Ingress + LoadBalancer for production-ready access.
📊 Add Prometheus + Grafana monitoring.
🧩 Migrate to Helm charts for reusability.
☁️ Upgrade to EKS (AWS Kubernetes Service) for managed infra.

👨‍💻 Author: Taha Bohra
🐳 Docker Hub: tahabohra001

☸️ Kubernetes Enthusiast | DevOps Engineer


-------------------------------------------------------