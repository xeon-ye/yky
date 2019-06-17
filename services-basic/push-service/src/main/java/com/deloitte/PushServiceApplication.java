package com.deloitte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication(exclude =  {RabbitAutoConfiguration.class })//, ConfigClientAutoConfiguration.class,BusAutoConfiguration.class
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
public class PushServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PushServiceApplication.class, args);
    }

}
