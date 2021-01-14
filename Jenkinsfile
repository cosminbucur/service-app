pipeline {
    agent any

    stages {
        stage ('Build') {
            sh "gradlew clean build -x test"
        }
        stage ('Test') {
            sh 'gradlew test'
        }
        stage ('Deploy') {

        }
    }
}
