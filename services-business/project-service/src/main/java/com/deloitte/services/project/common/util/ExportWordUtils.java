package com.deloitte.services.project.common.util;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author : lixin
 * @Date: Created By 07/03/2019
 * @Description :
 * @Modified:
 */
@Slf4j
public class ExportWordUtils {

    //@Value("${word.export.path:word/export}")
    private static String WORD_EXPORT_PATH = "word/export";
    //private static String WORD_EXPORT_PATH;

    /**
     * 生成word文件
     *
     * @param templateName   模板名称前缀
     * @param dataMap        数据对象
     * @param targetFileName 生成的word文件名称
     * @return 返回生成后的word文件全路径
     */
    public static String exportWord(String templateName, Map<String, Object> dataMap, String targetFileName) {

        Writer out = null;
        FileInputStream sourceXmlIn = null;
        FileOutputStream targetXmOut = null;
        try {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            File jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile();
            log.info("jarPath:" + jarPath);
            String workingDir = jarPath + "/" + WORD_EXPORT_PATH + "/tmp/" + uuid;
            String templatePath = "/" + WORD_EXPORT_PATH + "/template";
            String finalWordDir = jarPath + "/" + WORD_EXPORT_PATH + "/outcome";
            String finalWordName = finalWordDir + "/" + targetFileName;


            //第1步：创建工作目录
            makeSomeDirs(workingDir, finalWordDir);

            //第2步：生成document.xml
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
            configuration.setDefaultEncoding("UTF-8");
            configuration.setClassForTemplateLoading(ExportWordUtils.class, templatePath);
            Template template = configuration.getTemplate(templateName + ".xml");
            String documentXmlName = workingDir + "/document.xml";
            File documentXmlFile = new File(documentXmlName);
            if (!documentXmlFile.getParentFile().exists()) {
                documentXmlFile.getParentFile().mkdirs();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(documentXmlFile), "utf-8"));
            template.process(dataMap, out);

            //第3步：解压模板
            InputStream stream = ExportWordUtils.class
                    .getClassLoader()
                    .getResourceAsStream("word/export/template/" + templateName + ".docx");

            String wordUnzipDir = workingDir + "/word_unzip";
            ZipUtil.unzip(stream, wordUnzipDir);

            //第4步：替换document.xml
            sourceXmlIn = new FileInputStream(documentXmlName);
            targetXmOut = new FileOutputStream(wordUnzipDir + "/word/document.xml");
            IOUtils.copy(sourceXmlIn, targetXmOut);

            //第5步：打包文件夹，重新生成docx文件
            ZipUtil.zip(wordUnzipDir, finalWordName);

            return finalWordName;
        } catch (Exception e) {
            log.error("生成word文件异常", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(sourceXmlIn);
            IOUtils.closeQuietly(targetXmOut);
        }
    }

    private static void makeSomeDirs(String... dirs) {
        for (String d : dirs) {
            File file = new File(d);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        // 测试WORD文档导出
        //ExportWordUtils.WORD_EXPORT_PATH = "word/export";

        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "小黑");
        jsonObject.put("color", "白色");
        jsonObject.put("pinzhong", "皇室血统");
        jsonObject.put("remark", "出生皇室，贵族纯正血统，排行6子，聪慧惹人爱，有胜任王的资质...");

        Map dataMap = new HashMap(jsonObject.size());
        dataMap.put("dog", jsonObject);
        String fileName = "Dog饲养材料说明_" + DateFormatUtils.format(new Date(), "yyyyMMdd") + ".docx";
        String fileUrl = ExportWordUtils.exportWord("template_dog", dataMap, fileName);

        // 测试PDF导出
        File file = new File("F:/ExcelOut");
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetSource = "F:/ExcelOut";
        String pdfName = "Dog饲养材料说明_" + DateFormatUtils.format(new Date(), "yyyyMMdd") + ".PDF";
        //WordConvertUtil.wordConvertToPdf(fileUrl, targetSource + File.separator + pdfName);
        WordConvertUtil.docx2PDF(fileUrl, targetSource + File.separator + pdfName);*/

        JSONObject dataPackage = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("projectName", "TEST");
        data.put("projectCode", "TEST");
        data.put("organizationName", "TEST");
        data.put("ouChargeStaffName", "TEST");
        data.put("projectHeaderName", "TEST");
        data.put("finHeaderName", "TEST");
        data.put("projectCatgory", "TEST");
        data.put("cycle", "TEST");
        data.put("planYear", "TEST");
        data.put("projectHeaderPost", "TEST");
        data.put("projectConnectStaffName", "TEST");
        data.put("proConnectStaffTel", "TEST");
        data.put("adress", "TEST");
        data.put("zipCode", "TEST");
        data.put("budBasis", "TEST");
        data.put("budContent", "TEST");
        data.put("budTargetMeasure", "TEST");
        data.put("budCondition", "TEST");

        dataPackage.put("app", data);

        Map dataMap = new HashMap();
        dataMap.put("data", dataPackage);

        String targetFileName = "附件2项目申报文本材料_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999) + ".docx";
        String fileUrl = ExportWordUtils.exportWord("template_dept_budget", dataMap, targetFileName);

        try {
            File rootPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile();
            String pdfTemp = rootPath + File.separator + "tempDir";
            File targetFilePath = new File(pdfTemp);
            if (!targetFilePath.exists()) {
                targetFilePath.mkdirs();
            }
            String targetPdfFileName = "附件2项目申报文本材料_" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomUtils.nextInt(0, 9999) + ".pdf";
            String targetFile = targetFilePath.getAbsolutePath()+File.separator+targetPdfFileName;
            //WordConvertUtil.wordConvertToPdf(fileUrl, targetFile);
            WordConvertUtil.docx2PDF(fileUrl, targetFile);

        } catch (Exception e) {
            log.error("生成PDF文件异常", e.getCause(), e.getMessage());
        } finally {
        }

    }

}


