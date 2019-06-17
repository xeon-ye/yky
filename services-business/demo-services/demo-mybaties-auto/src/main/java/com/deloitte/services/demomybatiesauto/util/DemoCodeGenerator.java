package com.deloitte.services.demomybatiesauto.util;

import com.deloitte.platform.common.core.util.CodeGenerator;
import com.deloitte.platform.common.core.util.GeneratorCodePropertis;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class DemoCodeGenerator {

    public static void main(String[] args) {
        GeneratorCodePropertis gcp=new GeneratorCodePropertis();
        gcp.setDriverName("com.mysql.jdbc.Driver");
        gcp.setUrl("jdbc:mysql://localhost:3306/visa_test?characterEncoding=utf8");
        gcp.setUsername("root");
        gcp.setPassword("root");
        gcp.setBaseProjectPath("C:\\workspace\\cams\\code\\trunk-cams\\cams-back\\platform\\services-business\\demo-services\\demo-mybaties-auto");
        gcp.setApiProjectPath("C:\\workspace\\cams\\code\\trunk-cams\\cams-back\\platform\\platform-api");
        gcp.setModuleName("demomybatiesauto");
        gcp.setTableNames(new String[] { "user_demo"});
        gcp.setAuthor("jack");
        gcp.setOnlyGeneratorPOJO(false);
        gcp.setGeneratorSpringCloud(true);

        CodeGenerator.generatorCode(gcp);
    }
}
