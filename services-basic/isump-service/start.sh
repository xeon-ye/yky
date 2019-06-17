#!/bin/bash

java -javaagent:/opt/app/dtt_service/agent/skywalking-agent.jar -jar /opt/app/dtt_service/isump-service-1.0-SNAPSHOT.jar --spring.config.location=/opt/app/dtt_service/config/bootstrap.yml > /opt/app/dtt_service/applog/environment-isump.log