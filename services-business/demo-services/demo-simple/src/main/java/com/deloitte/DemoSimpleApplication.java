package com.deloitte;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication(exclude =  { DruidDataSourceAutoConfigure.class,DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
public class DemoSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSimpleApplication.class, args);
	}

}

