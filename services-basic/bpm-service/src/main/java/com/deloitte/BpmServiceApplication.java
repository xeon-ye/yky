package com.deloitte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
		                            org.activiti.spring.boot.SecurityAutoConfiguration.class})
@ComponentScan({"org.activiti.rest.diagram","com.deloitte.*"})
@EnableAsync
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
public class BpmServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpmServiceApplication.class, args);
	}

}
