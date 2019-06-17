package com.deloitte.platform.basic.bpmservice.exception;

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
@ConfigurationProperties(prefix = "bpm-service")
public class BpmPropertis {



}
