#!/bin/bash

USER="root"
USER_PASSWORD="root"

DATABASE="xmen-ml-db"
CONTAINER_NAME="xmen-ml-db"


docker \
  run \
  --detach \
  --env POSTGRES_USER=${USER} \
  --env POSTGRES_PASSWORD=${USER_PASSWORD} \
  --env POSTGRES_DB=${DATABASE} \
  --name ${CONTAINER_NAME} \
  --publish 5432:5432 \
  postgres:9.4;
