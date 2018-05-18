#!/bin/bash

docker stop xmen-ml-db
docker rm xmen-ml-db

./db/dockerPostgre.sh
