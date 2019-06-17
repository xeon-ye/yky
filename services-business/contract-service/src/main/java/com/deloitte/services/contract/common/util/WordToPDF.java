package com.deloitte.services.contract.common.util;

import java.io.*;

import com.aspose.words.FontSettings;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.ResourceUtils;
import com.aspose.cells.Workbook;
import com.aspose.words.Document;

public class WordToPDF {
    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     * @param source 源为word文档， 必须为docx文档
     * @param target 目标输出
     * @throws Exception
     */
    public static void wordConverterToPdf(InputStream source,
                                          OutputStream target) throws Exception {
        wordConverterToPdf(source, target, null);
    }

    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     * @param source 源为word文档， 必须为docx文档
     * @param target 目标输出
     * @param options PdfOptions.create().fontEncoding( "windows-1250" ) 或者其他
     * @throws Exception
     */
    public static void wordConverterToPdf(InputStream source, OutputStream target,
                                          PdfOptions options) throws Exception {
        XWPFDocument doc = new XWPFDocument(source);
        PdfConverter.getInstance().convert(doc, target, options);
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
     * 获取license
     *
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = WordToPDF.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
                System.out.println(jarPath);
                fontSettings.setFontsFolder(jarPath, false);
                doc.setFontSettings(fontSettings);
                doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void docx2PDF(InputStream source, String pdfPath) {
        try {
//            // 验证License
            if (!getLicense()) {
                return;
            }
            if (notNull(pdfPath)) {
                File file = new File(pdfPath);
                FileOutputStream os = new FileOutputStream(file);
                Document doc = new Document(source);
                FontSettings fontSettings = new FontSettings();
                String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
                System.out.println(jarPath);
                fontSettings.setFontsFolder(jarPath, false);
                doc.setFontSettings(fontSettings);
                doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param excelPath 需要被转换的excel全路径带文件名
     * @param pdfPath 转换之后pdf的全路径带文件名
     */
    public static void excel2pdf(String excelPath, String pdfPath) {
        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
            return;
        }
        try {
            long old = System.currentTimeMillis();
            Workbook wb = new Workbook(excelPath);// 原始excel路径
            FileOutputStream fileOS = new FileOutputStream(new File(pdfPath));
            wb.save(fileOS, com.aspose.cells.SaveFormat.PDF);
            fileOS.close();
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        String filepath = "C:\\Users\\Administrator\\Desktop\\work\\11111.docx";
//        String outpath = Class.class.getClass().getResource("/").getPath() + "\\111.pdf";
        String filepath = "C:\\Users\\Administrator\\Desktop\\work\\111789.xlsx";
        String outpath = "C:\\Users\\Administrator\\Desktop\\work\\111789.pdf";
//        System.out.println(Class.class.getClass().getResource("/").getPath());
//        InputStream source;
//        OutputStream target;
//        try {
//            source = new FileInputStream(filepath);
//            target = new FileOutputStream(outpath);
//            PdfOptions options = PdfOptions.create();
//            wordConverterToPdf(source, target, options);
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        docx2PDF(filepath, outpath);
        excel2pdf(filepath, outpath);
    }
}
