package com.deloitte.platform.support.zuulservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableSwagger2
public class SwaggerGatewayConfig {

    @Autowired
    Environment environment;


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("医科院MacRP项目接口API文档  RESTful APIs")
                .description("接口说明文档")
                .termsOfServiceUrl("")
                .contact(new Contact("jackliu","jackliu@deloitte.com.cn","jackliu@deloitte.com.cn"))
                .version("1.0")
                .build();
    }

}
