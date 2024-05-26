#! /bin/bash -e

docker-compose --file docker-compose-dev.yaml --env-file dev.env up --build