#!/bin/bash

mvn clean package -DskipTests
clear
echo "Verifying sha"
java -jar target/jcrypt-cli-shasum-1.0-SNAPSHOT.jar $@