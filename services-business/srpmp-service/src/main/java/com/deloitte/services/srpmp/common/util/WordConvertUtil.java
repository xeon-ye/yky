package com.deloitte.services.srpmp.common.util;

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
import org.springframework.util.ResourceUtils;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.*;

/**
 * Created by lixin on 04/03/2019.
 * word转换工具类
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
        if (html == null) return html;
        //先转码
        html = StringEscapeUtils.unescapeHtml4(html);

        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
        document.select("br").append("\n");
        document.select("p").prepend("\n");
        document.select("h1").prepend("\n");
        document.select("h2").prepend("\n");
        document.select("h3").prepend("\n");
        document.select("h4").prepend("\n");
        document.select("h5").prepend("\n");
        document.select("h6").prepend("\n");
        document.select("h7").prepend("\n");
        document.select("div").prepend("\n");

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
                public Font getFont(String familyName, String encoding, float size, int style, Color color) {
                    try {
//                        Resource fileRource = new ClassPathResource("simsun.ttf");

//                        String path = fileRource.getFile().getAbsolutePath();

                        String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
                        String fontFilePath = jarPath + "/simsun.ttf";
                        BaseFont bfChinese = BaseFont.createFont(fontFilePath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                        Font fontChinese = new Font(bfChinese, size, style, color);
                        if (familyName != null)
                            fontChinese.setFamily(familyName);
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
     * @param url 文件地址
     * @return
     */
    public static String downloadHttpUrl(String url) {
        if (StringUtils.isBlank(url)){
            return  null;
        }
        try {
            String classPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
            //存储文件名
            String fileName  = new Date().getTime()+url.substring(url.lastIndexOf("/")+NumberUtils.INTEGER_ONE,url.length());
            //存储目录
            String  dir = classPath +File.separator+"download"+File.separator+"tmp"+File.separator;
            URL httpurl = new URL(url);
            File dirfile = new File(dir);
            if (!dirfile.exists()) {
                dirfile.mkdirs();
            }
            fileName  = dir + fileName;
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
     * @param obj
     *            字符串对象
     * @return
     */
    protected static boolean notNull(String obj) {
        if (obj != null && !obj.equals("") && !obj.equals("undefined")
                && !obj.trim().equals("") && obj.trim().length() > 0) {
            return true;
        }
        return false;
    }

    /**
     * doc to pdf
     *
     * @param docPath
     *            doc源文件
     * @param pdfPath
     *            pdf目标文件
     */
    public static void docx2PDF(String docPath, String pdfPath) {
        try {
//            // 验证License
            if (!getLicense()) {
                return;
            }
            if (notNull(docPath) && notNull(pdfPath)) {
                File file = new File(pdfPath);
                FileOutputStream os = new FileOutputStream(file);
                com.aspose.words.Document doc = new com.aspose.words.Document(docPath);
                FontSettings fontSettings = new FontSettings();
                String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
                fontSettings.setFontsFolder(jarPath, false);
                doc.setFontSettings(fontSettings);
                doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//          downloadHttpUrl("http://localhost:8999/budgetimport.docx");

//        String strContent = htmlToText(StringEscapeUtils.unescapeHtml4("&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;简要说明&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;任务立项&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;的&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;必要性、研究目标、技术方案、预期成果、相关基础条件等（限800字以内）。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span id=&quot;OLE_LINK5&quot;&gt;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;立项依据：&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;国际&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;eScience&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;组织Force11于2016年提出了科学数据管理应达到准则：Findable, Accessible, Interoperable and Reusable （FAIR）。在该准则实施方案中明确指出语义支持的元数据标准，是FAIR实施过程中必不可少的内容，也是最终保证数据能够重利用，并提升后续决策分析性能的基石。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;本体是在标准化的术语体系基础上，进而建立术语间的逻辑关系，通过人和计算机都可以理解的方式表示，并支持依据逻辑关系进行推理。在本体的支持下，知识搜索、积累、共享和人工智能医学决策的效率将大大提高。因此，本体越来越成为一种占主导地位的科学信息组织策略。通用数据元素（Common Data Elements，CDEs），是跨不同数据&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;集共同&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;使用的数据元素，规定了数据元素的概念、属性和数值表示，&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;是元数据描述的基础&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;。只有将CDE与本体等语义标准相结合，才能保证&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;机器可以理解的元数据建设，实现信息数据标准和数据采集标准结合。在&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;诸如精准医学研究中，&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;实现&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;广泛来源的临床和研究数据整合，并支持后续数据分析和医学决策。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;研究&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;目标&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;本研究拟建立一个通用性强的、符合IS&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;O&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;/IEC&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;11179&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;标准的本体支持的通用数据元素表示模型，在收集国内外通用数据元素数据基础上，建立一个共享的、支持重利用、&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;本体&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;语义支持的通用数据元素存储和应用平台。并利用一个多中心临床试验，在其数据管理过程中，测评上述通用元素数据平台的性能。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;技术方案&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;1、&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;建立语义支持的数据元素标准化表示模型，以该模型为基础进行自动化的元数据&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;数据元素生成、&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;数据元素推荐评价研究；&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;a)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;以ISO/ICE11179数据元素模型为基础，建立语义支持的数据元素各部分表示模型，并用OWL语言实现。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;b)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;收集国际国内通用数据元素，利用上述模型实现本体支持的数据元素的OWL表示。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;c)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;本体支持的&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;数据元素数据库的建设。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;d)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;基于语义分析的数据元素质量评价、相关&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;度统计&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;方法的建立，并测评。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;2、&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;本体资源建设与应用扩展：&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;a)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;建设分子标志物本体；&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;b)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;建立中英文双语言本体表示体系：提供对通用数据元素应用系统的中文支持。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;3、&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;通用数据元素数据库系统建设: 建设本体支持的数据元素的应用平台，保证&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;本系统的通用&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;数据元素符合ISO/IEC11179数据标准，并具有通用数据元素的存储、管理、编审、唯一标识符、查询、质量评价、推荐&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;、&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;共享和远程调用&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;等功能&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;；支持用户的数据元素的自建，并给予结构和语义的标准化支持。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;4、&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;应用案例研究：围绕&ldquo;环丝氨酸治疗耐药性结核杆菌多中心临床研究&rdquo;建立元数据体系和&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;支持数据管理，并对上述通用数据元素数据库系统性能进行测评。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;研究基础：&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;（1）已经在基础医学科学数据中心建立了国内第一个生物医学本体服务系统，包括提供本体存储、术语映射和文本注释的本体资源平台&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;MedPortal&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;，提供中文术语检索的搜索工具，映像了国际知名的本体重利用工具&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;OntoFox&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;；（2）已经建立本体的OWL表示解析工作流，并应用于数据库建设项目（高分辨率可视人数据库），并获得软件著作权；（3）组织并参与了多个重要OBO FOUNDRY本体的翻译，包括RO,BFO和HPO等; (4)是国际生物医学本体建设组织&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;OBO &lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;FOUNDRY工作委员会成员，参与&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;OBO核心&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;本体建设&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;，了解国际生物医学本体建设的进展和围绕本体展开的生物医学信息项目；（5）将国家细胞系平台资源整合入细胞系本体（CLO），在国际会议上汇报。&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;（6&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;）&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;具备多中心&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;临床&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;实验&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;数据管理和分析的经验。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&lt;/p&gt;"));
//        System.out.println("strContent : " + strContent);
//        String[] strArr = strContent.split("\\n");
//        System.out.println(strArr.length);


//
//        SrpmsProjectApplyInnPreSubmitVo vo = new SrpmsProjectApplyInnPreSubmitVo();
//        vo.setProjectAbstract("&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;简要说明&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;任务立项&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;的&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;必要性、研究目标、技术方案、预期成果、相关基础条件等（限800字以内）。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span id=&quot;OLE_LINK5&quot;&gt;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;立项依据：&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;国际&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;eScience&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;组织Force11于2016年提出了科学数据管理应达到准则：Findable, Accessible, Interoperable and Reusable （FAIR）。在该准则实施方案中明确指出语义支持的元数据标准，是FAIR实施过程中必不可少的内容，也是最终保证数据能够重利用，并提升后续决策分析性能的基石。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;本体是在标准化的术语体系基础上，进而建立术语间的逻辑关系，通过人和计算机都可以理解的方式表示，并支持依据逻辑关系进行推理。在本体的支持下，知识搜索、积累、共享和人工智能医学决策的效率将大大提高。因此，本体越来越成为一种占主导地位的科学信息组织策略。通用数据元素（Common Data Elements，CDEs），是跨不同数据&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;集共同&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;使用的数据元素，规定了数据元素的概念、属性和数值表示，&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;是元数据描述的基础&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;。只有将CDE与本体等语义标准相结合，才能保证&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;机器可以理解的元数据建设，实现信息数据标准和数据采集标准结合。在&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;诸如精准医学研究中，&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;实现&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;广泛来源的临床和研究数据整合，并支持后续数据分析和医学决策。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;研究&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;目标&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;本研究拟建立一个通用性强的、符合IS&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;O&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;/IEC&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;11179&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;标准的本体支持的通用数据元素表示模型，在收集国内外通用数据元素数据基础上，建立一个共享的、支持重利用、&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;本体&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;语义支持的通用数据元素存储和应用平台。并利用一个多中心临床试验，在其数据管理过程中，测评上述通用元素数据平台的性能。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;技术方案&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;1、&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;建立语义支持的数据元素标准化表示模型，以该模型为基础进行自动化的元数据&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;数据元素生成、&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;数据元素推荐评价研究；&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;a)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;以ISO/ICE11179数据元素模型为基础，建立语义支持的数据元素各部分表示模型，并用OWL语言实现。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;b)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;收集国际国内通用数据元素，利用上述模型实现本体支持的数据元素的OWL表示。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;c)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;本体支持的&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;数据元素数据库的建设。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;d)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;基于语义分析的数据元素质量评价、相关&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;度统计&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;方法的建立，并测评。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;2、&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;本体资源建设与应用扩展：&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;a)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;建设分子标志物本体；&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;b)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;建立中英文双语言本体表示体系：提供对通用数据元素应用系统的中文支持。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;3、&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;通用数据元素数据库系统建设: 建设本体支持的数据元素的应用平台，保证&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;本系统的通用&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;数据元素符合ISO/IEC11179数据标准，并具有通用数据元素的存储、管理、编审、唯一标识符、查询、质量评价、推荐&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;、&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;共享和远程调用&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;等功能&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;；支持用户的数据元素的自建，并给予结构和语义的标准化支持。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;4、&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;应用案例研究：围绕&ldquo;环丝氨酸治疗耐药性结核杆菌多中心临床研究&rdquo;建立元数据体系和&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;支持数据管理，并对上述通用数据元素数据库系统性能进行测评。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;研究基础：&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:28.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;（1）已经在基础医学科学数据中心建立了国内第一个生物医学本体服务系统，包括提供本体存储、术语映射和文本注释的本体资源平台&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;MedPortal&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;，提供中文术语检索的搜索工具，映像了国际知名的本体重利用工具&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;OntoFox&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;；（2）已经建立本体的OWL表示解析工作流，并应用于数据库建设项目（高分辨率可视人数据库），并获得软件著作权；（3）组织并参与了多个重要OBO FOUNDRY本体的翻译，包括RO,BFO和HPO等; (4)是国际生物医学本体建设组织&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;OBO &lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;FOUNDRY工作委员会成员，参与&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;OBO核心&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;本体建设&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;，了解国际生物医学本体建设的进展和围绕本体展开的生物医学信息项目；（5）将国家细胞系平台资源整合入细胞系本体（CLO），在国际会议上汇报。&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;（6&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;）&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;具备多中心&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;临床&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;实验&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;color:#000000;&quot;&gt;数据管理和分析的经验。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&lt;/p&gt;");
//        vo.setApprovalNecessay("&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;span id=&quot;OLE_LINK1&quot;&gt;&lt;/span&gt;&lt;span id=&quot;OLE_LINK2&quot;&gt;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;阐述立项的必要性以及国内外开展研究及进展情况（限&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;800&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;字以内）。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;自从人类基因组草图公布以来，数据的积累成为生命科学进步的一个重要特征，数据也深刻改变了生命科学研究的面貌。目前已经公认，生物学已经成为数据密集型科学（&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;National Research Council. 2011&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;）。数据给生命科学研究带来了新的解决问题的途径，也带来了巨大挑战，这些挑战不仅在于数据本身，更在于如何通过科学有效的数据管理，使得数据&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;和&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;知识的整合、知识发现和创新、以及后续的数据重利用成为可能。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;2016&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;年，开放科学组织&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;Forc&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;e1&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;1&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;明确提出科学数据管理应该达到的准则&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;&mdash;&mdash;FAIR&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;（&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;findable, Accessible, Interoperable and Reusable&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;）准则（&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#222222;background-color:#ffffff;&quot;&gt;Scientific data, 2016, 3.&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;）。这意味着，通过该准则的实施，数据能够被人和计算机所理解。在该准则实施的细则中，明确元数据（&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;metadata&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;）所应达到的标准&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;&mdash;&mdash;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;元数据（描述数据的数据，例如二代测序的样本来源、样本处理、测序和分析平台等信息）的描述应该足够详细；应分配持久、全局标识符；元数据应被注册、索引、检索和获取；应有丰富准确的属性表示，并借助语义标准实现；也应以形式化的、可广泛应用的语言表示等。&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;这些具体的细则要求元数据标准的描述应该需要语义标准支撑，以公开和共享的方式构建，其使用也是可以溯源的。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;国际上元数据的上述可以通过数据元素实现，国际&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;ISO/IEC11179&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;数据标准提出了&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;（通用）&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;数据元素表示的框架，得到了不同领域的广泛应用。但是该框架没有&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;表示数据元素内部的关联，模型没有指明如何实现语义的支持。另一方面，近年来术语体系也不断发展，从传统的分类词表、术语&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;集发展&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;成为本体。所谓本体，是在标准化的术语体系基础上，进而建立术语间的逻辑关系，通过人和计算机都可以理解的方式表示，并支持依据逻辑关系进行推理。&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;近些年，在&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;OBO FOUNDRY&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;组织统一规范（&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#222222;background-color:#ffffff;&quot;&gt;Nature biotechnology, 2007, 25: 1251.&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;）的本体建设原则支持下，产生了诸如&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;HPO&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;、&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;C&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;hEBI&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;和&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;GO &lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;等高质量广泛应用的生物医学本体。&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;如果将数据元素与本体结合，实现元数据的形式化表示和语义标准化，将极大提升元数据机器可理解的程度，也使得单系统内部使用的数据元素变为跨系统、&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;跨数据集共同&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;使用的通用数据元素（&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;Common Data Elements, CDE&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;s&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;）。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;国际上一些机构开始将数据元素与本体相结合进行元数据描述，以提升数&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;据自动处理的可能性。这方面比较有代表性的工作是美国&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;Stanford&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;大学&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;Musen&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;实验室的&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;CEDAR&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;项目（&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt; J Am Med Inform &lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;Assoc&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt; 2015;22:1148&ndash;1152&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;），该研究针对于生物医学基础研究建立&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;元数据建设平台和资源库。该项目是美国&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;NIH&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;的&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;BD2K&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;（大数据到知识）项目（&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#222222;background-color:#ffffff;&quot;&gt;Journal of the American Medical Informatics Association, 2014, 21: 957-958.&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;）建设科学&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;研据数据&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;生态环境中重要组成部分。美国&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;NCI&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt; &lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;c&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;aDSR&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;也建立了符合织&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;ISO/IEC11179&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;的通用据元素数据库，也利用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;NCIT&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;对其进行语义支持。但笔者在使用过程中发现数据元素中有大量逻辑不一致的问题。&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;美国&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;Vanderbilt&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;大学开发的&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;RedCap&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;系统（&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#222222;background-color:#ffffff;&quot;&gt;Journal of biomedical informatics, 2009, 42(2): 377-381.&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;）国际用户广泛，该系统也支持本体支撑的元数据建设，但是该系统的重点更在于元数据建成后的多中心数据管理。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;我国也非常重视通用数据元素等元数据标&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;准的制定和实施。但是这些标准的实施由各个独立部门独自&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;进行，&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;实施的技术门槛较高、质量不一、容易出错，数据元素与语义系统分割，还是容易形成数据孤岛，不利用在更大范围实现数据整合。同时&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;，这些数据元素对基础研究领域数据覆盖度不高，给科研数据汇交和整&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;合&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;带来一定困难。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;本研究拟建立一个通用性强的、符合&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;ISO/IEC11179&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;标准的本体支持的通用数据元素表示模型，在收集国&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;内&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;外&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;通用数据元素数据基础上，建&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;立一个共享的、支持重利用、语义支持的通用数据元素存储和应用平台&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;。同时，&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;将完善该平台的本体支持，提升本体的中文支持，并进行关联的领域本体建设&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;。还将进行应用案例研究，对该系统&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;在多中心临床试验研究数据管理中的&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;使用进行评估。&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;&quot;&gt;该系统的建设，将有助于从数据项层面提升领域内容元数据标准的实施，是大数据整合、人工智能性能提升并提供新型决策分析方法的前提和基础。&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;text-indent:30.0pt;&quot;&gt;&lt;/p&gt;");
//        vo.setProjectTarget("string");
//        vo.setResearchContentMain("&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;1&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;、&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;建立语义支持的&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;数据元素标准化表示模型，以该模型为基础进行自动化的元数据&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;数据元素生成、&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;数据元素推荐评价研究；&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;a)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;以&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;ISO/ICE11179&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;数据元素模型为基础，建立语义支持的数据元素各部分表示模型，并用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;OWL&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;语言实现。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;b)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;收集&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;存储在&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;国际&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;元数据资源库中&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;主要研究（例如&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;TCGA&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;，&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt; NHANE&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;S&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;）的&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;数据元素，实现符合上述模型&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;的&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;OWL&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;表示，并实现本体和数据元素的图数据库存储。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;c)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;本体支持的&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;数据元素数据库的建设。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;d)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;基于语义分析的数据元素质量评价、相关&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;度统计&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;方法的建立，并测评。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;2&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;、&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;本体资源建设与应用扩展：&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;a)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;建设分子标志物本体（&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;Molecular Biomarker Ontology&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;），该本体应符合&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;OBO&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;准则，并体现了专业领域对生物标志物的共识和常规研究体系；&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;b)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;建立中英文双语言本体表示体系&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;i&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;.&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;该体系应该符合&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;OBO&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;的基本原则，由能在常用的本体系统&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;Protege&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;、&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;BioPortal&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;和&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;OntoAnimal&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;系列工具中正常显示、推理、检索&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;、&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;可视化&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;和远程调用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;ii.&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;通过该体系实现已经完成中文翻译的本体的中文本体库。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;3&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;、&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;数据元素数据库系统建设&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;: &lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;建设本体支持的数据元素的应用平台，保证本系统的&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;数据元素符合&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;ISO/IEC11179&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;数据标准，基本功能有&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;：&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;a)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;该平台支持通用数据元素的存储、管理、编审，对符合质量标准通用数据元素分配唯一标识符；&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;b)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;支持通用数据元素的查询、质量评价、推荐、相似度评价；&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;c)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;支持通用数据元素共享和远程调用；&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;d)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;支持用户的数据元素的自建，并给予结构和语义的标准化支持。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;4&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;、&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;应用案例研究：围绕&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;&ldquo;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;环丝氨酸治疗耐药性结核杆菌多中心临床研究&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;&rdquo;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;建立元数据体系和数据管理系统。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;a)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;依托内容&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;3&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;的&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;数据元素系统建立本体支持的符合&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;ISO/ICE11179&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;标准，针对本多中心研究的&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;数据元&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;素&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;体系&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;；&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;b&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;支持数据采集系统数据&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;标准化实施&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;；&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;c&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;)&lt;/span&gt;&lt;span&gt;&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;测评&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;通用&lt;/span&gt;&lt;span style=&quot;font-family:'Times New Roman';font-size:12.0pt;color:#000000;&quot;&gt;数据元素系统对多中心数据管理数据质量和数据分析性能的提升。&lt;/span&gt;&lt;/p&gt;");
//        vo.setAchievementForm("&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;1、科研论文&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;：本研究将发表SCI论文两篇，将介绍本课题建立的语义支持的数据元素标准化表示模型&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;和建设的分子标志物本体及其应用。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;2、软件系统&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;：建设在线&ldquo;通用数据元素数据库系统&rdquo;，该系统将具备&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;通用数据元素的管理、注册、审编、检索，术语支持、数据导出和远程服务等功能。用户可以自由浏览和登录数据使用。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;3、软件著作权&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;：申请软件著作权1-2项，并获得软件著作权证书。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;4、符合FAIR准则的数据产品&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;：&ldquo;通用数据元素数据库系统&rdquo;中存储和建设的通用数据资源，将符合FAIR准则对元数据质量的规定。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;/p&gt;");
//        vo.setAchievementBenefit("&lt;p&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;1、社会效益：建立可共享、可溯源、符合国际数据元素标准的存储应用平台，将大大降低数据元素建设的门槛，推进本体等语义标准的应用。有助于推动更大范围的数据整合&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;，对于后续的数据分析和决策制定提供新的方法，提升人工&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;智能的性能，&lt;/span&gt;&lt;span style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;是大数据建设的基础性工作。本研究的数据平台，将有助于推进科研项目&ldquo;全链条&rdquo;数据管理的实施，帮助数据中心数据注册质量提升，具有较大的实际应用意义。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p class=&quot;Normal ListParagraph&quot;&gt;&lt;span class=&quot;Normal ListParagraph&quot;&gt;1、 &lt;/span&gt;&lt;span class=&quot;Normal ListParagraph&quot; style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;经济效益：本课题的数据资源将公开共享，&lt;/span&gt;&lt;span class=&quot;Normal ListParagraph&quot; style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;也服务&lt;/span&gt;&lt;span class=&quot;Normal ListParagraph&quot; style=&quot;font-family:'Calibri';font-size:12.0pt;&quot;&gt;于各类生物医学数据公司，将提升这些公司专业的元数据管理水平，具有潜在的经济效益。&lt;/span&gt;&lt;/p&gt;&lt;p&gt;&lt;/p&gt;");
//        vo.setSuperiorityDeptBasic("string");
//        vo.setCoopDeptList(new ArrayList<>());
//        vo.setMainMemberList(new ArrayList<>());
//
//
//
//        String basePath = WordConvertUtil.class.getClassLoader().getResource("").getPath();
//        String uuid = UUID.randomUUID().toString();
//
//
//
//        SrpmsProjectDeptVo leadDept = new SrpmsProjectDeptVo();
//        SrpmsProjectPersonVo leadPerson = new SrpmsProjectPersonVo();
//        Map<String, Object> dataMap = new HashMap<>();
//
//        vo.setProjectAbstract(WordConvertUtil.htmlToText(StringEscapeUtils.unescapeHtml4(vo.getProjectAbstract())));
//        vo.setApprovalNecessay(WordConvertUtil.htmlToText(StringEscapeUtils.unescapeHtml4(vo.getApprovalNecessay())));
//        vo.setProjectTarget(WordConvertUtil.htmlToText(StringEscapeUtils.unescapeHtml4(vo.getProjectTarget())));
//        vo.setResearchContentMain(WordConvertUtil.htmlToText(StringEscapeUtils.unescapeHtml4(vo.getResearchContentMain())));
//        vo.setAchievementForm(WordConvertUtil.htmlToText(StringEscapeUtils.unescapeHtml4(vo.getAchievementForm())));
//        vo.setAchievementBenefit(WordConvertUtil.htmlToText(StringEscapeUtils.unescapeHtml4(vo.getAchievementBenefit())));
//        vo.setSuperiorityDeptBasic(WordConvertUtil.htmlToText(StringEscapeUtils.unescapeHtml4(vo.getSuperiorityDeptBasic())));


//            vo.setProjectAbstract("");
//            vo.setApprovalNecessay("");
//            vo.setProjectTarget("");
//            vo.setResearchContentMain("");
//            vo.setAchievementBenefit("");
//            vo.setSuperiorityDeptBasic("");


//        dataMap.put("vo", vo);`
//        dataMap.put("leadDept", leadDept);
//        dataMap.put("leadPerson", leadPerson);

//        FreemarkerUtil.fillTemplate("/word/export/template","template_apply_inn_pre.xml",basePath+uuid+"/document.xml", dataMap);


        String docxFile = "C:\\Work\\word\\real\\import\\fBFk7lzeXlaAZooXAAgUDZYaRww03.docx";
        String pdfFile = "C:\\Work\\word\\real\\import\\fBFk7lzeXlaAZooXAAgUDZYaRww03.pdf";
        docx2PDF(docxFile, pdfFile);

//        wordConvertToHtml(new File("C:\\Work\\word\\real\\import\\fBFk7lzBDVKAImtNAD38Z8vENCc21.docx"),"C:\\Work\\word\\real\\import");

    }

}
