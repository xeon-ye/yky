//package com.deloitte.platform.common.core.mq;
//
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//
///**
// * Author:LIJUN
// * Date:17/04/2019
// * Description:
// */
//@Configuration
//public class RabbitMqConfig {
//
//    @Value("${spring.rabbitmq.host}")
//    private String host;
//    @Value("${spring.rabbitmq.port}")
//    private Integer port;
//    @Value("${spring.rabbitmq.username}")
//    private String username;
//    @Value("${spring.rabbitmq.password}")
//    private String password;
//    @Value("${spring.rabbitmq.publisher-confirms}")
//    private Boolean publisherConfirms;
//    @Value("${spring.rabbitmq.virtual-host}")
//    private String virtualHost;
//
//    // 构建mq实例工厂
//    @Bean
//    public ConnectionFactory connectionFactory(){
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost(host);
//        connectionFactory.setPort(port);
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        connectionFactory.setPublisherConfirms(publisherConfirms);
//        connectionFactory.setVirtualHost(virtualHost);
//        return connectionFactory;
//    }
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public RabbitTemplate rabbitTemplate(){
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
//        return template;
//    }
//
//}
