FROM docker.io/anapsix/alpine-java
WORKDIR /opt/app/dtt_service/
COPY src/main/resources/localtime /etc/localtime
COPY target/bpm-service-1.0-SNAPSHOT.jar /opt/app/dtt_service/bpm-service-1.0-SNAPSHOT.jar
COPY src/main/resources/bootstrap.yml /opt/app/dtt_service/config/bootstrap.yml
COPY src/main/resources/STSONG.TTF /usr/share/fonts/
COPY src/main/resources/SIMSUN.TTC /usr/share/fonts/
COPY src/main/resources/STSONG.TTF /opt/jdk1.8.0_192/jre/lib/fonts/
COPY src/main/resources/SIMSUN.TTC /opt/jdk1.8.0_192/jre/lib/fonts/
COPY src/main/resources/agent/ /opt/app/dtt_service/agent/
ADD start.sh /opt/app/dtt_service/
EXPOSE 8030
RUN chmod +x /opt/app/dtt_service/start.sh \
  && echo "Asia/Shanghai" > /etc/TZ && echo "Asia/Shanghai" > /etc/timezone
ENV LC_ALL "zh_CN.UTF-8"
ENTRYPOINT /opt/app/dtt_service/start.sh
