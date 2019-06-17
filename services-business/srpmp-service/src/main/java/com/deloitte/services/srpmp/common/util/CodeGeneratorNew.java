package com.deloitte.services.srpmp.common.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.deloitte.platform.common.core.util.GeneratorCodePropertis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : jackliu
 * @Date : Create in 1:01 28/01/2019
 * @Description :
 * @Modified :
 */
public class CodeGeneratorNew {


    public static void generatorCode(GeneratorCodePropertis gcProper) {

        // 代码生成器
        AutoGenerator mpgBase=constructAG(gcProper);
     //   mpgBase.getCfg().setFileOutConfigList(gdcBaseTemplet(gcProper));
        if(gcProper.isGeneratorSpringCloud()){

        }
        mpgBase.execute();

    }

    private static  AutoGenerator constructAG(GeneratorCodePropertis gcProper){
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                new GlobalConfig()
                        .setOutputDir( gcProper.getBaseProjectPath()+"/src/main/java")
                        .setFileOverride(true)
                        .setActiveRecord(true)
                        .setEnableCache(false)
                        .setBaseResultMap(true)
                        .setBaseColumnList(true)
                        .setSwagger2(true)
                        .setAuthor(gcProper.getAuthor())
                        .setOpen(false)
                        //全局唯一id
                        .setIdType(IdType.ID_WORKER)
        ).setDataSource(
                new DataSourceConfig()
                        .setDriverName(gcProper.getDriverName())
                        .setUrl(gcProper.getUrl())
                        .setUsername(gcProper.getUsername())
                        .setPassword(gcProper.getPassword())
                        //默认使用框架自带的oracle类型转换，如果不满足，就使用自定义类型转换类
                        .setTypeConvert(
                                (gcProper.getDriverName().contains("oracle"))?new OracleTypeConvert():new MySqlTypeConvert())
                // .setTypeConvert(new MyTypeConvert())
        ).setPackageInfo(
                new PackageConfig()
                        .setModuleName(gcProper.getModuleName())
                        .setParent("com.deloitte.services")
        ).setCfg(
                new InjectionConfig(){
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        map.put("apiPackage", "com.deloitte.platform.api."+gcProper.getModuleName());
                        this.setMap(map);
                    }
                }.setFileOutConfigList(gdcBaseTemplet(gcProper))
        ).setTemplate(
                new TemplateConfig()
                        .setController("/generator_templet/controller.java")
                        .setService("/generator_templet/service.java")
                        .setServiceImpl("/generator_templet/serviceImpl.java")
                        .setEntity("/generator_templet/entity.java")
//                        .setMapper("/generator_templet/mapper.java")
//                        .setXml("/generator_templet/mapper.xml")
        ).setStrategy(
                new StrategyConfig()
                        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
                        .setNaming(NamingStrategy.underline_to_camel)
                        .setColumnNaming(NamingStrategy.underline_to_camel)
                        .setEntityLombokModel(true)
                        .setRestControllerStyle(true)
                        .setInclude(gcProper.getTableNames())
                        .setTableFillList(myTableFill())
                        .setEntityColumnConstant(true)
                        .setControllerMappingHyphenStyle(true)
                        .setTablePrefix(gcProper.getModuleName() + "_")
        );
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        return  mpg;

    }

    private static List<FileOutConfig> gdcBaseTemplet(GeneratorCodePropertis gcProper){

        List FileOutConfigList=  new ArrayList<FileOutConfig>();
        String filePath = gcProper.getModuleName().replaceAll("\\.","/");

        FileOutConfigList.add(new FileOutConfig("/generator_templet/vo.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath() + "/src/main/java/com/deloitte/platform/api/"+filePath+"/vo/" + tableInfo.getEntityName() + "Vo" + StringPool.DOT_JAVA;
            }});
        FileOutConfigList.add(new FileOutConfig("/generator_templet/form.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath() + "/src/main/java/com/deloitte/platform/api/"+filePath+"/vo/" + tableInfo.getEntityName() + "Form" + StringPool.DOT_JAVA;
            }});
        FileOutConfigList.add(new FileOutConfig("/generator_templet/queryForm.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath() + "/src/main/java/com/deloitte/platform/api/"+filePath+"/param/" + tableInfo.getEntityName() + "QueryForm" + StringPool.DOT_JAVA;
            }});
        FileOutConfigList.add(new FileOutConfig("/generator_templet/queryParam.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath() + "/src/main/java/com/deloitte/platform/api/"+filePath+"/param/" + tableInfo.getEntityName() + "QueryParam" + StringPool.DOT_JAVA;
            }});
        FileOutConfigList.add(new FileOutConfig("/generator_templet/client.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath() + "/src/main/java/com/deloitte/platform/api/"+filePath+"/" + tableInfo.getEntityName() + "Client" + StringPool.DOT_JAVA;
            }});
        if(gcProper.isGeneratorSpringCloud()){
            FileOutConfigList.add(new FileOutConfig("/generator_templet/feign.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return gcProper.getApiProjectPath() + "/src/main/java/com/deloitte/platform/api/"+filePath+"/feign/" + tableInfo.getEntityName() + "FeignService" + StringPool.DOT_JAVA;
                }});
        }

        return FileOutConfigList;

    }



    private static List<TableFill> myTableFill() {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        //如 每张表都有一个创建时间、修改时间
        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        //修改时，修改时间会修改，
        //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
        //使用公共字段填充功能，就可以实现，自动按场景更新了。
        //如下是配置
        TableFill createTimeField = new TableFill("create_time", FieldFill.INSERT);
        TableFill createByField = new TableFill("create_by", FieldFill.INSERT);
        TableFill updateTimeField = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        TableFill updateByField = new TableFill("update_by", FieldFill.INSERT_UPDATE);
        tableFillList.add(createTimeField);
        tableFillList.add(createByField);
        tableFillList.add(updateTimeField);
        tableFillList.add(updateByField);
        return tableFillList;
    }


    public static void main(String[] args){
        System.out.println("a.bc.dd".replaceAll("\\.","/"));
    }


}
