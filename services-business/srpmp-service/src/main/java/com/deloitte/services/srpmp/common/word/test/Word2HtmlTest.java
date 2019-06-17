package com.deloitte.services.srpmp.common.word.test;

import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by lixin on 01/03/2019.
 */
public class Word2HtmlTest {

    public static void main(String[] args) throws IOException {
        XWPFDocument document = new XWPFDocument(new FileInputStream("C:\\word\\real\\先导专项申报书-杨啸林.docx"));


        XHTMLOptions options = XHTMLOptions.create();
        options.setExtractor(new FileImageExtractor(new File("C:\\word\\real\\image")));
        options.URIResolver(new BasicURIResolver("image"));

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("C:\\word\\real\\aa.html"));
        XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
        xhtmlConverter.convert(document, out, options);
        out.close();
    }




}
