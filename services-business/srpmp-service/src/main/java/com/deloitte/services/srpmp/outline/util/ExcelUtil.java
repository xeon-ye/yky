package com.deloitte.services.srpmp.outline.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private static Logger logger = Logger.getLogger(ExcelUtil.class);
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    /**
     * 读入excel文件，解析后返回
     *
     * @param file
     * @throws IOException
     */
    public static List<String[]> readExcel(MultipartFile file) throws IOException {
        //检查文件
        checkFile(file);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if (workbook != null) {
            //获得当前sheet工作表
            Sheet sheet = workbook.getSheetAt(0);
            //获得当前sheet的开始行
            int firstRowNum = sheet.getFirstRowNum();
            //获得当前sheet的结束行
            int lastRowNum = sheet.getLastRowNum();
            int lastCellNum = 0;
            for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                //获得当前行
                Row row = sheet.getRow(rowNum);
                if(row == null) {
                    continue;
                }
                if (rowNum == 0) {
                    lastCellNum = row.getPhysicalNumberOfCells();
                    continue;
                }
                //获得当前行的开始列
                int firstCellNum = row.getFirstCellNum();
                if(firstCellNum > 0) {
                    break;
                }
                //获得当前行的列数
                String[] cells = new String[lastCellNum];
                //循环当前行
                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    cells[cellNum] = getCellValue(cell);
                }
                list.add(cells);
            }
            workbook.close();
        }
        return list;
    }

    public static void checkFile(MultipartFile file) throws IOException {
        //判断文件是否存在
        if (null == file) {
            logger.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            logger.error(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }
    }

    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(xls)) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(xlsx)) {
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字与日期
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(cell.getDateCellValue());
                } else {
                    //把数字当成String来读，避免出现1读成1.0的情况
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cellValue = String.valueOf(cell.getStringCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    public static void generateSelect(Sheet sheet, Object object, int rowNum, int cn) {
        if (object != null && object instanceof String[]) {
            String[] textlist = (String[]) object;
            // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
            CellRangeAddressList regions = new CellRangeAddressList(rowNum,rowNum, cn, cn);

            if (sheet instanceof HSSFSheet) {
                DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);

                // 数据有效性对象
                HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);
                data_validation_list.setShowErrorBox(true);
                sheet.addValidationData(data_validation_list);
            } else if (sheet instanceof XSSFSheet) {
                if (textlist.length > 0) {
                    XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
                    XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                            .createExplicitListConstraint(textlist);
                    XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(
                            dvConstraint, regions);
                    validation.setShowErrorBox(true);
                    sheet.addValidationData(validation);
                }
            } else {
                return;
            }
        }

    }

    public static void generateSelectCategory(HSSFWorkbook wb, Sheet sheet, String[] select, int index, int cn) {
        int cellNum = cn;
        int startRow = 1; // 开始行
        int endRow = select.length; // 结束行
        String hiddenSheet = "categoryHidden";
        if(index == 0) {
            HSSFSheet categoryHidden = wb.createSheet(hiddenSheet); // 创建隐藏域
            for (int m = 0, length = select.length; m < length; m++) { // 循环赋值（为了防止下拉框的行数与隐藏域的行数相对应来获取>=选中行数的数组，将隐藏域加到结束行之后）
                categoryHidden.createRow(endRow + m).createCell(cellNum).setCellValue(select[m]);
            }
            Name categoryName = wb.createName();
            categoryName.setNameName(hiddenSheet);
            categoryName.setRefersToFormula(hiddenSheet + "!A1:A" + (select.length + endRow)); // A1:A代表隐藏域创建第?列createCell(?)时。以A1列开始A行数据获取下拉数组
        }

        DVConstraint constraint = DVConstraint.createFormulaListConstraint(hiddenSheet);
        CellRangeAddressList addressList = new CellRangeAddressList(startRow, endRow, cellNum, cellNum);
        HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
        wb.setSheetHidden(1, true); // 1隐藏、0显示
        sheet.addValidationData(validation);

    }


    public static void exportExcel(String fileName, String sheetName, String[] title, String[][] values, HttpServletRequest request, HttpServletResponse response, Map<Integer, String[]> mapSelect) throws IOException {
        OutputStream os = null;
        try {
            HSSFWorkbook wb = getHSSFWorkbook(sheetName, title, values, null, mapSelect);

            String agent = request.getHeader("USER-AGENT").toLowerCase();
            response.setContentType("application/vnd.ms-excel");
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            response.setCharacterEncoding("utf-8");
            if (StringUtils.isNotEmpty(agent) && agent.contains("firefox")) {
                response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
            } else {
                response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
            }

            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            os = response.getOutputStream();
            wb.write(os);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
    }
    public static void exportExcelCategory(String fileName, String sheetName, String[] title, String[][] values, HttpServletRequest request, HttpServletResponse response, Map<Integer, String[]> mapSelect) throws IOException {
        OutputStream os = null;
        try {
            HSSFWorkbook wb = getHSSFWorkbookCategory(sheetName, title, values, null, mapSelect);

            String agent = request.getHeader("USER-AGENT").toLowerCase();
            response.setContentType("application/vnd.ms-excel");
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            response.setCharacterEncoding("utf-8");
            if (StringUtils.isNotEmpty(agent) && agent.contains("firefox")) {
                response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));
            } else {
                response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
            }

            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            os = response.getOutputStream();
            wb.write(os);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
    }

    public static HSSFWorkbook getHSSFWorkbookCategory(String sheetName, String[] title, String[][] values, HSSFWorkbook wb, Map<Integer, String[]> mapSelect) {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //设置字体
        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 10);//设置字体大小
        style.setFont(font);
        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for (int i = 0; i < title.length; i++) {
            style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND );
            style.setFillForegroundColor(HSSFColor.YELLOW.index);// 设置背景色
            cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(title[i]);
        }

        //创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
                String[] select = mapSelect.get(j);
                if(select != null) {
                    if(j == 5) {
                        generateSelectCategory(wb, sheet, select, i, j);
                    } else {
                        generateSelect(sheet, select, i + 1, j);
                    }
                }
                sheet.autoSizeColumn(j);
            }
        }
        return wb;
    }
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb, Map<Integer, String[]> mapSelect) {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //设置字体
        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 10);//设置字体大小
        style.setFont(font);
        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for (int i = 0; i < title.length; i++) {
            style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND );
            style.setFillForegroundColor(HSSFColor.YELLOW.index);// 设置背景色
            cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(title[i]);
        }

        //创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
                String[] select = mapSelect.get(j);
                if(select != null) {
                    generateSelect(sheet, select, i + 1, j);
                }
                sheet.autoSizeColumn(j);
            }
        }
        return wb;
    }
}
