#!/bin/bash

java -jar /opt/app/dtt_service/config-service-1.0-SNAPSHOT.jar --spring.config.location=/opt/app/dtt_service/config/bootstrap.yml > /opt/app/dtt_service/applog/environment-config.log