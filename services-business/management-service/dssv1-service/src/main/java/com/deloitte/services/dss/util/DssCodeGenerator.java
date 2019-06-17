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
        gcp.setUsername("dssv1");
        gcp.setPassword("dssv1");
        gcp.setBaseProjectPath("D:\\1");
        gcp.setApiProjectPath("D:\\2");
        gcp.setModuleName("dssv1");
        gcp.setTableNames(new String[] {
                "DSS_FIN_ETL_PROJECT_LIB",
                "DSS_FIN_ETL_PROJECT_PRET",
                "DSS_FIN_ETL_PROJECT_FACT"
        });
        gcp.setAuthor("chitose");
        gcp.setOnlyGeneratorPOJO(false);
        gcp.setGeneratorSpringCloud(true);
        CodeGenerator.generatorCode(gcp);
    }

}
