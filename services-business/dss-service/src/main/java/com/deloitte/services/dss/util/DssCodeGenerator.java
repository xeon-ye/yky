package com.deloitte.services.dss.util;

import com.deloitte.platform.common.core.util.CodeGenerator;
import com.deloitte.platform.common.core.util.GeneratorCodePropertis;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class DssCodeGenerator {


    /**
     * 注意：新生成的代码会完全覆盖旧代码，对于已经有修改的Controller,Service等类，一定要慎用
     */
    public static void main(String[] args) {
        GeneratorCodePropertis gcp=new GeneratorCodePropertis();
        gcp.setDriverName("oracle.jdbc.OracleDriver");
        gcp.setUrl("jdbc:oracle:thin:@124.17.100.184:1521:TEST");
        gcp.setUsername("dss");
        gcp.setPassword("dss");
        gcp.setBaseProjectPath("C:\\test_code\\platform\\services-business\\srpmp-service");
        gcp.setApiProjectPath("C:\\test_code\\platform\\platform-api");
        gcp.setModuleName("dss");
        gcp.setTableNames(new String[] { "SRPMS_PROJECT"});
        gcp.setAuthor("jack");
        gcp.setGeneratorSpringCloud(false);
        CodeGenerator.generatorCode(gcp);
    }

}
