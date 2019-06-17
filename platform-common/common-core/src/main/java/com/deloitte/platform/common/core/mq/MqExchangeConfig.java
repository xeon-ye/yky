package com.deloitte.platform.common.core.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:LIJUN
 * Date:17/04/2019
 * Description:
 */
@Configuration
public class MqExchangeConfig {

    /**
     * topicExchange
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(MqExchangeQueueEnum.EXCHANGE_TOPIC.getCode());
    }

    //定义队列
    @Bean
    public Queue fsscQueue(){
        return new Queue(MqExchangeQueueEnum.QUEUE_SRPMP_FSSC.getCode());
    }

//    @Bean
//    public Queue yyQueue(){
//        return new Queue(MqExchangeQueueEnum.QUEUE_SRPMP_YY.getCode());
//    }

    //队列和交换机绑定
    @Bean
    Binding bindingFsscQueue(Queue fsscQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(fsscQueue).to(topicExchange).with(MqExchangeQueueEnum.ROUTINGKEY_SRPMP_FSSC.getCode());
    }

//    @Bean
//    Binding bindingYYQueue(Queue yyQueue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(yyQueue).to(topicExchange).with(MqExchangeQueueEnum.ROUTINGKEY_SRPMP_TOPIC.getCode());
//    }
}
