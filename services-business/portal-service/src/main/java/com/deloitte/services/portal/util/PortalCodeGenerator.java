package com.deloitte.services.portal.util;

import com.deloitte.platform.common.core.util.CodeGenerator;
import com.deloitte.platform.common.core.util.GeneratorCodePropertis;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class PortalCodeGenerator {

    public static void main(String[] args) {
        GeneratorCodePropertis gcp=new GeneratorCodePropertis();
        gcp.setDriverName("oracle.jdbc.OracleDriver");
        gcp.setUrl("jdbc:oracle:thin:@124.17.100.184:1521:TEST");
        gcp.setUsername("potal");
        gcp.setPassword("potal");
        gcp.setBaseProjectPath("C:\\codeGenerator\\potal-services");
        gcp.setApiProjectPath("C:\\codeGenerator\\platform-api");
        gcp.setModuleName("potal");
        gcp.setTableNames(new String[] { "user"});
        gcp.setAuthor("pengchao");
        gcp.setGeneratorSpringCloud(true);

        CodeGenerator.generatorCode(gcp);
    }
}
