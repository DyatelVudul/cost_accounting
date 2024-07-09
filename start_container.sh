#!/bin/bash

# Build container 
docker build . --tag cost_accounting:latest

# Start container using mount point
docker run -it -v $PWD:/home/developer/cost_accounting cost_accounting