#!/bin/bash

# any future command that fails will exit the script
set -e

# This will write the public key of our ec2 instance
eval $(ssh-agent -s)
echo "$SSH_PRIVATE_KEY" | tr -d '\r' | ssh-add - > /dev/null

# Whenever we ssh into a EC2 instance that we have never sshed before, we get prompt to verify fingerprint
# disable the host key checking.
echo "disable Host Key Checking"
chmod a+x ./deploy/disableHostKeyChecking.sh
./deploy/disableHostKeyChecking.sh

#let us ssh to deploy server in order to deploy docker image
chmod a+x ./deploy/dockerImageUpdateAndStart.sh
echo "deploying to ${DEPLOY_SERVER}"
ssh ubuntu@$DEPLOY_SERVER \
    CI_PROJECT_PATH=$CI_PROJECT_PATH \
    CI_PROJECT_NAME=$CI_PROJECT_NAME \
    CI_COMMIT_SHA=$CI_COMMIT_SHA \
    DOCKER_REPO_USER=$DOCKER_REPO_USER \
    DOCKER_REPO_PASS=$DOCKER_REPO_PASS \
    RDS_USERNAME=$RDS_USERNAME \
    RDS_PASSWORD=$RDS_PASSWORD \
    SPRING_PROFILE=$SPRING_PROFILE \
    bash -s < ./deploy/dockerImageUpdateAndStart.sh
