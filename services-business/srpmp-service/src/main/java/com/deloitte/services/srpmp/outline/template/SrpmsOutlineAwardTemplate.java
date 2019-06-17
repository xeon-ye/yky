package com.deloitte.services.srpmp.outline.template;

import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineAwardReportVo;
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
 * @Description :  题录-奖励统计数量导出报表
 * @Modified :
 */
public class SrpmsOutlineAwardTemplate {

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
     * @param reportVoList
     * @throws IOException
     */
    public void writeExcel(
            String outputPath,
            SrpmsOutlineQueryParam queryParam,
            Map<Long, String> deptMap,
            List<SrpmsOutlineAwardReportVo> reportVoList
    ) throws IOException {

        Workbook workbook = getWorkbookTypeByFile(outputPath);
        if (workbook == null) {
            return;
        }

        // 设置数据
        setReportModuleToExcelDatas(workbook, queryParam, deptMap, reportVoList);


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
     * @param reportVoList
     */
    private void setReportModuleToExcelDatas(
            Workbook workbook,
            SrpmsOutlineQueryParam queryParam,
            Map<Long, String> deptMap,
            List<SrpmsOutlineAwardReportVo> reportVoList
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

        setReportCountData(reportVoList, deptMap, workbook, sheet, name, ref, row, cell);
    }

    /**
     * 数量统计报表写入excel数据
     *
     * @param reportVoList
     * @param workbook
     * @param sheet
     * @param name
     * @param ref
     * @param row
     * @param cell
     */
    public void setReportCountData(
            List<SrpmsOutlineAwardReportVo> reportVoList,
            Map<Long, String> deptMap,
            Workbook workbook,
            Sheet sheet,
            Name name,
            CellReference ref,
            Row row,
            Cell cell
    ) {

        SrpmsOutlineAwardReportVo reportVo;
        String deptName = "";
        int firstLastRow = 0;
        int secoendLastRow = 0;
        int threeLastRow = 0;
        int insertRow = reportVoList.size();

        for (int rowNum = 0; rowNum < reportVoList.size(); rowNum++) {

            reportVo = reportVoList.get(rowNum);

            if (deptMap != null) {
                deptName = deptMap.get(reportVo.getOrgId());
            }

            /**
             * 中华医学科技奖
             */
            // 单位
            name = workbook.getName("KJBA4");
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
                cell.setCellValue(deptName);
            }
            name = workbook.getName("JLQT1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getZFirstCount());
            }
            name = workbook.getName("JLQT2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getZSecoendCount());
            }
            name = workbook.getName("JLQT3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getZThreeCount());
            }
            /**
             * 其他社会奖
             */
            name = workbook.getName("JLQT4");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getOFirstCount());
            }
            name = workbook.getName("JLQT5");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getOSecoendCount());
            }
            name = workbook.getName("JLQT6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getOThreeCount());
            }

            /**
             * 成果鉴定项目
             */
//            name = workbook.getName("JLJD1");
//            if (name != null) {
//                ref = new CellReference(name.getRefersToFormula());
//                sheet = workbook.getSheet(ref.getSheetName());
//                row = sheet.getRow(ref.getRow() + rowNum);
//                cell = row.getCell(ref.getCol());
//                if (cell == null) {
//                    cell = row.createCell(ref.getCol());
//                }
//                cell.setCellValue(.NEW_TITLE_COUNT_500530)));
//            }
//            name = workbook.getName("JLJD2");
//            if (name != null) {
//                ref = new CellReference(name.getRefersToFormula());
//                sheet = workbook.getSheet(ref.getSheetName());
//                row = sheet.getRow(ref.getRow() + rowNum);
//                cell = row.getCell(ref.getCol());
//                if (cell == null) {
//                    cell = row.createCell(ref.getCol());
//                }
//                cell.setCellValue(.NEW_TITLE_COUNT_500530)));
//            }

            /**
             * 高校科技奖励
             */
            // 单位
            name = workbook.getName("KJBA3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                if (rowNum > 0) {
                    if(rowNum == 1) {
                        secoendLastRow = firstLastRow + ref.getRow();
                        sheet.shiftRows(ref.getRow() + rowNum, firstLastRow + insertRow, insertRow, true, false);
                    }
                    row = sheet.createRow(ref.getRow() + rowNum);
                    cell = row.createCell(ref.getCol());
                } else {
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                }
                cell.setCellValue(deptName);
            }
            name = workbook.getName("JLGX1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getGNaturalFirstCount());
            }
            name = workbook.getName("JLGX2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getGNaturalSecoendCount());
            }
            name = workbook.getName("JLGX3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getGImproveFirstCount());
            }
            name = workbook.getName("JLGX4");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getGImproveSecoendCount());
            }
            name = workbook.getName("JLGX5");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getGInventFirstCount());
            }
            name = workbook.getName("JLGX6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getGInventSecoendCount());
            }

            /**
             *省部级
             */
            // 单位
            name = workbook.getName("KJBA2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                if (rowNum > 0) {
                    if(rowNum == 1) {
                        threeLastRow = secoendLastRow + firstLastRow + ref.getRow();
                        sheet.shiftRows(ref.getRow() + rowNum, secoendLastRow + firstLastRow + insertRow, insertRow, true, false);
                    }
                    row = sheet.createRow(ref.getRow() + rowNum);
                    cell = row.createCell(ref.getCol());
                } else {
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                }
                cell.setCellValue(deptName);
            }
            name = workbook.getName("JLSB1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getSepcialCount());
            }
            name = workbook.getName("JLSB2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getSNaturalFirstCount());
            }
            name = workbook.getName("JLSB3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getSNaturalSecoendCount());
            }
            name = workbook.getName("JLSB4");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getSNaturalThreeCount());
            }
            name = workbook.getName("JLSB5");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getSImproveFirstCount());
            }
            name = workbook.getName("JLSB6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getSImproveSecoendCount());
            }
            name = workbook.getName("JLSB7");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getSImproveThreeCount());
            }
            name = workbook.getName("JLSB8");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getSInventFirstCount());
            }
            name = workbook.getName("JLSB9");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getSInventSecoendCount());
            }
            name = workbook.getName("JLSB10");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getSInventThreeCount());
            }


            /**
             * 国家级科技奖励
             */
            // 单位
            name = workbook.getName("KJBA1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                if (rowNum > 0) {
                    if(rowNum == 1) {
                        sheet.shiftRows(ref.getRow() + rowNum, threeLastRow + insertRow, insertRow, true, false);
                    }
                    row = sheet.createRow(ref.getRow() + rowNum);
                    cell = row.createCell(ref.getCol());
                } else {
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                }
                cell.setCellValue(deptName);
            }

            // 最高奖
            name = workbook.getName("JLGJ1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getCBestCount());
            }
            // 国际合作奖
            name = workbook.getName("JLGJ2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getCCooperateCount());
            }
            // 自然科学类一等
            name = workbook.getName("JLGJ3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getCNaturalFirstCount());
            }
            // 自然科学类二等
            name = workbook.getName("JLGJ4");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getCNaturalSecoendCount());
            }
            // 科技进步奖一等
            name = workbook.getName("JLGJ5");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getCImproveFirstCount());
            }
            // 科技进步奖二等
            name = workbook.getName("JLGJ6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getCImproveSecoendCount());
            }
            name = workbook.getName("JLGJ7");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getCInventFirstCount());
            }
            name = workbook.getName("JLGJ8");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getCInventSecoendCount());
            }


        }
    }

}
