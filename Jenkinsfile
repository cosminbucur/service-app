pipeline {
    agent any

    stages {
        stage ('Build') {
            sh "./gradlew assemble"
        }
        stage ('Test') {
            sh './gradlew test'
        }
        stage ('Deploy') {

        }
    }
}
