package com.deloitte.services.project.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.FontSettings;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import fr.opensagres.xdocreport.itext.extension.font.ITextFontRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.*;

/**
 * @Author : lixin
 * @Date : Created By 04/03/2019
 * @Description : word转换工具类
 * @Modified :
 */
@Slf4j
public class WordConvertUtil {


    /**
     * 将word文件转换为html文件
     *
     * @param wordFileUrl  word文件的URL地址
     * @param htmlFilePath 生成html文件的目标文件夹
     * @return 生成后的html文件的绝对路径
     */
    public static String wordConvertToHtml(String wordFileUrl, String htmlFilePath) throws IOException {
        URLConnection urlConnection = new URL(wordFileUrl).openConnection();
        XWPFDocument document = new XWPFDocument(urlConnection.getInputStream());
        XHTMLOptions options = XHTMLOptions.create();
        options.setExtractor(new FileImageExtractor(new File(htmlFilePath + File.separator + "image")));
        options.URIResolver(new BasicURIResolver("image"));
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".html";
        String filePath = htmlFilePath + File.separator + fileName;
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath));
        XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
        xhtmlConverter.convert(document, out, options);
        out.close();
        return filePath;
    }

    /**
     * 将word文件转换为html文件
     *
     * @param wordFileUrl  word文件的URL地址
     * @param htmlFilePath 生成html文件的目标文件夹
     * @return 生成后的html文件的绝对路径
     */
    public static String wordConvertToHtml(File wordFileUrl, String htmlFilePath) throws IOException {
        XWPFDocument document = new XWPFDocument(new FileInputStream(wordFileUrl));
        XHTMLOptions options = XHTMLOptions.create();
        options.setExtractor(new FileImageExtractor(new File(htmlFilePath + File.separator + "image")));
        options.URIResolver(new BasicURIResolver("image"));
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".html";
        String filePath = htmlFilePath + File.separator + fileName;
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath));
        XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
        xhtmlConverter.convert(document, out, options);
        out.close();
        return filePath;
    }

    public static String htmlToText(String html) {
        if (html == null) {
            return html;
        }
        //先转码
        html = StringEscapeUtils.unescapeHtml4(html);

        Document document = Jsoup.parse(html);
        //makes html() preserve linebreaks and spacing
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));
        document.select("br").append("\n");
        document.select("p").prepend("\n");
        String s = document.html().replaceAll("\\\\n", "\n").replaceAll("&nbsp;", " ").replaceFirst("\n", "");
        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }

    public static void batchHtmlToText(JSONObject voJson, String... properties) {
        for (String property : properties) {
            voJson.put(property, htmlToText(voJson.getString(property)));
        }
    }

    public static void wordConvertToPdf(String sourceFilePath, String targetFilePath) throws Exception {
        InputStream source = null;
        OutputStream target = null;
        try {
            source = new FileInputStream(sourceFilePath);
            target = new FileOutputStream(targetFilePath);
            Map<String, String> params = new HashMap<String, String>();
            PdfOptions options = PdfOptions.create();
            options.fontProvider(new ITextFontRegistry() {
                @Override
                public Font getFont(String familyName, String encoding, float size, int style, Color color) {
                    try {
                        ClassPathResource cpr = new ClassPathResource("templatefont"+File.separator+"simsun.ttf");
                        File file = cpr.getFile();
                        BaseFont bfChinese = BaseFont.createFont(file.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                        Font fontChinese = new Font(bfChinese, size, style, color);
                        if (familyName != null) {
                            fontChinese.setFamily(familyName);
                        }
                        return fontChinese;
                    } catch (Throwable e) {
                        log.error("PDF加载字体异常.", e);
                        return ITextFontRegistry.getRegistry().getFont(familyName, encoding, size, style, color);
                    }
                }
            });
            wordConverterToPdf(source, target, options, params);
        } catch (Exception e) {
            throw e;
        } finally {
            if (source != null) {
                source.close();
            }
            if (target != null) {
                target.close();
            }
        }
    }


    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     *
     * @param source 源为word文档， 必须为docx文档
     * @param target 目标输出
     * @param params 需要替换的变量
     * @throws Exception
     */
    private static void wordConverterToPdf(InputStream source,
                                           OutputStream target, Map<String, String> params) throws Exception {
        wordConverterToPdf(source, target, null, params);
    }

    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     *
     * @param source  源为word文档， 必须为docx文档
     * @param target  目标输出
     * @param params  需要替换的变量
     * @param options PdfOptions.create().fontEncoding( "windows-1250" ) 或者其他
     * @throws Exception
     */
    private static void wordConverterToPdf(InputStream source, OutputStream target,
                                           PdfOptions options,
                                           Map<String, String> params) throws Exception {
        XWPFDocument doc = new XWPFDocument(source);
        paragraphReplace(doc.getParagraphs(), params);
        for (XWPFTable table : doc.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    paragraphReplace(cell.getParagraphs(), params);
                }
            }
        }
        PdfConverter.getInstance().convert(doc, target, options);
    }

    /**
     * 替换段落中内容
     */
    private static void paragraphReplace(List<XWPFParagraph> paragraphs, Map<String, String> params) {
        if (MapUtils.isNotEmpty(params)) {
            for (XWPFParagraph p : paragraphs) {
                for (XWPFRun r : p.getRuns()) {
                    String content = r.getText(r.getTextPosition());
                    if (StringUtils.isNotEmpty(content) && params.containsKey(content)) {
                        r.setText(params.get(content), 0);
                    }
                }
            }
        }
    }


    /**
     * 获取学科选择
     *
     * @param dictMap
     * @param subject
     * @return
     */
    public static String paseSubjectChooice(Map<String, String> dictMap, String subject) {
        if (StringUtils.isBlank(subject) || MapUtils.isEmpty(dictMap)) {
            return null;
        }
        List<String> subjectCodes = JSONArray.parseArray(subject, String.class);
        List<String> subjectValue = new ArrayList<>();
        for (String code : subjectCodes) {
            subjectValue.add(dictMap.get(code));
        }
        return StringUtils.join(subjectValue, "/");
    }

    /**
     * 删除文件
     *
     * @param filePath
     */
    public static void delHtmlFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public static List<String> parseCodeListToText(Map<String, String> allSysDict, List<String> checkList) {
        List<String> rlist = new ArrayList<>();
        for (Map.Entry<String, String> entry : allSysDict.entrySet()) {
            String key = entry.getKey();
            if (checkList != null && checkList.contains(key)) {
                rlist.add("▉" + entry.getValue());
            } else {
                rlist.add("□" + entry.getValue());
            }
        }
        return rlist;
    }


    /**
     * 导入时将根据获取对应code
     *
     * @param allSysDict
     * @param checkStr
     * @return
     */
    public static JSONArray parseTextsToCodes(Map<String, String> allSysDict, String checkStr) {
        if (StringUtils.isBlank(checkStr) || MapUtils.isEmpty(allSysDict)) {
            return null;
        }

        String[] checkArray = checkStr.split("\\s+");
        JSONArray resultArray = new JSONArray();
        for (String checkVal : checkArray) {
            if (StringUtils.isBlank(checkVal) || checkVal.startsWith("□")) {
                continue;
            }
            for (Map.Entry<String, String> entry : allSysDict.entrySet()) {
                String value = entry.getValue();
                String code = entry.getKey();
                if (StringUtils.contains(checkVal, value)) {
                    resultArray.add(code);
                }
            }
        }
        return resultArray;
    }

    public static String getRoleCode(Map<String, String> roleMap, String value) {
        String role = StringUtils.trim(value);
        String otherProjectRole = "";
        if (StringUtils.isBlank(role)) {
            return otherProjectRole;
        }
        for (Map.Entry<String, String> entry : roleMap.entrySet()) {
            if (StringUtils.equals(entry.getValue(), role)) {
                otherProjectRole = entry.getKey();
            }
        }
        return otherProjectRole;
    }


    /**
     * 下载文件---返回下载后的文件存储路径
     *
     * @param url 文件地址
     * @return
     */
    public static String downloadHttpUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
            //存储文件名
            String fileName = System.currentTimeMillis() + url.substring(url.lastIndexOf("/") + NumberUtils.INTEGER_ONE, url.length());
            //存储目录
            String dir = classPath + File.separator + "download" + File.separator + "tmp" + File.separator;
            URL httpurl = new URL(url);
            File dirfile = new File(dir);
            if (!dirfile.exists()) {
                dirfile.mkdirs();
            }
            fileName = dir + fileName;
            FileUtils.copyURLToFile(httpurl, new File(fileName));
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取license
     *
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = WordConvertUtil.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断是否为空
     *
     * @param obj 字符串对象
     * @return
     */
    protected static boolean notNull(String obj) {
        String undefined = "undefined";
        if (obj != null && !obj.equals("") && !undefined.equals(obj)
                && !obj.trim().equals("") && obj.trim().length() > 0) {
            return true;
        }
        return false;
    }

    /**
     * doc to pdf
     *
     * @param docPath doc源文件
     * @param pdfPath pdf目标文件
     */
    public static void docx2PDF(String docPath, String pdfPath) {
        FileOutputStream os = null;
        try {
            // 验证License
            if (!getLicense()) {
                return;
            }
            if (notNull(docPath) && notNull(pdfPath)) {
                File file = new File(pdfPath);
                os = new FileOutputStream(file);
                com.aspose.words.Document doc = new com.aspose.words.Document(docPath);
                FontSettings fontSettings = new FontSettings();
                ClassPathResource cpr = new ClassPathResource("templatefont");
                File fontFilePath = cpr.getFile();
                fontSettings.setFontsFolder(fontFilePath.getAbsolutePath(), false);
                doc.setFontSettings(fontSettings);
                //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
                doc.save(os, SaveFormat.PDF);

                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

}