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
