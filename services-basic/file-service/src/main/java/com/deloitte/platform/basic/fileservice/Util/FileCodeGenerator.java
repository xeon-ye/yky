package com.deloitte.platform.basic.fileservice.Util;

import com.deloitte.platform.common.core.util.CodeGenerator;
import com.deloitte.platform.common.core.util.GeneratorCodePropertis;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class FileCodeGenerator {

    public static void main(String[] args) {
        //代码生成器属性
        GeneratorCodePropertis gcp=new GeneratorCodePropertis();
        //数据库驱动
        gcp.setDriverName("oracle.jdbc.OracleDriver");
        //数据库连接URL
        gcp.setUrl("jdbc:oracle:thin:@124.17.100.184:1521:TEST");
        //数据库用户名
        gcp.setUsername("bpm");
        //数据库密码
        gcp.setPassword("bpm");
        //需要生成通用代码的表名
        gcp.setTableNames(new String[] { "FILE_INFO"});
        // 项目文件生成后存放的绝对路径
        gcp.setBaseProjectPath("C:\\workspace\\cams\\code\\trunk-cams\\cams-back\\platform\\services-basic\\file-service");
        // 接口文件生成后存放的绝对路径
        gcp.setApiProjectPath("C:\\workspace\\cams\\code\\trunk-cams\\cams-back\\platform\\platform-api");
        // 服务名称
        gcp.setModuleName("fileservice");
        // 服务的包名（默认的包名是：com.deloitte.services ，如果是业务服务，使用默认值就可以了）
        gcp.setPackageMsg("com.deloitte.platform.basic");
        // 生成代码的作者
        gcp.setAuthor("jackliu");

        gcp.setOnlyGeneratorPOJO(false);
        // 是否支持微服务间的调用（true:会生成feign客户端已经相应的fallback函数）
        gcp.setGeneratorSpringCloud(true);

        CodeGenerator.generatorCode(gcp);
    }
}
