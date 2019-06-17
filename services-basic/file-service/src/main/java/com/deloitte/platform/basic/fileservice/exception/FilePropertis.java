package com.deloitte.platform.basic.fileservice.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 10:47 17/02/2019
 * @Description :
 * @Modified :
 */
@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "file-service")
public class FilePropertis {

    private String uploadLocation;


}
