#! /bin/bash

docker stop demo-postgres
docker rm demo-postgres
docker run --name demo-postgres -p 15432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_USER=testuser -d postgres
