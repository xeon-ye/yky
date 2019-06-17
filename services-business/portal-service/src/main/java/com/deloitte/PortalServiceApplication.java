package com.deloitte;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCasClient
@MapperScan("com.deloitte.services.**.mapper")
public class PortalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalServiceApplication.class, args);
	}

}