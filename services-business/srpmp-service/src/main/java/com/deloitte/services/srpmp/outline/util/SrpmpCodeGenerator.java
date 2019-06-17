package com.deloitte.services.srpmp.outline.util;

import com.deloitte.platform.common.core.util.CodeGenerator;
import com.deloitte.platform.common.core.util.GeneratorCodePropertis;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class SrpmpCodeGenerator {

    public static void main(String[] args) {
        GeneratorCodePropertis gcp=new GeneratorCodePropertis();
        gcp.setDriverName("oracle.jdbc.OracleDriver");
        gcp.setUrl("jdbc:oracle:thin:@124.17.100.184:1521:TEST");
        gcp.setUsername("srpmp");
        gcp.setPassword("srpmp");
        gcp.setBaseProjectPath("C:\\codeGenerator\\srpmp-services");
        gcp.setApiProjectPath("C:\\codeGenerator\\platform-api");
        gcp.setModuleName("srpmp.project");
        gcp.setTableNames(new String[] { "SRPMS_PROJECT_TASK_REFORM"});
        gcp.setAuthor("zhouchen");
//        gcp.setSpringCloud(true);
        gcp.setGeneratorSpringCloud(true);

        CodeGenerator.generatorCode(gcp);
    }
}
