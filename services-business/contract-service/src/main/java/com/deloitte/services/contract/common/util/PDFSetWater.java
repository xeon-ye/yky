package com.deloitte.services.contract.common.util;

import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import java.io.FileOutputStream;

public class PDFSetWater {

    public static void waterMark(String inputFile,
                                 String outputFile, String waterMarkName) {
        try {
            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
                    outputFile));

            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",   BaseFont.EMBEDDED);

            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.3f);
            gs.setStrokeOpacity(0.4f);
            int total = reader.getNumberOfPages() + 1;

            JLabel label = new JLabel();
            label.setText(waterMarkName);

            PdfContentByte under;
            for (int i = 1; i < total; i++) {
                under = stamper.getOverContent(i);
                under.saveState();
                under.setGState(gs);
                under.beginText();
                under.setFontAndSize(base, 120);

                // 水印文字成30度角倾斜
                under.showTextAligned(Element.ALIGN_LEFT
                        , waterMarkName, 200,
                        180, 55);
                // 添加水印文字
                under.endText();
            }
            //说三遍
            //一定不要忘记关闭流
            //一定不要忘记关闭流
            //一定不要忘记关闭流
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
