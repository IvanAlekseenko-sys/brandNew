pipeline {
  agent any

  options {
    timestamps()
  }

  environment {
    ALLURE_TOOL = 'allure-2.35.1'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Test (headless)') {
      steps {
        sh '''
          set -eux
          chmod +x gradlew
          ./gradlew clean test --no-daemon --info --stacktrace -Dheadless=true
        '''
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
          archiveArtifacts allowEmptyArchive: true, artifacts: 'build/reports/tests/test/**'
          archiveArtifacts allowEmptyArchive: true, artifacts: 'allure-results/**'
        }
      }
    }

    stage('Allure Report') {
      steps {
        allure([
          results: [[path: 'allure-results']],
          reportBuildPolicy: 'ALWAYS'
        ])
      }
    }
  }

  post {
    always { echo 'Done.' }
  }
}
