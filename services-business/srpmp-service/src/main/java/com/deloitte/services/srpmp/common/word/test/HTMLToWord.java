package com.deloitte.services.srpmp.common.word.test;


import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by lixin on 02/03/2019.
 * doc
 */
public class HTMLToWord{

    public static void main(String[] args) throws Exception {
        String content = "<html>" +
                "<head>你好</head>" +
                "<body>" +
                "<table>" +
                "<tr>" +
                "<td>信息1</td>" +
                "<td>信息2</td>" +
                "<td>t3</td>" +
                "<tr>" +
                "</table>" +
                "</body>" +
                "</html>";
        byte b[] = content.getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        POIFSFileSystem poifs = new POIFSFileSystem();
        DirectoryEntry directory = poifs.getRoot();
        directory.createDocument("WordDocument",new FileInputStream("C:\\word\\real\\aa.html"));
        FileOutputStream ostream = new FileOutputStream("C:\\word\\real\\bbb.doc");
        poifs.writeFilesystem(ostream);
        bais.close();
        ostream.close();
    }

}
