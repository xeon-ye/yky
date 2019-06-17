package com.deloitte.services.demomybatiesauto.constant;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : jackliu
 * @Date : Create in 10:47 17/02/2019
 * @Description :
 * @Modified :
 */
@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "demo-service")
public class DemoPropertis {

    private String test1;

    private String test2;

    private String test3;

    private String test4;
}
