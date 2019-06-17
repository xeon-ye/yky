package com.deloitte.services.srpmp.common.util;

import com.deloitte.platform.common.core.util.UUIDUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.assertj.core.util.Lists;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by lixin on 10/04/2019.
 */
@Slf4j
public class PDFUtils {

    public static final List<String> IMAGE_SUFFIX = Lists.newArrayList("JPG", "JPEG", "PNG", "WEBP", "JPEG");

    private static boolean isImage(String fileName){
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (IMAGE_SUFFIX.contains(fileSuffix.toUpperCase())){
            return true;
        }
        return false;
    }

    private static boolean isPdf(String fileName){
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("PDF".equals(fileSuffix.toUpperCase())){
            return true;
        }
        return false;
    }



    public static void mergePdf(List<String> beMergeFileNames, String targetFilename) throws Exception {
        PdfReader reader = null;
        Document document = new Document();
        PdfCopy pdfCopy = new PdfCopy(document, new FileOutputStream(targetFilename)); //创建一个新文件
        int pageCount = 0;
        document.open();
        for (int i = 0; i < beMergeFileNames.size(); ++i) {
            reader = new PdfReader(beMergeFileNames.get(i));
            pageCount = reader.getNumberOfPages();
            for (int j = 1; j <= pageCount; ++j) {
                pdfCopy.addPage(pdfCopy.getImportedPage(reader, j));
            }
        }
        document.close();
    }


    public static void mergePdfSupportImage(List<String> beMergeFileNames, String targetFilename) throws Exception {
        PdfReader reader = null;
        Document document = new Document();
        PdfCopy pdfCopy = new PdfCopy(document, new FileOutputStream(targetFilename)); //创建一个新文件
        int pageCount = 0;
        document.open();
        for (int i = 0; i < beMergeFileNames.size(); ++i) {
            String fileName = beMergeFileNames.get(i);
            if (!isImage(fileName) && !isPdf(fileName)){
                break; //既不是图片又不是PDF文件，跳过
            }
            if (isImage(fileName)){
                fileName = imgToPdf(fileName); //如果是图片，转PDF
            }
            reader = new PdfReader(fileName);
            pageCount = reader.getNumberOfPages();
            for (int j = 1; j <= pageCount; ++j) {
                pdfCopy.addPage(pdfCopy.getImportedPage(reader, j));
            }
        }
        document.close();
    }

    public static String imgToPdf(String imgFileUrl) {
        File pdfFile = null;
        String pdfFileUrl = null;
        String pdfFilePath=null;
        Document document = new Document();
        FileOutputStream fos = null;
        try {
            String jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsolutePath();
            pdfFilePath = jarPath+ "/word/pdf/"+ UUIDUtil.getRandom32PK() +".pdf";
            File parentFile = new File(pdfFilePath).getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }
            pdfFile = new File(pdfFilePath);
            log.info("img to pdf:" + pdfFilePath);
            fos = new FileOutputStream(pdfFilePath);
            PdfWriter.getInstance(document, fos);
            document.addAuthor("");
            document.addSubject("");
            document.setPageSize(PageSize.A4);
            document.open();
            Image image = Image.getInstance(imgFileUrl);
            float imageHeight = image.getScaledHeight();
            float imageWidth = image.getScaledWidth();
            int i = 0;
            while (imageHeight > 500 || imageWidth > 500) {
                image.scalePercent(100 - i);
                i++;
                imageHeight = image.getScaledHeight();
                imageWidth = image.getScaledWidth();
            }
            image.setAlignment(Image.ALIGN_CENTER);
            document.add(image);
            document.close();
        } catch (DocumentException de) {
            log.error("img to pdf error.", de);
        } catch (IOException ioe) {
            log.error("img to pdf error.", ioe);
        } finally {
            IOUtils.closeQuietly(fos);
//            if (pdfFile != null && pdfFile.exists()){
//                pdfFile.delete();
//            }
        }
        return pdfFilePath;
    }

    public static void main(String[] args) throws Exception {
        //PDF合并


        //图片转PDF文件
//        String picStr = "http://www.51itstudy.com/web/UploadFiles/Document/201206/21/20120621195513532437.jpg";
//        String pdfFilePath = "C:\\word\\real\\tabletest\\p4.pdf";
//        imgToPdf(picStr, pdfFilePath);


        System.out.println('&');

        System.out.println(StringEscapeUtils.unescapeHtml4("information analysis, research evaluation, S &amp; T strategy research"));
        System.out.println(StringEscapeUtils.escapeHtml4("&"));
    }


}
