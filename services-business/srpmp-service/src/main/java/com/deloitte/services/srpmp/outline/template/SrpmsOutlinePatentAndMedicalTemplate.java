package com.deloitte.services.srpmp.outline.template;

import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineMedicalReportVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePatentReportVo;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-28
 * @Description :  题录-奖新药器械数量导出报表
 * @Modified :
 */
public class SrpmsOutlinePatentAndMedicalTemplate {

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
            List<SrpmsOutlinePatentReportVo> patentReportVoList,
            List<SrpmsOutlineMedicalReportVo> voList,
            Map<Long, String> deptPatentMap,
            Map<Long, String> deptAwardMap
    ) throws IOException {

        Workbook workbook = getWorkbookTypeByFile(outputPath);
        if (workbook == null) {
            return;
        }

        // 设置数据
        setReportModuleToExcelDatas(workbook, queryParam, patentReportVoList, voList, deptPatentMap, deptAwardMap);


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
            List<SrpmsOutlinePatentReportVo> patentReportVoList,
            List<SrpmsOutlineMedicalReportVo> voList,
            Map<Long, String> deptPatentMap,
            Map<Long, String> deptAwardMap
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

        setReportCountData(patentReportVoList, voList, deptPatentMap, deptAwardMap, workbook, sheet, name, ref, row, cell);
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
            List<SrpmsOutlinePatentReportVo> patentReportVoList,
            List<SrpmsOutlineMedicalReportVo> voList,
            Map<Long, String> deptPatentMap,
            Map<Long, String> deptAwardMap,
            Workbook workbook,
            Sheet sheet,
            Name name,
            CellReference ref,
            Row row,
            Cell cell
    ) {

        SrpmsOutlinePatentReportVo patentReportVo;
        SrpmsOutlineMedicalReportVo reportVo;

        int firstLastRow = 0;
        int secoendLastRow = 0;
        int insertRow;
        // 新药器械报表
        if (voList != null && voList.size() > 0) {
            String orgName = "";
            insertRow = voList.size();
            for (int rowNum = 0; rowNum < voList.size(); rowNum++) {

                reportVo = voList.get(rowNum);

                if (deptAwardMap != null) {
                    orgName = deptAwardMap.get(reportVo.getOrgId());
                }
                /**
                 * 医疗器械
                 */
                // 单位
                name = workbook.getName("KJBA3");
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
                // 医疗器械一类
                name = workbook.getName("YLQX2");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getQFirstCount());
                }
                // 医疗器械二类
                name = workbook.getName("YLQX3");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getQSecondCount());
                }
                // 医疗器械三类
                name = workbook.getName("YLQX4");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getQThreeCount());
                }
                // 医疗器械四类
                name = workbook.getName("YLQX5");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getQFourCount());
                }

                // 医疗器械总数
                name = workbook.getName("YLQX1");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getTotalMedicalCount());
                }

                // 单位
                name = workbook.getName("KJBA2");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    if (rowNum > 0) {
                        if(rowNum == 1) {
                            secoendLastRow = ref.getRow();
                            sheet.shiftRows(ref.getRow() + rowNum, firstLastRow + ref.getRow() + insertRow, insertRow, true, false);
                        }
                        row = sheet.createRow(ref.getRow() + rowNum);
                        cell = row.createCell(ref.getCol());
                    } else {
                        row = sheet.getRow(ref.getRow() + rowNum);
                        cell = row.getCell(ref.getCol());
                    }
                    cell.setCellValue(orgName);
                }

                /**
                 * 新药证书统计
                 */
                // 西药一类
                name = workbook.getName("XYZS2");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getWFirstCount());
                }
                // 西药二类
                name = workbook.getName("XYZS3");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getWSecondCount());
                }
                // 西药三类
                name = workbook.getName("XYZS4");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getWThreeCount());
                }
                // 西药四类
                name = workbook.getName("XYZS5");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getWFourCount());
                }
                // 中药一类
                name = workbook.getName("XYZS6");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getZhFirstCount());
                }
                // 中药二类
                name = workbook.getName("XYZS7");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getZhSecondCount());
                }
                // 中药三类
                name = workbook.getName("XYZS8");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getZhThreeCount());
                }
                // 中药四类
                name = workbook.getName("XYZS9");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getZhFourCount());
                }
                // 中药五类
                name = workbook.getName("XYZS10");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getZhFiveCount());
                }

                // 生物制剂一类
                name = workbook.getName("XYZS11");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getSFirstCount());
                }
                // 生物制剂二类
                name = workbook.getName("XYZS12");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getSSecondCount());
                }
                // 生物制剂三类
                name = workbook.getName("XYZS13");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getSThreeCount());
                }
                // 生物制剂四类
                name = workbook.getName("XYZS14");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getSFourCount());
                }

                // 新药证书总数
                name = workbook.getName("XYZS1");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(reportVo.getTotalNewCount());
                }

            }
        }

        // 专利报表
        if (patentReportVoList != null && patentReportVoList.size() > 0) {
            String orgName = "";
            insertRow = patentReportVoList.size();
            for (int rowNum = 0; rowNum < patentReportVoList.size(); rowNum++) {
                patentReportVo = patentReportVoList.get(rowNum);

                if (deptPatentMap != null) {
                    orgName = deptPatentMap.get(patentReportVo.getOrgId());
                }
                // 单位
                name = workbook.getName("KJBA1");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    if (rowNum > 0) {
                        if(rowNum == 1) {
                            secoendLastRow = secoendLastRow + firstLastRow + ref.getRow();
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

                name = workbook.getName("ZLTJ2");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(patentReportVo.getInNoAuthCount());
                }
                name = workbook.getName("ZLTJ3");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(patentReportVo.getInAuthCount());
                }
                name = workbook.getName("ZLTJ4");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(patentReportVo.getOutNoAuthCount());
                }
                name = workbook.getName("ZLTJ5");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(patentReportVo.getOutAuthCount());
                }

                name = workbook.getName("ZLTJ1");
                if (name != null) {
                    ref = new CellReference(name.getRefersToFormula());
                    sheet = workbook.getSheet(ref.getSheetName());
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                    if (cell == null) {
                        cell = row.createCell(ref.getCol());
                    }
                    cell.setCellValue(patentReportVo.getTotalCount());
                }


            }
        }

    }

}
