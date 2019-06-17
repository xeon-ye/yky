package com.deloitte.platform.basic.fileservice.Util;

import com.deloitte.platform.basic.fileservice.exception.FileErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author : jackliu
 * @Date : Create in 14:46 19/02/2019
 * @Description :
 * @Modified :
 */
public class FileUtils {

    private final static String[] strHex = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    //盐，用于混交md5
    private static final String slat = "&%5123***&&%%$$#@";
    /**
     * 生成md5
     * @param seckillId
     * @return
     */
    public static String getMD5(byte[] b) {
       // String base = b.toString() +"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(b);
        return md5;
    }



    public static String getMD5UseByte( byte[] b) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            for (int i = 0; i < b.length; i++) {
                int d = b[i];
                if (d < 0) {
                    d += 256;
                }
                int d1 = d / 16;
                int d2 = d % 16;
                sb.append(strHex[d1] + strHex[d2]);
            }
        } catch (Exception e) {
            throw new ServiceException(FileErrorType.FILE_TRANSACTION_MD5_ERROR,e.getMessage());
        }
        return sb.toString();
    }


    public static String getFileMD5UseFile(File f) {
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (Exception e) {
           throw new ServiceException(FileErrorType.FILE_TRANSACTION_MD5_ERROR,e.getMessage());
        }
        return bi.toString(16);
    }
}
