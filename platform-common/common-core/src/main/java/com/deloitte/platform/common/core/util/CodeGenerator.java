package com.deloitte.platform.common.core.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.deloitte.platform.common.core.exception.PlatFormException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.mybatis.MyTypeConvert;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @Author : jackliu
 * @Date : Create in 1:01 28/01/2019
 * @Description :
 * @Modified :
 */
public class CodeGenerator {


    public static void generatorCode(GeneratorCodePropertis gcProper) {

        // 代码生成器
        AutoGenerator mpgBase=constructAG(gcProper);
        List FileOutConfigList=  new ArrayList<FileOutConfig>();
        gdcPojoTemplet(FileOutConfigList,gcProper);
        if(gcProper.isOnlyGeneratorPOJO()){
            mpgBase.setTemplate(
                    new TemplateConfig()
                            .setController(null)
                            .setService(null)
                            .setServiceImpl(null)
                            .setEntity("/generator_templet/entity.java"));
        }else{
            gdcBaseTemplet(FileOutConfigList,gcProper);
            mpgBase.setTemplate(
                    new TemplateConfig()
                            .setController("/generator_templet/controller.java")
                            .setService("/generator_templet/service.java")
                            .setServiceImpl("/generator_templet/serviceImpl.java")
                            .setEntity("/generator_templet/entity.java"));
        }
        if(gcProper.isGeneratorSpringCloud()){
            gdcFeignTemplet(FileOutConfigList,gcProper);
        }

        mpgBase.getCfg().setFileOutConfigList(FileOutConfigList);
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
                        .setParent(gcProper.getPackageMsg())
        ).setCfg(
                new InjectionConfig(){
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        map.put("apiPackage", gcProper.getApiPackage()+"."+gcProper.getModuleName());
                        this.setMap(map);
                    }
                }
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

    private static void gdcPojoTemplet(List FileOutConfigList,GeneratorCodePropertis gcProper){
        FileOutConfigList.add(new FileOutConfig("/generator_templet/vo.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath() +packageToPath(gcProper.getApiPackage())+gcProper.getModuleName()+"/vo/" + tableInfo.getEntityName() + "Vo" + StringPool.DOT_JAVA;
            }});
        FileOutConfigList.add(new FileOutConfig("/generator_templet/form.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath() +packageToPath(gcProper.getApiPackage())+gcProper.getModuleName()+"/vo/" + tableInfo.getEntityName() + "Form" + StringPool.DOT_JAVA;
            }});
        FileOutConfigList.add(new FileOutConfig("/generator_templet/queryForm.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath() +packageToPath(gcProper.getApiPackage())+gcProper.getModuleName()+"/param/" + tableInfo.getEntityName() + "QueryForm" + StringPool.DOT_JAVA;
            }});
        FileOutConfigList.add(new FileOutConfig("/generator_templet/queryParam.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath() +packageToPath(gcProper.getApiPackage())+gcProper.getModuleName()+"/param/" + tableInfo.getEntityName() + "QueryParam" + StringPool.DOT_JAVA;
            }});
    }

    private static void gdcBaseTemplet(List FileOutConfigList,GeneratorCodePropertis gcProper){
 /*       FileOutConfigList.add(new FileOutConfig("/generator_templet/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath()  + packageToPath(gcProper.getPackageMsg()) + gcProper.getModuleName() + "/service/I" + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        });
        FileOutConfigList.add(new FileOutConfig("/generator_templet/serviceImpl.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath()  + packageToPath(gcProper.getPackageMsg()) + gcProper.getModuleName() + "/service/impl/" + tableInfo.getEntityName() + "ServiceImpl" + StringPool.DOT_JAVA;
            }
        });
        FileOutConfigList.add(new FileOutConfig("/generator_templet/controller.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath()  + packageToPath(gcProper.getPackageMsg()) + gcProper.getModuleName() + "/controller/" + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
            }
        });*/
        FileOutConfigList.add(new FileOutConfig("/generator_templet/client.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath() +packageToPath(gcProper.getApiPackage()) + gcProper.getModuleName() + "/client/" + tableInfo.getEntityName() + "Client" + StringPool.DOT_JAVA;
            }
        });
    }

    private static void gdcFeignTemplet(List FileOutConfigList,GeneratorCodePropertis gcProper){
        FileOutConfigList.add(new FileOutConfig("/generator_templet/feign.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return gcProper.getApiProjectPath() +packageToPath(gcProper.getApiPackage())+gcProper.getModuleName()+"/feign/" + tableInfo.getEntityName() + "FeignService" + StringPool.DOT_JAVA;
            }});
    }

    private static String packageToPath(String packageStr){
          if(StringUtils.isNotEmpty(packageStr)){
              String path=StringUtils.replace(packageStr,".","/");
              String fullPath="/src/main/java/"+path+"/";
              return fullPath;
          }
          return "";
    }


    private static List<TableFill> myTableFill() {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        //如 每张表都有一个创建时间、修改时间
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


}
