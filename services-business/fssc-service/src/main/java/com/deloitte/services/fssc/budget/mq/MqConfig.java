package com.deloitte.services.fssc.budget.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MqConfig {

    public static final String que="QUEUE.SRPMP.FSSC";

    public static final String sendToOa="FSSC.SENDTO.OA";

    public static final String fsscTopicEx="FSSC_TOPIC_EX";
    @Bean
    public Queue queueOa(){
        return new Queue(sendToOa);
    }

    @Bean
    public TopicExchange topicExchangeOa(){
        return new TopicExchange(fsscTopicEx);
    }

    @Bean
    public Binding binding(Queue queueOa,TopicExchange topicExchangeOa){
        return BindingBuilder.bind(queueOa).to(topicExchangeOa).with(sendToOa);
    }
}
