pipeline {
    agent any

    tools {
        jdk 'jdk17'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/panditkamal/java-springboot-boilerplate.git'
            }
        }

        stage('Build') {
            steps {
                echo "Building Spring Boot project using Gradle..."
                sh './gradlew clean build -x test'
            }
        }

        stage('Test') {
            steps {
                echo "Running tests..."
                sh './gradlew test'
            }
        }

        stage('Package') {
            steps {
                echo "Packaging JAR file..."
                sh './gradlew bootJar'
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying Spring Boot JAR..."
                sh 'docker build -t na-backend .'
                sh 'docker run -d -p 8081:8080 na-backend'
            }
        }
    }

    post {
        success {
            echo "✅ Build succeeded!"
        }
        failure {
            echo "❌ Build failed!"
        }
    }
}
