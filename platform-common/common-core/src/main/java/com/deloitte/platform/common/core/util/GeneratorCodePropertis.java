package com.deloitte.platform.common.core.util;

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
public class GeneratorCodePropertis {

    private String driverName;

    private String url;

    private String username;

    private String password;

    private String baseProjectPath;

    private String apiProjectPath;

    private String moduleName;

    private String apiPackage="com.deloitte.platform.api";

    private String packageMsg="com.deloitte.services";

    private String[] tableNames;

    private String author;

    //只生成POJO对象
    private boolean onlyGeneratorPOJO=true;

    //生成SpringCloud Feign客户端
    private boolean generatorSpringCloud=false;

    //生成Vue页面组件
    private boolean generatorVue=false;
}
