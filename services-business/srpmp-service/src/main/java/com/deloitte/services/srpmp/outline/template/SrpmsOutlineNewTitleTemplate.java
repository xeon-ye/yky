package com.deloitte.services.srpmp.outline.template;

import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineNewTitleReportCountVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineNewTitleReportOutlayVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineNewTitleStateCountVo;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
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
 * @Description :  题录-新获项目统计数量导出报表
 * @Modified :
 */
public class SrpmsOutlineNewTitleTemplate {

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
     * @param reportCountVoList
     * @param reportOutlayVoList
     * @param reportStateVoList
     * @throws IOException
     */
    public void writeExcel(
            String outputPath,
            int type,
            SrpmsOutlineQueryParam queryParam,
            Map<Long, String> deptMap,
            List<SrpmsOutlineNewTitleReportCountVo> reportCountVoList,
            List<SrpmsOutlineNewTitleReportOutlayVo> reportOutlayVoList,
            List<SrpmsOutlineNewTitleStateCountVo> reportStateVoList
    ) throws IOException {

        // 题录-新获项目-科技部导出报表
        Workbook workbook = getWorkbookTypeByFile(outputPath);
        if (workbook == null) {
            return;
        }

        // 设置数据
        setReportModuleToExcelDatas(workbook, queryParam, deptMap, reportCountVoList, reportOutlayVoList, reportStateVoList, type);


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
     * @param reportCountVoList
     * @param reportOutlayVoList
     */
    private void setReportModuleToExcelDatas(
            Workbook workbook,
            SrpmsOutlineQueryParam queryParam,
            Map<Long, String> deptMap,
            List<SrpmsOutlineNewTitleReportCountVo> reportCountVoList,
            List<SrpmsOutlineNewTitleReportOutlayVo> reportOutlayVoList,
            List<SrpmsOutlineNewTitleStateCountVo> reportStateVoList,
            int type
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

        switch (type) {
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FIRST:
                setReportCountData(reportCountVoList, deptMap, workbook, sheet, name, ref, row, cell);
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SECOND:
                setReportTotalOutlayData(reportOutlayVoList, deptMap, workbook, sheet, name, ref, row, cell);
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_THREE:
                setReportReceiveOutlayData(reportOutlayVoList, deptMap, workbook, sheet, name, ref, row, cell);
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SEVEN:
                setReportStateData(reportStateVoList, deptMap, workbook, sheet, name, ref, row, cell);
                break;
            default:
                break;
        }

    }

    /**
     * 数量统计报表写入excel数据
     *
     * @param reportCountVoList
     * @param workbook
     * @param sheet
     * @param name
     * @param ref
     * @param row
     * @param cell
     */
    public void setReportCountData(
            List<SrpmsOutlineNewTitleReportCountVo> reportCountVoList,
            Map<Long, String> deptMap,
            Workbook workbook,
            Sheet sheet,
            Name name,
            CellReference ref,
            Row row,
            Cell cell
    ) {

        SrpmsOutlineNewTitleReportCountVo resultVo;
        int totalCount;
        int indexCount;
        int firstLastRow = 0;
        int secoendLastRow = 0;
        int insertRow = reportCountVoList.size() - 1 ;

        String deptName = "";
        for (int rowNum = 0; rowNum < reportCountVoList.size(); rowNum++) {

            resultVo = reportCountVoList.get(rowNum);
            if (deptMap != null) {
                deptName = deptMap.get(resultVo.getOrgId());
            }

            /**
             * 新获部委局（省、市）等地方项目情况统计表
             */
            totalCount = 0;
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
                cell.setCellValue(deptName);
            }
            // 行业专项
            name = workbook.getName("XHBW1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGuildCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 其他项目
            name = workbook.getName("XHBW2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getOtherCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 教育部（项）-项目
            name = workbook.getName("XHBW3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getMoeProjectCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 教育部（项）-创新团队
            name = workbook.getName("XHBW4");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getMoeMakeCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家发改委项目
            name = workbook.getName("XHBW5");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getNdrcCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家药监局项目
            name = workbook.getName("XHBW6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getSfdaCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家中医药局项目
            name = workbook.getName("XHBW7");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getSatcmCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 其他部委项目
            name = workbook.getName("XHBW8");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getOtherMacCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 地方项目
            name = workbook.getName("XHBW9");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPlaceCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国际合作项目
            name = workbook.getName("XHBW10");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getNatunalCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 与企业联合项目
            name = workbook.getName("XHBW11");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getEnterCooCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 合计
            name = workbook.getName("XHBW12");
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


            /**
             * 新获国家自然基金项目情况统计表
             */
            totalCount = 0;
            // 单位
            name = workbook.getName("KJBA2");
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
            // 面上项目
            name = workbook.getName("XHGZR1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getOverallCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 重点项目（项）-项目
            name = workbook.getName("XHGZR2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPointProjectCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 重点项目（项）-课题
            name = workbook.getName("XHGZR3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPointTopicCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 重大项目-项目
            name = workbook.getName("XHGZR4");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getBigProjectCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 重大项目-课题
            name = workbook.getName("XHGZR5");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getBigTopicCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 重大研究计划项目-项目
            name = workbook.getName("XHGZR6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPlanProjectCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 重大研究计划项目-课题
            name = workbook.getName("XHGZR7");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPlanTopicCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 青年科学基金项目
            name = workbook.getName("XHGZR8");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectFirstCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 地区科学基金项目
            name = workbook.getName("XHGZR9");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectSecoendCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 优秀青年科学基金项目
            name = workbook.getName("XHGZR10");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectThreeCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家杰出青年科学基金项目
            name = workbook.getName("XHGZR11");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectFourCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 创新群体项目
            name = workbook.getName("XHGZR12");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectFiveCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国际（地区）合作研究项目
            name = workbook.getName("XHGZR13");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectSixCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 海外及港澳学者合作研究基金项目
            name = workbook.getName("XHGZR14");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectSevenCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家重大科研仪器研制项目
            name = workbook.getName("XHGZR15");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectEightCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 联合基金项目
            name = workbook.getName("XHGZR16");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectNineCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 应急管理项目
            name = workbook.getName("XHGZR17");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectTenCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 合计
            name = workbook.getName("XHGZR18");
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

            /**
             * 新获科技部项目情况统计表
             */
            totalCount = 0;
            // 单位
            name = workbook.getName("KJBA1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                if (rowNum > 0) {
                    if(rowNum == 1) {
                        sheet.shiftRows(ref.getRow() + rowNum, secoendLastRow + insertRow, insertRow, true, false);
                    }
                    row = sheet.createRow(ref.getRow() + rowNum);
                    cell = row.createCell(ref.getCol());
                } else {
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                }
                cell.setCellValue(deptName);
            }
            // 新药创制专项（项）-课题
            name = workbook.getName("KJBXY1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGxProjectCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 新药创制专项（项）-课题
            name = workbook.getName("KJBXY2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGxTopicCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 新药创制专项（项）-任务
            name = workbook.getName("KJBXY3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGxTaskCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 传染病专项（项）-项目
            name = workbook.getName("KJBCR1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGcProjectCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 传染病专项（项）-课题
            name = workbook.getName("KJBCR2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGcTopicCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 传染病专项（项）-任务
            name = workbook.getName("KJBCR3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGcTaskCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家重点研发计划-项目
            name = workbook.getName("KJBZD1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGProjectCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家重点研发计划-课题
            name = workbook.getName("KJBZD2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGTopicCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家重点研发计划-任务
            name = workbook.getName("KJBZD3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGTaskCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 技术创新引导专项
            name = workbook.getName("KJBZJCX");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getMakeCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 基地和人才专项
            name = workbook.getName("XHKJB11");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getSpecialCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 基础条件平台-牵头单位
            name = workbook.getName("XHKJB12");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getHeadCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 基础条件平台-参加单位
            name = workbook.getName("XHKJB13");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getJoinCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国际合作项目
            name = workbook.getName("XHKJB14");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getCooperateProjectCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 其他计划项目
            name = workbook.getName("XHKJB15");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getOtherPlanCount();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 合计
            name = workbook.getName("XHKJB16");
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

        }
    }

    /**
     * 总经费统计报表写入excel数据
     *
     * @param reportOutlayVoList
     * @param workbook
     * @param sheet
     * @param name
     * @param ref
     * @param row
     * @param cell
     */
    public void setReportTotalOutlayData(
            List<SrpmsOutlineNewTitleReportOutlayVo> reportOutlayVoList,
            Map<Long, String> deptMap,
            Workbook workbook,
            Sheet sheet,
            Name name,
            CellReference ref,
            Row row,
            Cell cell
    ) {

        SrpmsOutlineNewTitleReportOutlayVo resultVo;
        double totalCount;
        double indexCount;
        int firstLastRow = 0;
        int secoendLastRow = 0;
        int insertRow = reportOutlayVoList.size();

        String deptName = "";
        for (int rowNum = 0; rowNum < reportOutlayVoList.size(); rowNum++) {

            resultVo = reportOutlayVoList.get(rowNum);

            if (deptMap != null) {
                deptName = deptMap.get(resultVo.getOrgId());
            }

            /**
             * 部委局（省、市）等地方项目经费（万元）
             */
            totalCount = 0;
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
                cell.setCellValue(deptName);
            }
            // 国家卫生健康委
            name = workbook.getName("JFBW1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGuildOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 教育部（项）
            name = workbook.getName("JFBW2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getMoeProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家发改委项目
            name = workbook.getName("JFBW3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getNdrcOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家药监局项目
            name = workbook.getName("JFBW4");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getSfdaOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家中医药局项目
            name = workbook.getName("JFBW5");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getSatcmOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 其他部委项目
            name = workbook.getName("JFBW6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getOtherMacOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 地方项目
            name = workbook.getName("JFBW7");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPlaceOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国际合作项目
            name = workbook.getName("JFBW8");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getNatunalOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 与企业联合项目
            name = workbook.getName("JFBW9");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getEnterCooOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 合计
            name = workbook.getName("JFBW10");
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


            /**
             * 科技部项目经费（万元）
             */
            totalCount = 0;
            // 单位
            name = workbook.getName("KJBA2");
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
            // 国家科技重大专项
            name = workbook.getName("JFKJB1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGxProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家重点研发计划
            name = workbook.getName("JFKJB2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 技术创新引导专项
            name = workbook.getName("JFKJB3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getMakeOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 基地和人才专项
            name = workbook.getName("JFKJB4");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getSpecialOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 基础条件平台
            name = workbook.getName("JFKJB5");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getHeadOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }

            // 国际合作项目
            name = workbook.getName("JFKJB6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getCooperateProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 其他计划项目
            name = workbook.getName("JFKJB7");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getOtherPlanOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 合计
            name = workbook.getName("JFKJB8");
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

            /**
             * 国家自然基金项目经费（万元）
             */
            totalCount = 0;
            // 单位
            name = workbook.getName("KJBA1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                if (rowNum > 0) {
                    if(rowNum == 1) {
                        sheet.shiftRows(ref.getRow() + rowNum, secoendLastRow + insertRow, insertRow, true, false);
                    }
                    row = sheet.createRow(ref.getRow() + rowNum);
                    cell = row.createCell(ref.getCol());
                } else {
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                }
                cell.setCellValue(deptName);
            }
            // 面上项目
            name = workbook.getName("JFGZR1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getOverallOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 重点项目（项)
            name = workbook.getName("JFGZR2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPointProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 重大项目
            name = workbook.getName("JFGZR3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getBigProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 重大研究计划项目
            name = workbook.getName("JFGZR4");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPlanProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 青年科学基金项目
            name = workbook.getName("JFGZR5");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectFirstOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 地区科学基金项目
            name = workbook.getName("JFGZR6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectSecoendOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 优秀青年科学基金项目
            name = workbook.getName("JFGZR7");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectThreeOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家杰出青年科学基金项目
            name = workbook.getName("JFGZR8");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectFourOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 创新群体项目
            name = workbook.getName("JFGZR9");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectFiveOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国际（地区）合作研究项目
            name = workbook.getName("JFGZR10");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectSixOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 海外及港澳学者合作研究基金项目
            name = workbook.getName("JFGZR11");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectSevenOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家重大科研仪器研制项目
            name = workbook.getName("JFGZR12");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectEightOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 联合基金项目
            name = workbook.getName("JFGZR13");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectNineOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 应急管理项目
            name = workbook.getName("JFGZR14");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectTenOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 合计
            name = workbook.getName("JFGZR15");
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

        }

    }

    /**
     * 到位经费统计报表写入excel数据
     *
     * @param reportOutlayVoList
     * @param workbook
     * @param sheet
     * @param name
     * @param ref
     * @param row
     * @param cell
     */
    public void setReportReceiveOutlayData(
            List<SrpmsOutlineNewTitleReportOutlayVo> reportOutlayVoList,
            Map<Long, String> deptMap,
            Workbook workbook,
            Sheet sheet,
            Name name,
            CellReference ref,
            Row row,
            Cell cell
    ) {

        SrpmsOutlineNewTitleReportOutlayVo resultVo;
        double totalCount;
        double indexCount;
        int firstLastRow = 0;
        int secoendLastRow = 0;
        int insertRow = reportOutlayVoList.size();

        String deptName = "";
        for (int rowNum = 0; rowNum < reportOutlayVoList.size(); rowNum++) {

            resultVo = reportOutlayVoList.get(rowNum);

            if (deptMap != null) {
                deptName = deptMap.get(resultVo.getOrgId());
            }

            /**
             * 部委局（省、市）等地方项目经费（万元）
             */
            totalCount = 0;
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
                cell.setCellValue(deptName);
            }
            // 国家卫生健康委
            name = workbook.getName("DWJFBW1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGuildOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 教育部（项）
            name = workbook.getName("DWJFBW2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getMoeProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家发改委项目
            name = workbook.getName("DWJFBW3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getNdrcOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家药监局项目
            name = workbook.getName("DWJFBW4");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getSfdaOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家中医药局项目
            name = workbook.getName("DWJFBW5");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getSatcmOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 其他部委项目
            name = workbook.getName("DWJFBW6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getOtherMacOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 地方项目
            name = workbook.getName("DWJFBW7");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPlaceOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国际合作项目
            name = workbook.getName("DWJFBW8");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getNatunalOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 与企业联合项目
            name = workbook.getName("DWJFBW9");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getEnterCooOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 合计
            name = workbook.getName("DWJFBW10");
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

            /**
             * 科技部项目经费（万元）
             */
            totalCount = 0;
            // 单位
            name = workbook.getName("KJBA2");
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
            // 国家科技重大专项
            name = workbook.getName("DWJFKJB1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGxProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家重点研发计划
            name = workbook.getName("DWJFKJB2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getGProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 技术创新引导专项
            name = workbook.getName("DWJFKJB3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getMakeOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 基地和人才专项
            name = workbook.getName("DWJFKJB4");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getSpecialOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }

            // 重大科学计划
            name = workbook.getName("DWJFKJB5");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getBigTechOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 科技支撑计划
            name = workbook.getName("DWJFKJB6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getTechBraceOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 973计划
            name = workbook.getName("DWJFKJB7");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPlanFirstOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 863计划
            name = workbook.getName("DWJFKJB8");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPlanSecoendOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }

            // 基础条件平台
            name = workbook.getName("DWJFKJB9");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getHeadOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }

            // 国际合作项目
            name = workbook.getName("DWJFKJB10");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getCooperateProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 其他计划项目
            name = workbook.getName("DWJFKJB11");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getOtherPlanOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 合计
            name = workbook.getName("DWJFKJB12");
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

            /**
             * 国家自然基金项目经费（万元）
             */
            totalCount = 0;
            // 单位
            name = workbook.getName("KJBA1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                if (rowNum > 0) {
                    if(rowNum == 1) {
                        sheet.shiftRows(ref.getRow() + rowNum, secoendLastRow + insertRow, insertRow, true, false);
                    }
                    row = sheet.createRow(ref.getRow() + rowNum);
                    cell = row.createCell(ref.getCol());
                } else {
                    row = sheet.getRow(ref.getRow() + rowNum);
                    cell = row.getCell(ref.getCol());
                }

                cell.setCellValue(deptName);
            }
            // 面上项目
            name = workbook.getName("DWJFGZR1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getOverallOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 重点项目（项)
            name = workbook.getName("DWJFGZR2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPointProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 重大项目
            name = workbook.getName("DWJFGZR3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getBigProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 重大研究计划项目
            name = workbook.getName("DWJFGZR4");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getPlanProjectOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 青年科学基金项目
            name = workbook.getName("DWJFGZR5");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectFirstOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 地区科学基金项目
            name = workbook.getName("DWJFGZR6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectSecoendOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 优秀青年科学基金项目
            name = workbook.getName("DWJFGZR7");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectThreeOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家杰出青年科学基金项目
            name = workbook.getName("DWJFGZR8");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectFourOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 创新群体项目
            name = workbook.getName("DWJFGZR9");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectFiveOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国际（地区）合作研究项目
            name = workbook.getName("DWJFGZR10");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectSixOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 海外及港澳学者合作研究基金项目
            name = workbook.getName("DWJFGZR11");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectSevenOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 国家重大科研仪器研制项目
            name = workbook.getName("DWJFGZR12");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectEightOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 联合基金项目
            name = workbook.getName("DWJFGZR13");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectNineOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 应急管理项目
            name = workbook.getName("DWJFGZR14");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                indexCount = resultVo.getProjectTenOutlay();
                totalCount += indexCount;
                cell.setCellValue(indexCount);
            }
            // 合计
            name = workbook.getName("DWJFGZR15");
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

        }
    }

    /**
     * 项目执行情况
     *
     * @param voList
     * @param workbook
     * @param sheet
     * @param name
     * @param ref
     * @param row
     * @param cell
     */
    public void setReportStateData(
            List<SrpmsOutlineNewTitleStateCountVo> voList,
            Map<Long, String> deptMap,
            Workbook workbook,
            Sheet sheet,
            Name name,
            CellReference ref,
            Row row,
            Cell cell
    ) {

        SrpmsOutlineNewTitleStateCountVo reportVo;

        String deptName = "";
        for (int rowNum = 0; rowNum < voList.size(); rowNum++) {

            reportVo = voList.get(rowNum);

            if (deptMap != null) {
                deptName = deptMap.get(reportVo.getOrgId());
            }

            // 单位
            name = workbook.getName("KJBA1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
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

            name = workbook.getName("XMZX1");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getEndCount());
            }
            name = workbook.getName("XMZX2");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getResearchCount());
            }
            name = workbook.getName("XMZX3");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getNewCount());
            }
            name = workbook.getName("XMZX6");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getTerminationCount());
            }
            name = workbook.getName("XMZX7");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getCancelCount());
            }
            name = workbook.getName("XMZX8");
            if (name != null) {
                ref = new CellReference(name.getRefersToFormula());
                sheet = workbook.getSheet(ref.getSheetName());
                row = sheet.getRow(ref.getRow() + rowNum);
                cell = row.getCell(ref.getCol());
                if (cell == null) {
                    cell = row.createCell(ref.getCol());
                }
                cell.setCellValue(reportVo.getDelayCount());
            }

        }
    }

}
