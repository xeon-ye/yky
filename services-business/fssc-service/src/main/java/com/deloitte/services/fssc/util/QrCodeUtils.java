package com.deloitte.services.fssc.util;

import com.deloitte.services.fssc.eums.FsscErrorType;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

/**
 * 二维码生成类
 */
@Slf4j
public class QrCodeUtils {

    public static void creatRrCode(String contents, int width, int height) {
//        String binary = null;
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    contents, BarcodeFormat.QR_CODE, width, height, hints);
            // 1、读取文件转换为字节数组
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BufferedImage image = toBufferedImage(bitMatrix);
            //转换成png格式的IO流
            boolean png = ImageIO.write(image, "png", FsscHttpUtils.currentResponse().getOutputStream());
            AssertUtils.asserts(png, FsscErrorType.ID_CANT_BE_NULL);
//            byte[] bytes = out.toByteArray();

//            // 2、将字节数组转为二进制
//            BASE64Encoder encoder = new BASE64Encoder();
//            binary = encoder.encodeBuffer(bytes).trim();
        } catch (WriterException e) {
            e.printStackTrace();
            log.error("生成二维码错误:{}",e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("生成二维码错误:{}",e.getMessage());
        }

    }

    /**
     * image流数据处理
     *
     * @author ianly
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }


}
