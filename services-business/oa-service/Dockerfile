FROM docker.io/anapsix/alpine-java
WORKDIR /opt/app/dtt_service/
COPY src/main/resources/localtime /etc/localtime
COPY target/oa-service-1.0-SNAPSHOT.jar /opt/app/dtt_service/oa-service-1.0-SNAPSHOT.jar
COPY src/main/resources/bootstrap.yml /opt/app/dtt_service/config/bootstrap.yml
COPY src/main/resources/agent/ /opt/app/dtt_service/agent/
ADD start.sh /opt/app/dtt_service/
EXPOSE 8030
RUN chmod +x /opt/app/dtt_service/start.sh \
  && echo "Asia/Shanghai" > /etc/TZ && echo "Asia/Shanghai" > /etc/timezone
ENTRYPOINT /opt/app/dtt_service/start.sh
