package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FsscServiceApplication {
    @Autowired
    private RestTemplateBuilder builder;
    @Bean
    RestTemplate restTemplate(){
        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return builder.additionalMessageConverters(m).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(FsscServiceApplication.class, args);
    }

}

