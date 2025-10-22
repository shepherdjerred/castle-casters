// =====================================================
// DEPRECATED: This Jenkinsfile has been replaced
// =====================================================
//
// This project has migrated from Earthly/Jenkins to GitHub Actions/Dagger.
//
// New CI/CD configuration:
// - GitHub Actions: .github/workflows/ci.yaml
// - Dagger Module: .dagger/
//
// See MIGRATION.md for details about the migration.
//
// This file is kept for reference and can be removed after successful migration.
// =====================================================

pipeline {
    agent {
        kubernetes {
            defaultContainer 'earthly'
            inheritFrom 'default'
            yaml '''
            spec:
                containers:
                    - name: tailscale
                      image: tailscale/tailscale
                      securityContext:
                          privileged: true
                      env:
                      - name: TS_AUTHKEY
                        valueFrom:
                          secretKeyRef:
                            name: tailscale-auth-key
                            key: TS_AUTHKEY
                      - name: TS_ACCEPT_DNS
                        value: true
                      - name: TS_KUBE_SECRET
                        value:
                      - name: TS_USERSPACE
                        value: false
                    - name: earthly
                      image: earthly/earthly
                      env:
                      - name: NO_BUILDKIT
                        value: 1
                      - name: NO_COLOR
                        value: 1
                      command: ["sleep"]
                      args: ["1h"]
'''
        }
    }

    environment {
        GITHUB_USERNAME = "shepherdjerred"
        GITHUB_TOKEN = credentials('GITHUB_TOKEN')
        EARTHLY_TOKEN = credentials('EARTHLY_TOKEN')
    }

    stages {
        stage('Build') {
            steps {
                sh 'earthly --ci --secret GH_TOKEN=$GITHUB_TOKEN --sat=lamport --org sjerred --output +ci'
                // junit 'surefire-reports/*.xml'
                archiveArtifacts artifacts: 'castle-casters.jar', allowEmptyArchive: true
            }
        }
    }
}
