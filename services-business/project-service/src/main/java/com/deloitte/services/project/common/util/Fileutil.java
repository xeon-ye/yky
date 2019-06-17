package com.deloitte.services.project.common.util;

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
}
