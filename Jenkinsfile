pipeline {
    agent any

    environment {
        APP_NAME        = "springboot-demo"
        DOCKER_IMAGE    = "springboot-demo:latest"
        DOCKER_HUB_REPO = "tahabohra001/springboot-demo"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', credentialsId: 'github-creds', url: 'https://github.com/TAHAB-001/github-actions.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh 'docker tag $DOCKER_IMAGE $DOCKER_HUB_REPO'
                    sh 'docker push $DOCKER_HUB_REPO'
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    dir("${WORKSPACE}") {
                        sh '''
                            echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                            docker rmi -f tahabohra001/springboot-demo:latest || true
                            docker-compose down || true
                            docker-compose pull --ignore-pull-failures
                            docker-compose up -d --force-recreate --remove-orphans
                        '''
                    }
                }
            }
        }
    }
}
