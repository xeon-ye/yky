package com.deloitte.platform.common.core.mq;

/**
 * Author:LIJUN
 * Date:17/04/2019
 * Description:
 */
public enum MqExchangeQueueEnum {

    EXCHANGE_TOPIC("EXCHANGE_TOPIC", "topic模式"),

    QUEUE_SRPMP_FSSC("QUEUE.SRPMP.FSSC", "同步财务队列"),

    ROUTINGKEY_SRPMP_FSSC("routingKey.srpmp.fssc", "routingKey");


    MqExchangeQueueEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
