pipeline {
  agent any
  options { timestamps(); ansiColor('xterm') }
  environment { ALLURE_TOOL = 'allure-2.30.0' }

  stages {
    stage('Checkout') {
      steps {
        checkout([$class: 'GitSCM',
          branches: [[name: '*/main']],
          userRemoteConfigs: [[url: 'https://github.com/IvanAlekseenko-sys/brandNew.git']]
        ])
      }
    }

    stage('Test (headless)') {
      steps {
        sh '''
          set -eux
          chmod +x gradlew
          ./gradlew clean test -Dheadless=true --no-daemon
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
          includeProperties: false,
          reportBuildPolicy: 'ALWAYS',
          results: [[path: 'allure-results']]
        ])
      }
    }
  }
}
