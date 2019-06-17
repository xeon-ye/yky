#!/bin/bash

java -javaagent:/opt/app/dtt_service/agent/skywalking-agent.jar -jar /opt/app/dtt_service/bpm-service-1.0-SNAPSHOT.jar -Dfile.encoding=UTF-8 --spring.config.location=/opt/app/dtt_service/config/bootstrap.yml > /opt/app/dtt_service/applog/environment-bpm.log