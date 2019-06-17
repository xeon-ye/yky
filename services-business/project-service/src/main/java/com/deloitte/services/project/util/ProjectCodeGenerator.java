package com.deloitte.services.project.util;

import com.deloitte.platform.common.core.util.CodeGenerator;
import com.deloitte.platform.common.core.util.GeneratorCodePropertis;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class ProjectCodeGenerator {

    public static void main(String[] args) {
        GeneratorCodePropertis gcp=new GeneratorCodePropertis();
        gcp.setDriverName("oracle.jdbc.OracleDriver");
        gcp.setUrl("jdbc:oracle:thin:@124.17.100.184:1521:TEST");
        gcp.setUsername("project");
        gcp.setPassword("project");
        gcp.setBaseProjectPath("E:\\workspace-idea\\platform\\services-business\\project-service");
        gcp.setApiProjectPath("E:\\workspace-idea\\platform\\platform-api");
        gcp.setModuleName("project");
        gcp.setTableNames(new String[] {
                "PROJECT_APPROVAL_VOUCHERS"
        });
        gcp.setAuthor("zhengchun");
        //gcp.setOnlyGeneratorPOJO(false);
        gcp.setOnlyGeneratorPOJO(true);
        gcp.setGeneratorSpringCloud(true);
        CodeGenerator.generatorCode(gcp);
    }
}
