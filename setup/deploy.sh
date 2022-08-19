#!/bin/bash

PATH=$PATH:`pwd`/build/tools/func

workdir=`dirname "$0"`

cd $workdir/../code && \
./mvnw clean package azure-functions:deploy
