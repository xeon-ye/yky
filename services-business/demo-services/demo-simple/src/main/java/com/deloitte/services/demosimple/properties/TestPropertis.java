package com.deloitte.services.demosimple.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 10:47 24/01/2019
 * @Description :
 * @Modified :
 */
@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "test")
public class TestPropertis {

    private String username;

    private String password;

    private List<String> strlist = new ArrayList<>();
}
