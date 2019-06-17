package com.deloitte.services.isump.util;

import com.deloitte.platform.common.core.util.CodeGenerator;
import com.deloitte.platform.common.core.util.GeneratorCodePropertis;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class IsumpCodeGenerator {

    public static void main(String[] args) {
        GeneratorCodePropertis gcp=new GeneratorCodePropertis();
        gcp.setDriverName("oracle.jdbc.OracleDriver");
        gcp.setUrl("jdbc:oracle:thin:@124.17.100.184:1521:TEST");
        gcp.setUsername("isump");
        gcp.setPassword("isump");
        gcp.setBaseProjectPath("C:\\D\\codeGenerator\\isump-services");
        gcp.setApiProjectPath("C:\\D\\codeGenerator\\platform-api");
        gcp.setModuleName("isump");
        gcp.setTableNames(new String[] { "ISUMP_USER" });
//        gcp.setTableNames(new String[] { "ISUMP_USER","ISUMP_BASE_USER","ISUMP_DEPUTY_ACCOUNT","ISUMP_DEPUTY_ACCOUNT_ROLE",
//        "ISUMP_DICT","ISUMP_ORG_ROLE","ISUMP_ORGANIZATION","ISUMP_RESOURCE","ISUMP_ROLE","ISUMP_ROLE_RESOURCE","ISUMP_SUBORDINATE_ACCOUNT"});
        gcp.setAuthor("zhangdi");
        gcp.setOnlyGeneratorPOJO(false);
        gcp.setGeneratorSpringCloud(true);

        CodeGenerator.generatorCode(gcp);
    }
}
