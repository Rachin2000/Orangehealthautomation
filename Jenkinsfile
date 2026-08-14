pipeline {
    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven-3.9.14'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }
}