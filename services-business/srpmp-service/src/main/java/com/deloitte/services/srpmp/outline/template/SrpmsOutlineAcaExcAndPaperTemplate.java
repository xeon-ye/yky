package com.deloitte.services.srpmp.outline.template;

import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineAcaExcReportVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePaperReportVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineSatBookReportVo;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-28
 * @Description :  题录-论文与学术交流数量导出报表
 * @Modified :
 */
public class SrpmsOutlineAcaExcAndPaperTemplate {

    /**
     * 判断传入的excel文件后缀名
     *
     * @param filepath
     * @return
     */
    private Workbook getWorkbookTypeByFile(String filepath) {
        Workbook workbook = null;
        File file = new File(filepath);
        BufferedInputStream in = null;

        try {
            if (file.exists()) {
                in = new BufferedInputStream(new FileInputStream(file));
            }

            if (filepath.endsWith("xls")) {
                workbook = new HSSFWorkbook(in);
            } else if (filepath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * 写对应数据到报表模板中
     *
     * @param outputPath
     * @param voList
     * @throws IOException
     */
    public void writeExcel(
            String outputPath,
            SrpmsOutlineQueryParam queryParam,
            List<SrpmsOutlinePaperReportVo> paperReportVoList,
            List<SrpmsOutlineAcaExcReportVo> voList,
            List<SrpmsOutlineSatBookReportVo> bookReportVoList,
            Map<Long, String> deptAcaMap,
            Map<Long, String> deptPaperMap
    ) throws IOException {

        Workbook workbook = getWorkbookTypeByFile(outputPath);
        if (workbook == null) {
            return;
        }

        // 设置数据
        setReportModuleToExcelDatas(workbook, queryParam, paperReportVoList, voList, bookReportVoList, deptAcaMap, deptPaperMap);


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
    }

    /**
     * 设置数据
     *
     * @param workbook
     * @param voList
     */
    private void setReportModuleToExcelDatas(
            Workbook workbook,
            SrpmsOutlineQueryParam queryParam,
            List<SrpmsOutlinePaperReportVo> paperReportVoList,
            List<SrpmsOutlineAcaExcReportVo> voList,
            List<SrpmsOutlineSatBookReportVo> bookReportVoList,
            Map<Long, String> deptAcaMap,
            Map<Long, String> deptPaperMap
    ) {
        Sheet sheet = workbook.getSheetAt(0);


        // 年
        Name name = workbook.getName("YEAR");
        CellReference ref = new CellReference(name.getRefersToFormula());
        Row row = sheet.getRow(ref.getRow());
        Cell cell = row.getCell(ref.getCol());
        cell.setCellValue(queryParam.getYear() == null ? "" : queryParam.getYear());

        // 月
        name = workbook.getName("MONTH");
        ref = new CellReference(name.getRefersToFormula());
        row = sheet.getRow(ref.getRow());
        cell = row.getCell(ref.getCol());
        cell.setCellValue(queryParam.getMonth() == null ? "" : queryParam.getMonth());

        setReportCountData(paperReportVoList, voList, bookReportVoList, deptAcaMap, deptPaperMap, workbook, sheet, name, ref, row, cell);
    }

    /**
     * 数量统计报表写入excel数据
     *
     * @param voList
     * @param workbook
     * @param sheet
     * @param name
     * @param ref
     * @param row
     * @param cell
     */
    public void setReportCountData(
            List<SrpmsOutlinePaperReportVo> paperReportVoList,
            List<SrpmsOutlineAcaExcReportVo> voList,
            List<SrpmsOutlineSatBookReportVo> bookReportVoList,
            Map<Long, String> deptAcaMap,
            Map<Long, String> deptPaperMap,
            Workbook workbook,
            Sheet sheet,
            Name name,
            CellReference ref,
            Row row,
            Cell cell
    ) {

        Map<Long, String> orgMap = new HashMap<>();
        if (bookReportVoList != null && bookReportVoList.size() > 0) {
            SrpmsOutlineSatBookReportVo bookReportVo;
            StringBuilder stringBuilder = new StringBuilder();
            for (Iterator e = bookReportVoList.iterator(); e.hasNext(); ) {
                bookReportVo = (SrpmsOutlineSatBookReportVo) e.next();
                stringBuilder.append(bookReportVo.getChiefEditorCount());
                stringBuilder.append("-");
                stringBuilder.append(bookReportVo.getJoinBookCount());
                orgMap.put(bookReportVo.getOrgId(), stringBuilder.toString());
            }
        }

        int firstLastRow = 0;
        int secoendLastRow = 0;
        if (voList != null && voList.size() > 0) {
            SrpmsOutlineAcaExcReportVo reportVo;
            String orgName = "";
            for (int rowNum = 0; rowNum < voList.size(); rowNum++) {

                reportVo = voList.get(rowNum);
                if (deptAcaMap != null) {
                    orgName = deptAcaMap.get(reportVo.getOrgId());
                }

                // 单位
                name = workbook.getName("KJBA2");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    firstLastRow = ref.getRow();
                    if (rowNum > 0) {
                        row = sheet.createRow(ref.getRow() + rowNum);
                        cell = row.createCell(ref.getCol());
                    } else {
                        row = sheet.getRow(ref.getRow() + rowNum);
                        cell = row.getCell(ref.getCol());
                    }
                    CommonUtil.setBorder(cell);
                    cell.setCellValue(orgName);
                }

                name = workbook.getName("XSJL1");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getOutTeamHost());
                }
                name = workbook.getName("XSJL2");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getOutTeamJoin());
                }
                name = workbook.getName("XSJL3");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getInTeamHost());
                }
                name = workbook.getName("XSJL4");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getInTeamJoin());
                }

            }

        }

        if (paperReportVoList != null && paperReportVoList.size() > 0) {
            SrpmsOutlinePaperReportVo paperReportVo;
            int indexCount;
            int totalCount;
            int chiefEditorCount = 0;
            int joinBookCount = 0;
            String bookValue;
            String orgName = "";
            int insertRow = paperReportVoList.size();
            for (int rowNum = 0; rowNum < paperReportVoList.size(); rowNum++) {

                totalCount = 0;
                paperReportVo = paperReportVoList.get(rowNum);
                if (orgMap != null && orgMap.containsKey(paperReportVo.getOrgId())) {
                    bookValue = orgMap.get(paperReportVo.getOrgId());
                    chiefEditorCount = Integer.valueOf(bookValue.split("-")[0]);
                    joinBookCount = Integer.valueOf(bookValue.split("-")[1]);
                }

                if (deptPaperMap != null) {
                    orgName = deptPaperMap.get(paperReportVo.getOrgId());
                }

                // 单位
                name = workbook.getName("KJBA1");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    if (rowNum > 0) {
                        if(rowNum == 1) {
                            secoendLastRow = firstLastRow + ref.getRow();
                            sheet.shiftRows(ref.getRow() + rowNum, secoendLastRow + insertRow, insertRow, true, false);
                        }
                        row = sheet.createRow(ref.getRow() + rowNum);
                        cell = row.createCell(ref.getCol());
                    } else {
                        row = sheet.getRow(ref.getRow() + rowNum);
                        cell = row.getCell(ref.getCol());
                    }
                    CommonUtil.setBorder(cell);
                    cell.setCellValue(orgName);
                }

                name = workbook.getName("LWTJ1");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    indexCount = paperReportVo.getOutJournalCount();
                    totalCount += indexCount;
                    cell.setCellValue(indexCount);
                }
                name = workbook.getName("LWTJ2");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    indexCount = paperReportVo.getInJournalNationCount();
                    totalCount += indexCount;
                    cell.setCellValue(indexCount);
                }
                name = workbook.getName("LWTJ3");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    indexCount = paperReportVo.getInJournalCount();
                    totalCount += indexCount;
                    cell.setCellValue(indexCount);
                }
                name = workbook.getName("LWTJ4");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    totalCount += chiefEditorCount;
                    cell.setCellValue(chiefEditorCount);
                }
                name = workbook.getName("LWTJ5");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    totalCount += joinBookCount;
                    cell.setCellValue(joinBookCount);
                }

                name = workbook.getName("LWTJ6");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(totalCount);
                }
                name = workbook.getName("LWTJ7");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(paperReportVo.getOutSciFirstCount());
                }
                name = workbook.getName("LWTJ8");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(paperReportVo.getOutSciSecoendCount());
                }
                name = workbook.getName("LWTJ9");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(paperReportVo.getOutSciThreeCount());
                }
                name = workbook.getName("LWT10");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(paperReportVo.getOutSciFourCount());
                }

            }
        }

    }

}
