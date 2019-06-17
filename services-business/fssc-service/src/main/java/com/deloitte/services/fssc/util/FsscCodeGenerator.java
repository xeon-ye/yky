package com.deloitte.services.fssc.util;

import com.deloitte.platform.common.core.util.CodeGenerator;
import com.deloitte.platform.common.core.util.GeneratorCodePropertis;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class FsscCodeGenerator {

    public static void main(String[] args) {
        GeneratorCodePropertis gcp = new GeneratorCodePropertis();
        gcp.setDriverName("oracle.jdbc.OracleDriver");
        gcp.setUrl("jdbc:oracle:thin:@124.17.100.184:1521:TEST");
        gcp.setUsername("pumc_fssc");
        gcp.setPassword("pumc_fssc");
        gcp.setBaseProjectPath("D:\\医科院\\ffff\\services-business\\fssc-service");
        gcp.setApiProjectPath("D:\\医科院\\ffff\\platform-api");
        gcp.setModuleName("pe22");
        gcp.setTableNames(new String[] {"BASE_BPM_REJECT_REASON"});
        gcp.setAuthor("qiliao");
        gcp.setOnlyGeneratorPOJO(false);
        gcp.setGeneratorSpringCloud(false);
        CodeGenerator.generatorCode(gcp);
    }
}
