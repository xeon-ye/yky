#!/bin/bash

postFix="svc.cluster.local"
EUREKA_HOST_NAME="$MY_POD_NAME.$MY_IN_SERVICE_NAME.$MY_POD_NAMESPACE.$postFix"
export EUREKA_HOST_NAME=$EUREKA_HOST_NAME
BOOL_REGISTER="true"
BOOL_FETCH="true"
array=(${MY_POD_NAME//-/ })
idx=${array[${#array[*]}-1]}
if [ $EUREKA_REPLICAS = 1 ]; then
    echo "the replicas of eureka pod is one"
    BOOL_REGISTER="false"
    BOOL_FETCH="false"
    EUREKA_URL_LIST="http://$EUREKA_HOST_NAME:8761/eureka/,"
    echo " set the EUREKA_URL_LIST is $EUREKA_URL_LIST"
else
    echo "the replicas of the eureka pod is $EUREKA_REPLICAS"
    BOOL_REGISTER="true"
    BOOL_FETCH="true"
    for ((i=0 ;i<$EUREKA_REPLICAS; i ++))
    do
        if [ "$i" != "$idx" ]; then
            idx='echo $MY_POD_NAME | awk -F "-" "{print $NF}"'
            temp="http://$EUREKA_APPLICATION_NAME-$i.$MY_IN_SERVICE_NAME.$MY_POD_NAMESPACE.$postFix:8101/eureka/,"
			#eureka-2.eureka-svc.default.svc.cluster.local
			#temp="http://$EUREKA_APPLICATION_NAME-$i:8101/eureka/,"
			#temp="http://$MY_IN_SERVICE_NAME:8101/eureka/,"
			#temp="http://$MY_POD_IP:8101/eureka/,"
            EUREKA_URL_LIST="$EUREKA_URL_LIST$temp"
            echo $EUREKA_URL_LIST
        fi
    done
fi
# 这里我简单处理了，每个 pod 的 EUREKA_URL_LIST 都设置成了全部的 pod 域名。使用的时候，可以自行判断，选择不向自己注册。
# 例如 eureka-0 的 EUREKA_URL_LIST 可以剔除 http://eureka-0.eureka-service-internal.default.svc.cluster.local:8761/eureka/
EUREKA_URL_LIST=${EUREKA_URL_LIST%?}
export EUREKA_URL_LIST=$EUREKA_URL_LIST
export BOOL_FETCH=$BOOL_FETCH
export BOOL_REGISTER=$BOOL_REGISTER

java -jar /opt/app/dtt_service/eureka-service-1.0-SNAPSHOT.jar --spring.config.location=/opt/app/dtt_service/config/bootstrap.yml > /opt/app/dtt_service/applog/environment-eureka.log