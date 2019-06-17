package com.deloitte.services.srpmp.common.word.test;


import com.deloitte.services.srpmp.common.word.dto.Student;
import com.deloitte.services.srpmp.common.word.dto.Teacher;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixin on 06/03/2019.
 */
public class WordExportTest {

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDefaultEncoding("UTF-8");

        Teacher t = new Teacher();
        t.setName("张三");
        t.setAge("23");
        t.setBirthday("1988-12-11");
        t.setEmail("a@163.com");
        t.setGender("男");
        t.setPhone("13365666122");

        Student s1 = new Student();
        s1.setName("欧阳锋");
        s1.setPosition("教授");
        s1.setSalary("333331000");
        s1.setSerial("1");

        Student s2 = new Student();
        s2.setName("周伯通");
        s2.setPosition("副教授");
        s2.setSalary("3000");
        s2.setSerial("2");

        Student s3 = new Student();
        s3.setName("杨过");
        s3.setPosition("讲师");
        s3.setSalary("100");
        s3.setSerial("3");

        List<Student> stuList = new ArrayList<>();
        stuList.add(s1);
        stuList.add(s2);
        stuList.add(s3);

        t.setStuList(stuList);

        configuration.setClassForTemplateLoading(WordExportTest.class, "/wordtemplate");
        Template template = null;
        template = configuration.getTemplate("temp1.ftl");

        File outFile = new File("C:\\word\\导出\\export4.docx");
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));


        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("tch", t);

        template.process(dataMap, out);


    }


}

