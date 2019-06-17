
package com.deloitte.services.srpmp.common.word.test;

import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.POIXMLDocument;
//import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFPicture;
//import org.apache.poi.xwpf.usermodel.XWPFPictureData;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//import org.apache.poi.xwpf.usermodel.XWPFTable;
//import org.apache.poi.xwpf.usermodel.XWPFTableCell;
//import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixin on 28/02/2019.
 */
@Slf4j
public class PoiWordParseTest {


    public static void main(String[] args) throws IOException, OpenXML4JException, XmlException {
        /*OPCPackage opcPackage = POIXMLDocument.openPackage("C:\\word\\test.docx");
        XWPFDocument xwpfDocument = new XWPFDocument(opcPackage);
        //读取段落
        List<XWPFParagraph> paragraphList = xwpfDocument.getParagraphs();
        for (XWPFParagraph p : paragraphList) {
            List<XWPFRun> runList = p.getRuns();
            for (XWPFRun run : runList) {
                String runText = run.text();
                System.out.println(runText);
            }
        }

        //读取表格
        List<XWPFTable> tableList = xwpfDocument.getTables();
        {
            XWPFTable testTable = tableList.get(1);
            List<XWPFTableRow> rowList = testTable.getRows();
            for (int i = 0; i < rowList.size(); i++) {
                XWPFTableRow row = rowList.get(i);
                List<XWPFTableCell> cellList = row.getTableCells();
                for (int j = 0; j < cellList.size(); j++) {
                    XWPFTableCell cell = cellList.get(j);
                    System.out.println(String.format("第%s行，第%s列，内容:%s", i, j, cell.getText()));
                }
            }
        }


        System.out.println("===========================");
        XWPFTable personTable = tableList.get(1);
        {
            List<XWPFTableRow> rowList = personTable.getRows();
            List<Person> personList = new ArrayList<>();
            for (int i = 1; i < rowList.size(); i++) {
                XWPFTableRow row = rowList.get(i);
                List<XWPFTableCell> cellList = row.getTableCells();
                Person p = new Person();
                String name = cellList.get(1).getText();
                String gender = cellList.get(2).getText();
                p.setName(name);
                p.setGender(gender);
                personList.add(p);
            }

            for (Person p: personList){
                System.out.println(p);
            }
        }

        System.out.println("=====================================");
        {
            XWPFTable testTable = tableList.get(2);
            List<XWPFTableRow> rowList = testTable.getRows();
            for (int i = 0; i < rowList.size(); i++) {
                XWPFTableRow row = rowList.get(i);
                List<XWPFTableCell> cellList = row.getTableCells();
                for (int j = 0; j < cellList.size(); j++) {
                    XWPFTableCell cell = cellList.get(j);
                    System.out.println(String.format("第%s行，第%s列，内容:%s", i, j, cell.getText()));
                }
            }
        }*/

        OPCPackage opcPackage = POIXMLDocument.openPackage("C:\\word\\real\\先导专项申报书-杨啸林.docx");
        XWPFDocument xwpfDocument = new XWPFDocument(opcPackage);

        List<XWPFPictureData> pictureList = xwpfDocument.getAllPictures();
        for (XWPFPictureData pictureData: pictureList){
            System.out.println(pictureData.getFileName());
        }


        List<XWPFTable> tableList = xwpfDocument.getTables();
        int tableIndex = 0;
        for (XWPFTable table: tableList) {
            log.info("tableIndex:" + tableIndex);
            List<XWPFTableRow> rowList = table.getRows();
            for (int i = 0; i < rowList.size(); i++) {
                XWPFTableRow row = rowList.get(i);
                List<XWPFTableCell> cellList = row.getTableCells();
                for (int j = 0; j < cellList.size(); j++) {
                    XWPFTableCell cell = cellList.get(j);
                    System.out.println(String.format("第%s行，第%s列，内容:%s", i, j, cell.getText()));

                }
            }
        }

    }


}
class Person{

    private String name;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.name + " : "  + this.gender;
    }
}