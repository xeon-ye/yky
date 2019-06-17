package com.deloitte.services.srpmp.common.word.test;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.docx4j.wml.RFonts;

import java.io.File;

/**
 * Created by lixin on 02/03/2019.
 */
public class HTMLToDocx {

    public static void main(String[] args){
//        String inputfilepath = System.getProperty("user.dir") + "/somedir/some.html";
////        String baseURL = "file:///C:/Users/jharrop/git/docx4j-ImportXHTML/somedir/";
//        String baseURL = "file:///C:/Users/jharrop/git/docx4j-ImportXHTML/";
//
//        String stringFromFile = FileUtils.readFileToString(new File(inputfilepath), "UTF-8");
//
//        String unescaped = stringFromFile;
//        if (stringFromFile.contains("&lt;/") ) {
//    		unescaped = StringEscapeUtils.unescapeHtml(stringFromFile);
//        }


//        XHTMLImporter.setTableFormatting(FormattingOption.IGNORE_CLASS);
//        XHTMLImporter.setParagraphFormatting(FormattingOption.IGNORE_CLASS);

//        System.out.println("Unescaped: " + unescaped);

//
//        // Setup font mapping
//        RFonts rfonts = Context.getWmlObjectFactory().createRFonts();
//        rfonts.setAscii("Century Gothic");
//        XHTMLImporterImpl.addFontMapping("Century Gothic", rfonts);
//
//        // Create an empty docx package
//        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
////		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(System.getProperty("user.dir") + "/styled.docx"));
//
//
//        NumberingDefinitionsPart ndp = new NumberingDefinitionsPart();
//        wordMLPackage.getMainDocumentPart().addTargetPart(ndp);
//        ndp.unmarshalDefaultNumbering();
//
//        // Convert the XHTML, and add it into the empty docx we made
//        XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
//
//        XHTMLImporter.setHyperlinkStyle("Hyperlink");
//        wordMLPackage.getMainDocumentPart().getContent().addAll(
//                XHTMLImporter.convert(unescaped, baseURL) );
//
//        System.out.println(
//                XmlUtils.marshaltoString(wordMLPackage.getMainDocumentPart().getJaxbElement(), true, true));
//
////		System.out.println(
////				XmlUtils.marshaltoString(wordMLPackage.getMainDocumentPart().getNumberingDefinitionsPart().getJaxbElement(), true, true));
//
//        wordMLPackage.save(new java.io.File(System.getProperty("user.dir") + "/OUT_from_XHTML.docx") );


    }

}
