package com.deloitte.services.oa.util;

import com.deloitte.platform.common.core.util.CodeGenerator;
import com.deloitte.platform.common.core.util.GeneratorCodePropertis;

public class OaCodeGenerator {
    public static void main(String[] args) {
        GeneratorCodePropertis gcp=new GeneratorCodePropertis();
        gcp.setDriverName("oracle.jdbc.OracleDriver");
        gcp.setUrl("jdbc:oracle:thin:@124.17.100.184:1521:TEST");
        gcp.setUsername("oa_self");
        gcp.setPassword("oa_self");
        gcp.setBaseProjectPath("C:\\Users\\qifu\\work\\BJYKY\\tech\\code\\trunk-cams\\cams-back\\platform\\services-business\\oa-service");
        gcp.setApiProjectPath("C:\\Users\\qifu\\work\\BJYKY\\tech\\code\\trunk-cams\\cams-back\\platform\\platform-api");
        gcp.setModuleName("meeting");
        gcp.setTableNames(new String[] { "OA_MEETING_HEADER","OA_MEETING_MEMBERS","OA_MEETING_ADDRESS","OA_MEETING_ROOM"});
        gcp.setAuthor("fuqiao");
        // 服务的包名（默认的包名是：com.deloitte.services ，如果是业务服务，使用默认值就可以了）
        gcp.setPackageMsg("com.deloitte.services");
        gcp.setOnlyGeneratorPOJO(false);
        gcp.setGeneratorSpringCloud(true);

        CodeGenerator.generatorCode(gcp);
    }
}
