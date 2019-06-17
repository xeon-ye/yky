package com.deloitte.services.srpmp.common.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by lixin on 06/03/2019.
 */
public class FreemarkerUtil {


    public static void fillTemplate(String templatePath, String templateFileName, String targetFilePath, Map<String, Object> dataMap) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(FreemarkerUtil.class, templatePath);
        Template template = configuration.getTemplate(templateFileName);
        File outFile = new File(targetFilePath);
        if (!outFile.getParentFile().exists()){
            outFile.getParentFile().mkdirs();
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
        template.process(dataMap, out);
    }


}
