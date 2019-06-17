package com.deloitte.services.contract.common.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;

public class PdfEncryptUtil {

    public static void encryptPdf(String userPassword,String sourcePath,String tagetPath) throws Exception{
        PdfReader reader = new PdfReader(sourcePath);// 待加水印的文件
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(new File(tagetPath)));// 加完水印的文件
        byte[] ownerPassword = "system".getBytes();
        stamper.setEncryption(userPassword.getBytes(), ownerPassword, PdfWriter.ALLOW_ASSEMBLY, false);
        stamper.setEncryption(userPassword.getBytes(), ownerPassword, PdfWriter.ALLOW_COPY, false);
        stamper.setEncryption(userPassword.getBytes(), ownerPassword, PdfWriter.ALLOW_DEGRADED_PRINTING, false);
        stamper.setEncryption(userPassword.getBytes(), ownerPassword, PdfWriter.ALLOW_FILL_IN, false);
        stamper.setEncryption(userPassword.getBytes(), ownerPassword, PdfWriter.ALLOW_MODIFY_ANNOTATIONS, false);
        stamper.setEncryption(userPassword.getBytes(), ownerPassword, PdfWriter.ALLOW_MODIFY_CONTENTS, false);
        stamper.setEncryption(userPassword.getBytes(), ownerPassword, PdfWriter.ALLOW_PRINTING, false);
        stamper.setEncryption(userPassword.getBytes(), ownerPassword, PdfWriter.ALLOW_SCREENREADERS, false);
        stamper.setEncryption(userPassword.getBytes(), ownerPassword, PdfWriter.DO_NOT_ENCRYPT_METADATA, false);
        stamper.setViewerPreferences(PdfWriter.HideToolbar|PdfWriter.HideMenubar);
        stamper.close();
    }
    public static void main(String[] args) {
        try {
            encryptPdf("zhengchun","D:\\项目文档\\test.pdf","D:\\项目文档\\test3.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}