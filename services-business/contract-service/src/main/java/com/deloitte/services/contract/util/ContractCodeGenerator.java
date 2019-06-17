package com.deloitte.services.contract.util;

import com.deloitte.platform.common.core.util.CodeGenerator;
import com.deloitte.platform.common.core.util.GeneratorCodePropertis;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class ContractCodeGenerator {

    public static void main(String[] args) {
        GeneratorCodePropertis gcp=new GeneratorCodePropertis();
        gcp.setDriverName("oracle.jdbc.OracleDriver");
        gcp.setUrl("jdbc:oracle:thin:@124.17.100.184:1521:TEST");
        gcp.setUsername("contract");
        gcp.setPassword("contract");
        gcp.setBaseProjectPath("D:\\work\\workSpace\\workSpaceV4\\platform\\services-business\\contract-service");
        gcp.setApiProjectPath("D:\\work\\workSpace\\workSpaceV4\\platform\\platform-api");
        gcp.setModuleName("contract");
        gcp.setTableNames(new String[] { "SYS_DICT"});
        gcp.setAuthor("yangyq");
        gcp.setOnlyGeneratorPOJO(true);
        gcp.setGeneratorSpringCloud(true);
        CodeGenerator.generatorCode(gcp);
    }
}
