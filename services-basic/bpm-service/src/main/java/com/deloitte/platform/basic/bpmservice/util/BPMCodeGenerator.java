package com.deloitte.platform.basic.bpmservice.util;

import com.deloitte.platform.common.core.util.CodeGenerator;
import com.deloitte.platform.common.core.util.GeneratorCodePropertis;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class BPMCodeGenerator {

    public static void main(String[] args) {
        GeneratorCodePropertis gcp=new GeneratorCodePropertis();
        gcp.setDriverName("oracle.jdbc.OracleDriver");
        gcp.setUrl("jdbc:oracle:thin:@124.17.100.184:1521:TEST");
        gcp.setUsername("bpm");
        gcp.setPassword("bpm");
        gcp.setBaseProjectPath("C:\\Users\\qifu\\work\\BJYKY\\tech\\code\\trunk-cams\\cams-back\\platform\\services-basic\\bpm-service");
        gcp.setApiProjectPath("C:\\Users\\qifu\\work\\BJYKY\\tech\\code\\trunk-cams\\cams-back\\platform\\platform-api");
        gcp.setModuleName("bpmservice");
        gcp.setPackageMsg("com.deloitte.platform.basic");
        gcp.setTableNames(new String[] {"BPM_PROCESS_TASK_APPROVE"});
        gcp.setAuthor("qifu");
        gcp.setOnlyGeneratorPOJO(true);
        gcp.setGeneratorSpringCloud(false);

        CodeGenerator.generatorCode(gcp);
    }
}
