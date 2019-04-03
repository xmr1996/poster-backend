#!/bin/bash

###############################################################################
# Variables required by this script:
#  - CI_PROJECT_PATH
#  - CI_PROJECT_NAME
#  - CI_COMMIT_SHA
#  - DOCKER_REPO_USER
#  - DOCKER_REPO_PASS
#  - RDS_USERNAME
#  - RDS_PASSWORD
#  - SPRING_PROFILE
###############################################################################

###############################################################################
# Project-specific variables, these should be updated per-project             
CONTAINER_PORT=8333
HOST_PORT=8333
###############################################################################


# Any future command that fails will exit the script
set -e

# Helper variables
DOCKER_REGISTRY="registry.uwm-nm-te-capstone.com:8083"
IMAGE_NAME="$DOCKER_REGISTRY/$CI_PROJECT_PATH"
IMAGE_TAG=$CI_COMMIT_SHA
FULL_IMAGE_REF="$IMAGE_NAME:$IMAGE_TAG"
CONTAINER_NAME=$CI_PROJECT_NAME

# Delete previously existing docker image(s) and container
if [ "$(docker ps -aq -f status=running -f name=$CONTAINER_NAME)" ]; then
    echo "Stopping previously existing container $CONTAINER_NAME ..."
    docker kill $CONTAINER_NAME
fi

if [ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]; then
    echo "Removing previously existing container $CONTAINER_NAME ..."
    docker rm -f $CONTAINER_NAME
fi

echo "Removing existing docker images for $IMAGE_NAME ..."
if [[ ! "$(docker images -q $IMAGE_NAME 2> /dev/null)" == "" ]]; then
    docker rmi -f $(docker images -q $IMAGE_NAME)
fi

# Pull the requested docker image
echo "Pulling $FULL_IMAGE_REF"
docker login $DOCKER_REGISTRY --username $DOCKER_REPO_USER --password $DOCKER_REPO_PASS
docker pull $IMAGE_NAME:$IMAGE_TAG

# Run the docker image
echo "Running '$CONTAINER_NAME' on port $CONTAINER_PORT from '$FULL_IMAGE_REF'"
docker run -d \
    -p $HOST_PORT:$CONTAINER_PORT \
    --name $CONTAINER_NAME \
    -e RDS_USERNAME \
    -e RDS_PASSWORD \
    -e SPRING_PROFILE \
    $FULL_IMAGE_REF
