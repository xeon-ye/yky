package com.deloitte.services.fssc.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Excel导入工具类
 *
 * @author jawjiang
 */
@Slf4j
public class ExcelImportUtil {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 日期类型列名
     */
    static final String[] DATE_COLUMN_NAME_ARRAY = {"交易日期","起息日期"};

    /**
     * 数值类型列名
     */
    static final String[] NUMBER_COLUMN_NAME_ARRAY = {""};

    static Set<String> dateColumnNameSet = new HashSet<>(Arrays.asList(DATE_COLUMN_NAME_ARRAY));

    static Set<String> numberColumnNameSet = new HashSet<>(Arrays.asList(NUMBER_COLUMN_NAME_ARRAY));

    static DecimalFormat df = new DecimalFormat("0");

    private ExcelImportUtil() {

    }

    /**
     * 解析内容单个sheet的Excel,并且每列都是文本格式
     */
    public static ExcelResult getExcelDate(File excelFile) throws IOException {
        ExcelResult excelResult = new ExcelResult();
        List<Map<String, String>> tableData = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        try (Workbook wb = WorkbookFactory.create(new FileInputStream(excelFile))) {
            Sheet sheet = wb.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            Row firstRow = sheet.getRow(0);
            int columnCount = firstRow.getPhysicalNumberOfCells();
            log.info("行数: {},列数: {}", rowCount, columnCount);
            List<String> titleList = new ArrayList<>();
            for (int i = 0; i < columnCount; i++) {
                Cell cell1 = firstRow.getCell(i);
                titleList.add(cell1.getStringCellValue().replace("*", "")
                        .trim());
            }
            for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                HashMap<String, String> rowData = new HashMap<>();
                for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                    Cell cell = row.getCell(columnIndex);
                    if (cell == null) {
                        log.info("列:{},值:{}", titleList.get(columnIndex), "");
                        rowData.put(titleList.get(columnIndex), "");
                        continue;
                    }
                    if (dateColumnNameSet.contains(titleList.get(columnIndex))) {
                        String cellValue = getCellValue(dateFormat, cell);
                        rowData.put(titleList.get(columnIndex), cellValue.trim());
                    } else if (numberColumnNameSet.contains(titleList.get(columnIndex))) {
                        String cellValue = "";
                        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                            //去除逗号和科学计数法
                            cellValue = df.format(cell.getNumericCellValue());
                            cellValue = cellValue.trim().replace(",", "");
                        } else {
                            cell.setCellType(CellType.STRING);
                            cellValue = cell.getStringCellValue().trim().replace(",", "");
                        }
                        rowData.put(titleList.get(columnIndex), cellValue);
                    } else {
                        cell.setCellType(CellType.STRING);
                        String cellValue = cell.getStringCellValue() != null ? cell.getStringCellValue() : "";
                        rowData.put(titleList.get(columnIndex), cellValue.trim());
                    }
                }
                tableData.add(rowData);
            }
        } catch (Exception e) {
            log.error("导入失败: {}",e.getMessage());
            excelResult.addErrorMsg("导入失败,"+e.getMessage());
        }
        Files.delete(excelFile.toPath());
        if (tableData.isEmpty()) {
            excelResult.addErrorMsg("没有读取到有效数据");
        }
        excelResult.setTableData(tableData);
        return excelResult;
    }

    /**
     * 解析内容单个sheet的Excel
     */
    public static ExcelResult getExcelImportData(File excelFile) throws IOException {
        ExcelResult excelResult = new ExcelResult();
        List<Map<String, String>> tableData = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        try (Workbook wb = WorkbookFactory.create(new FileInputStream(excelFile))) {
            Sheet sheet = wb.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            Row firstRow = sheet.getRow(0);
            if (firstRow != null) {
                int columnCount = firstRow.getPhysicalNumberOfCells();
                log.info("行数: {},列数: {}", rowCount, columnCount);
                List<String> titleList = new ArrayList<>();
                for (int i = 0; i < columnCount; i++) {
                    Cell cell1 = firstRow.getCell(i);
                    titleList.add(cell1.getStringCellValue());
                }
                for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    HashMap<String, String> rowData = new HashMap<>();
                    for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                        Cell cell = row.getCell(columnIndex);
                        if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) {
                            excelResult.addErrorMsg(rowIndex,
                                    titleList.get(columnIndex), "不能获取单元格");
                            rowData.put(titleList.get(columnIndex), "");
                            continue;
                        }
                        String cellValue = "";
                        switch (cell.getCellTypeEnum()) {
                            case STRING:
                                // 文本
                                cellValue = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                // 数字、日期
                                if (org.apache.poi.ss.usermodel.DateUtil
                                        .isCellDateFormatted(cell)) {
                                    cellValue = fmt.format(cell.getDateCellValue());
                                } else {
                                    cellValue = String.valueOf(cell.getNumericCellValue());
                                }
                                break;
                            case BOOLEAN:
                                // 布尔型
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case BLANK:
                                // 空白
                                cellValue = cell.getStringCellValue();
                                excelResult.addErrorMsg(rowIndex, titleList.get(columnIndex), "不能为空");
                                break;
                            case ERROR:
                                // 错误
                                excelResult.addErrorMsg(rowIndex, titleList.get(columnIndex), "不支持类型");
                                cellValue = "错误";
                                break;
                            case FORMULA:
                                // 公式
                                excelResult.addErrorMsg(rowIndex, titleList.get(columnIndex), "不支持公式类型");
                                cellValue = "错误";
                                break;
                            default:
                                cellValue = "错误";
                        }
                        rowData.put(titleList.get(columnIndex), cellValue);
                    }
                    tableData.add(rowData);
                }
            } else {
                return excelResult;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        Files.delete(excelFile.toPath());
        excelResult.setTableData(tableData);
        if (tableData.isEmpty()) {
            excelResult.addErrorMsg("没有读取到有效数据");
        }
        return excelResult;
    }

    private static String getCellValue(SimpleDateFormat fmt, Cell cell) {
        String cellValue;
        try {
            if (DEFAULT_DATE_FORMAT.equals(cell.toString())) {
                cellValue = "";
            } else if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                if (cell.getDateCellValue() != null) {
                    // 日期型
                    cellValue = fmt.format(cell.getDateCellValue());
                } else {
                    cellValue = "";
                }
            } else {
                // 数字
                cellValue = String.valueOf(cell.getNumericCellValue());
            }
        } catch (Exception e) {
            cellValue = cell.getStringCellValue();
        }
        return cellValue;
    }

}
