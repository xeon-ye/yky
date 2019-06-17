package com.deloitte.services.srpmp.common.word.test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.commons.lang3.CharSet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by lixin on 12/03/2019.
 */
public class HtmlToPdfTest {

    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\word\\real\\out.pdf"));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("C:\\word\\real\\a2.html"), Charset.forName("UTF-8"));
        document.close();
    }

}
