pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', credentialsId: 'github-creds', url: 'https://github.com/TAHAB-001/github-actions.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
    }
}
