package com.deloitte.services.contract.common.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static void writeExcel(String filePath, String[] titles, List<String[]> list) throws Exception{
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("data");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//        int m = 0;
//        for (int length : widthLength) {
//            sheet.setColumnWidth(m, length);
//            m++;
//        }
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中

        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置加粗显示

        HSSFCellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());// 设置背景色
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        style.setFont(font);// 选择需要用到的字体格式
        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = row.createCell((short) i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(style);
        }
        // 表头行数
        int titleLine = 1;

        // HSSFFont cellFont = wb.createFont();
        // cellFont.setFontName("宋体");
        // cellFont.setFontHeightInPoints((short) 10);//设置字体大小
        //
        // HSSFCellStyle cellStyle = wb.createCellStyle();
        // cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        // cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        // cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        // cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        // List list = CreateSimpleExcelToDisk.getStudent();

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + titleLine);
            String[] results = list.get(i);
            for (int j = 0; j < results.length; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(results[j]);
                // cellStyle.setFont(cellFont);//选择需要用到的字体格式
                // cell.setCellStyle(cellStyle);
            }
        }
        // 第六步，将文件存到指定位置
        FileOutputStream fout = new FileOutputStream(filePath);
        wb.write(fout);
        fout.close();
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Administrator\\Desktop\\work\\测试.xls";
        String[] titles = {"测试1","测试2","测试3"};
        List<String[]> list = new ArrayList<>();
        String[] str = {"1","12","123"};
        list.add(str);
        try {
            writeExcel(filePath, titles, list);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

    }
}
