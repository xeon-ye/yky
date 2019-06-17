package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaTheoTechProTableForm;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author:LIJUN
 * Date:28/03/2019
 * Description:
 */
@Service
@Slf4j
public class ExcelExportImpl {

    /**
     * 复制文件
     * @param templateName 文件名（不含后缀）
     * @return
     */
    public String copyTemplateFile(String templateName) {
        StringBuilder templateFile = new StringBuilder("excel/template/");
        templateFile.append(templateName);
        templateFile.append(SrpmsConstant.EXT_EXCEL_XLSX);

        StringBuilder newFilePath = new StringBuilder();
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        //新的文件路径
        try {
            File workPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile();
            newFilePath.append(workPath);
            newFilePath.append(SrpmsConstant.SEPARATOR);
            newFilePath.append(SrpmsConstant.EXCEL);
            newFilePath.append(SrpmsConstant.SEPARATOR);

            File file = new File(newFilePath.toString());
            // 创建目录
            if (!file.exists()) {
                file.mkdirs();
            }
            inputStream = this.getClass().getClassLoader().getResourceAsStream(templateFile.toString());
            if (inputStream != null) {
                newFilePath.append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
                newFilePath.append(RandomUtils.nextInt(0, 9999));
                newFilePath.append(SrpmsConstant.EXT_EXCEL_XLSX);

                outputStream = new FileOutputStream(newFilePath.toString());

                // 实现文件的复制
                byte[] b = new byte[1024];
                int len;
                while ((len = inputStream.read(b)) != -1) {
                    outputStream.write(b, 0, len);
                }
            }

        } catch (Exception e) {
            log.error("生成Excel文件异常", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("关闭流异常", e);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("关闭流异常", e);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
        }
        return newFilePath.toString();
    }

    /**
     * 批量写数据
     * @param workbook
     * @param keys
     * @param dataMap
     */
    public void writeData(Workbook workbook, List<String> keys, Map<String, Object> dataMap) {
        if (CollectionUtils.isNotEmpty(keys)) {
            for (String key : keys) {
                writeSingleData(workbook, key, MapUtils.getString(dataMap, key));
            }
        }
    }

    /**
     * 根据Key写数据
     * @param workbook
     * @param key
     * @param data
     */
    public void writeSingleData(Workbook workbook, String key, String data) {
        Name name = workbook.getName(key);
        CellReference ref = new CellReference(name.getRefersToFormula());
        Sheet sheet = workbook.getSheet(ref.getSheetName());
        Row row = sheet.getRow(ref.getRow());
        Cell cell = row.getCell(ref.getCol());
        cell.setCellValue(data);
    }

    public int insertBlankRow(Workbook workbook, int insertRowSize, String beginTag) {
        Name name = workbook.getName(beginTag);
        CellReference ref = new CellReference(name.getRefersToFormula());
        Sheet sheet = workbook.getSheet(ref.getSheetName());
        Row rowSource = sheet.getRow(ref.getRow());
        //获取当前行样式
        CellStyle rowStyle = rowSource.getRowStyle();
        int startRow = rowSource.getRowNum();
        if (insertRowSize == 0) {
            return startRow;
        }
        sheet.shiftRows(startRow, sheet.getLastRowNum(), insertRowSize, true, false);

        //复制样式
        for (int i = startRow; i < startRow + insertRowSize; i++) {
            Row rowInsert = sheet.createRow(i);
            if (rowStyle != null) {
                rowInsert.setRowStyle(rowStyle);
                rowInsert.setHeight(rowSource.getHeight());
            }
            for (int col = 0; col < rowSource.getLastCellNum(); col++) {
                Cell cellSource = rowSource.getCell(col);
                Cell cellInsert = rowInsert.createCell(col);
                if (null != cellSource) {
                    CellStyle cellStyle = cellSource.getCellStyle();
                    //设置单元格样式　　　　
                    if (cellStyle != null) {
                        cellInsert.setCellStyle(cellStyle);
                    }
                }

            }

        }
        return startRow;
    }

    public static void main(String[] args) {
        ExcelExportImpl excelExport = new ExcelExportImpl();
        String outputPath = excelExport.copyTemplateFile("template_mpr_annex_one");
        try {
            File file = new File(outputPath);
            Workbook workbook = new XSSFWorkbook(new BufferedInputStream(new FileInputStream(file)));
            Sheet sheet = workbook.getSheetAt(0);

            List<MprEvaTheoTechProTableForm> theoTechProTableFormList = Lists.newArrayList();
            MprEvaTheoTechProTableForm proTableForm = new MprEvaTheoTechProTableForm();
            proTableForm.setOutputName("1");
            proTableForm.setOutputType("11");
            proTableForm.setBringMean("重大意义");
            MprEvaTheoTechProTableForm proTableForm2 = new MprEvaTheoTechProTableForm();
            proTableForm2.setOutputName("2");
            proTableForm2.setOutputType("22");
            proTableForm2.setBringMean("丢丢重要");

            theoTechProTableFormList.add(proTableForm);
            theoTechProTableFormList.add(proTableForm2);

            if (CollectionUtils.isNotEmpty(theoTechProTableFormList)) {
                int startRow = excelExport.insertBlankRow(workbook, theoTechProTableFormList.size(), "TheoTech");

                for (int i = 0; i < theoTechProTableFormList.size(); i++) {
                    Row insertRow = sheet.getRow(startRow + i);
                    //产出类型
                    Cell insertCell = insertRow.getCell(2);
                    insertCell.setCellValue(theoTechProTableFormList.get(i).getOutputType());
                    CellRangeAddress region = new CellRangeAddress(startRow + i, startRow + i, 2, 4);
                    //产出名称
                    Cell insertCell1 = insertRow.getCell(5);
                    insertCell1.setCellValue(theoTechProTableFormList.get(i).getOutputName());
                    CellRangeAddress region1 = new CellRangeAddress(startRow + i, startRow + i, 5, 8);
                    //产生意义
                    Cell insertCell2 = insertRow.getCell(9);
                    insertCell2.setCellValue(theoTechProTableFormList.get(i).getBringMean());
                    CellRangeAddress region2 = new CellRangeAddress(startRow + i, startRow + i, 9, 12);

                    // 合并单元格
                    sheet.addMergedRegion(region);
                    sheet.addMergedRegion(region1);
                    sheet.addMergedRegion(region2);
                }
            }

            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(outputPath);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            } catch (Exception e) {
                workbook.close();
                if (outputStream != null) {
                    outputStream.close();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
