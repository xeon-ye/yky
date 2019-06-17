package com.deloitte.services.contract.common.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Fileutil {

    /**
     * 从网络Url中获取流
     *
     * @param urlStr
     * @throws IOException
     */
    public static InputStream getInputStreamFromUrl(String urlStr) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        // 防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        // 得到输入流
        return conn.getInputStream();
    }

    public static void main(String[] org){

        String urlStr = "http://124.17.100.241:8888/group1/M00/00/74/fBFk8VzUGXyALzicAABPk7jHkaU61.xlsx";
//        String urlStr = "http://124.17.100.241:8888/group1/M00/00/3A/fBFk8Vy69jGADsMrAB9AgqxbahE291.pdf";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//            InputStream inputStream = getInputStreamFromUrl(url);
            InputStream inputStream = conn.getInputStream();
            File file = new File("C:\\Users\\Administrator\\Desktop\\work", "【合同管理平台】测试团队任务计划表.xlsx");
            OutputStream os = new FileOutputStream(file);
            int byteCount = 0;
            byte[] data = new byte[1024];
            while ((byteCount = inputStream.read(data)) != -1) {
                os.write(data, 0, byteCount);
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
