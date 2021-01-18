pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "./gradlew assemble"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing....'
//                sh './gradlew test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                sh 'java -jar /var/lib/jenkins/workspace/service-app_master/build/libs/service-app-1.0-SNAPSHOT.jar'
            }
        }
    }
}
