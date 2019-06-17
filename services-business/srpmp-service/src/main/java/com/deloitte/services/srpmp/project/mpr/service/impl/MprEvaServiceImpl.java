package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.util.HTMLParseUtil;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.project.mpr.entity.*;
import com.deloitte.services.srpmp.project.mpr.service.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Author:LIJUN
 * Date:18/03/2019
 * Description:
 */
@Service
@Transactional
@Slf4j
public class MprEvaServiceImpl implements IMprEvaService {

    @Override
    public void importWord(String wordFileUrl) {
//        try {
//            String localPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile().toString();
////            String htmlFilePath = WordConvertUtil.wordConvertToHtml(wordFileUrl, localPath);
//            //解析html文件
////            Document document = Jsoup.parse(new File(htmlFilePath), "utf-8");
//            Document document = Jsoup.parse(new File("C:\\Users\\junlicq\\Documents\\Work Files\\科研\\doc\\3.html"), "utf-8");
//            Elements tableElements = document.getElementsByTag("table");
//            if (tableElements.isEmpty()) {
//                throw new BaseException(SrpmsErrorType.NO_ELEMENTS);
//            }
//
//            int currentRow = 0;
//
//            //附件1
//            Element baseTable = tableElements.get(0);
//
//            MprEvaBaseA evaBaseA = new MprEvaBaseA();
//            String projectNo = HTMLParseUtil.extractCellFromTable(baseTable, 3, 1);
//            currentRow = buildEvaBaseAInfo(baseTable, evaBaseA, currentRow);
//
//            //一、项目基本情况
//            //构造项目任务列表数据
//            List<MprEvaTaskTable> taskTableList = Lists.newArrayList();
//            currentRow = bulidEvaTaskTable(baseTable, taskTableList, projectNo, currentRow);
//            evaTaskTableService.saveBatch(taskTableList);
//
//            evaBaseA.setProgressState(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//            currentRow += 1;
//            evaBaseA.setWorkState(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//            currentRow += 1;
//
//            //二、项目人员情况
//            //构造项目人员情况列表数据
//            List<MprEvaPeopleTable> peopleTableList = Lists.newArrayList();
//            currentRow = bulidEvaPeopleTable(baseTable, peopleTableList, projectNo, currentRow);
//            evaPeopleTableService.saveBatch(peopleTableList);
//
//            //研究人员
//            evaBaseA.setTotalPeople(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 0));
//            evaBaseA.setResearcherAmount(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//            evaBaseA.setResearcherSeniorLevel(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 3));
//            evaBaseA.setResearcherDeputyLevel(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 5));
//            evaBaseA.setResearcherMiddleLevel(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 7));
//            evaBaseA.setResearcherJuniorLevel(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 9));
//            evaBaseA.setResearcherOtherLevel(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 11));
//            currentRow += 1;
//            evaBaseA.setResearcherDoctorDegree(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//            evaBaseA.setResearcherMasterDegree(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 3));
//            evaBaseA.setResearcherMasterDegree(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 5));
//            evaBaseA.setResearcherOtherDegree(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 7));
//
//            //技术人员
//            currentRow += 1;
//            evaBaseA.setTechnicalAmount(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 0));
//            evaBaseA.setTechnicalSeniorLevel(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 2));
//            evaBaseA.setTechnicalDeputyLevel(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 4));
//            evaBaseA.setTechnicalMiddleLevel(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 6));
//            evaBaseA.setTechnicalJuniorLevel(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 8));
//            evaBaseA.setTechnicalOtherLevel(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 10));
//            currentRow += 1;
//            evaBaseA.setTechnicalDoctorDegree(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//            evaBaseA.setTechnicalMasterDegree(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 3));
//            evaBaseA.setTechnicalBachelorDegree(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 5));
//            evaBaseA.setTechnicalOtherDegree(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 7));
//
//            //三、项目中期绩效目标及考核指标完成情况
//            currentRow += 4;
//            List<MprEvaCompleteTable> completeTableList = Lists.newArrayList();
//            currentRow = buildEvaCompleteTable(baseTable, completeTableList, projectNo, currentRow);
//            evaCompleteTableService.saveBatch(completeTableList);
//
//            currentRow += 1;
//            evaBaseA.setDiscTheoPrinNum(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 3));
//            evaBaseA.setTechMethodNum(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 5));
//            evaBaseA.setProductNum(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 7));
//            currentRow += 1;
//            evaBaseA.setStanGuideSpecNum(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//            evaBaseA.setAppSolutionNum(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 3));
//            evaBaseA.setSoftNum(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 5));
//            currentRow += 1;
//            evaBaseA.setDeviSystNum(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//            evaBaseA.setResLibNum(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 3));
//            evaBaseA.setDatabaseNum(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 5));
//            currentRow += 1;
//            evaBaseA.setTechSupPlatNum(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//            evaBaseA.setTechSystNum(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 3));
//            evaBaseA.setConReseRepoNum(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 5));
//
//            //
//            currentRow += 2;
//            List<MprEvaTheoTechProTable> theoTechProTableList = Lists.newArrayList();
//            currentRow = buildTheoTechProTable(baseTable, theoTechProTableList, projectNo, currentRow);
//
//
//
//            evaBaseAService.save(evaBaseA);
//
//
//        } catch (FileNotFoundException e) {
//            throw new BaseException(SrpmsErrorType.FILE_NO_FOUND);
//        } catch (IOException e) {
//            log.error("转换html io异常：", e);
//            throw new BaseException(SrpmsErrorType.TRANSFER_HTML_ERROR);
//        } catch (Exception e) {
//            log.error("导入中期绩效考评报告系统异常", e);
//            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
//        }
    }

//    private int buildEvaBaseAInfo(Element baseTable, MprEvaBaseA evaBaseA, int currentRow) {
//        currentRow += 2;//2
//        evaBaseA.setProjectName(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//        currentRow += 1;//3
//        String projectNo = HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1);
//        evaBaseA.setProjectNo(projectNo);
//        evaBaseA.setJobQuantity(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 3));
//        evaBaseA.setBudget(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 5));
//        evaBaseA.setExecuteCycle(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 7));
//        currentRow += 1;//4
//        evaBaseA.setProjectCategory(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//        currentRow += 1;//5
//        evaBaseA.setProjectClassification(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//        currentRow += 1;//6
//        evaBaseA.setLeadUnit(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//        evaBaseA.setTakeUnit(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 3));
//        currentRow += 1;//7
//        evaBaseA.setChiefSpecialist(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 1));
//        evaBaseA.setJointChiefSpecialist(HTMLParseUtil.extractCellFromTable(baseTable, currentRow, 3));
//        currentRow += 1;
//        return currentRow;
//    }
//
//    //构造项目任务列表数据
//    private int bulidEvaTaskTable(Element baseTable,  List<MprEvaTaskTable> taskTableList, String projectNo, int currentRow) {
//        Elements rowList = baseTable.getElementsByTag("tr");
//        for (int i = currentRow; i < rowList.size(); i++){
//            Element rowElement = rowList.get(i);
//            Elements cellElements = rowElement.getElementsByTag("td");
//            //匹配到“任务设置”后，开始从下一行取数据
//            if (StringUtils.equals(cellElements.get(0).text(), "任务设置")) {
//                continue;
//            }
//            //匹配到“进展情况”退出整个循环
//            if (StringUtils.equals(cellElements.get(0).text(), "进展情况")) {
//                //退出前赋值当前行
//                currentRow = i;
//                break;
//            }
//            MprEvaTaskTable taskTable = new MprEvaTaskTable();
//            taskTable.setProjectNo(projectNo);
//            taskTable.setTaskName(cellElements.get(1).text());
//            taskTable.setTaskPrincipal(cellElements.get(2).text());
//            taskTable.setTaskParticipant(cellElements.get(3).text());
//            taskTableList.add(taskTable);
//        }
//        return currentRow;
//    }
//
//    //构造项目人员情况列表数据
//    private int bulidEvaPeopleTable(Element baseTable, List<MprEvaPeopleTable> peopleTableList, String projectNo, int currentRow) {
//        currentRow += 1;
//        Elements rowList = baseTable.getElementsByTag("tr");
//        for (int i = currentRow; i < rowList.size(); i++){
//            Element rowElement = rowList.get(i);
//            Elements cellElements = rowElement.getElementsByTag("td");
//            //匹配到“序号”后，开始从下一行取数据
//            if (StringUtils.equals(cellElements.get(0).text(), "序号")) {
//                continue;
//            }
//            //匹配到“进展情况”退出整个循环
//            if (cellElements.get(0).text().startsWith("总人数")) {
//                //退出前赋值当前行
//                currentRow = i;
//                break;
//            }
//            MprEvaPeopleTable peopleTable = new MprEvaPeopleTable();
//            peopleTable.setProjectNo(projectNo);
//            peopleTable.setName(cellElements.get(1).text());
//            peopleTable.setBirthday(cellElements.get(2).text());
//            peopleTable.setJobTitle(cellElements.get(3).text());
//            peopleTable.setDegree(cellElements.get(4).text());
//            peopleTable.setProfession(cellElements.get(5).text());
//            peopleTable.setDivisionLabor(cellElements.get(6).text());
//            peopleTable.setCurrentUnit(cellElements.get(7).text());
//            peopleTable.setPhone(cellElements.get(8).text());
//            peopleTable.setChangesOccur(cellElements.get(9).text());
//            peopleTable.setChangesOccurReason(cellElements.get(10).text());
//            peopleTableList.add(peopleTable);
//        }
//        return currentRow;
//    }
//
//    //项目中期绩效目标及考核指标完成情况
//    private int buildEvaCompleteTable(Element baseTable, List<MprEvaCompleteTable> completeTableList, String projectNo, int currentRow) {
//        Elements rowList = baseTable.getElementsByTag("tr");
//
//        String projectObjectives = "";
//        String repOutcomeName = "";
//        String outcomeType = "";
//        String relatedTask = "";
//        for (int i = currentRow; i < rowList.size(); i++) {
//            Element rowElement = rowList.get(i);
//            Elements cellElements = rowElement.getElementsByTag("td");
//
//            //匹配到“进展情况”退出整个循环
//            if (cellElements.get(0).text().contains("项目实施成效")) {
//                //退出前赋值当前行
//                currentRow = i;
//                break;
//            }
//            MprEvaCompleteTable table = new MprEvaCompleteTable();
//            table.setProjectNo(projectNo);
//            if (cellElements.size() == 7) {
//                projectObjectives = cellElements.get(0).text();
//                repOutcomeName = cellElements.get(1).text();
//                outcomeType = cellElements.get(2).text();
//                relatedTask = cellElements.get(3).text();
//                table.setOutcomeLevel(cellElements.get(4).text());
//                table.setIndicatorName(cellElements.get(5).text());
//                table.setIndicatorCompleteStatus(cellElements.get(6).text());
//            }
//
//            if (cellElements.size() == 6) {
//                repOutcomeName = cellElements.get(0).text();
//                outcomeType = cellElements.get(1).text();
//                relatedTask = cellElements.get(2).text();
//                table.setOutcomeLevel(cellElements.get(3).text());
//                table.setIndicatorName(cellElements.get(4).text());
//                table.setIndicatorCompleteStatus(cellElements.get(5).text());
//            }
//            if (cellElements.size() == 3) {
//                table.setOutcomeLevel(cellElements.get(0).text());
//                table.setIndicatorName(cellElements.get(1).text());
//                table.setIndicatorCompleteStatus(cellElements.get(2).text());
//            }
//            table.setProjectObjectives(projectObjectives);
//            table.setRepOutcomeName(repOutcomeName);
//            table.setOutcomeType(outcomeType);
//            table.setRelatedTask(relatedTask);
//            completeTableList.add(table);
//        }
//        return currentRow;
//    }
//
//    private int buildTheoTechProTable(Element baseTable, List<MprEvaTheoTechProTable> theoTechProTableList, String projectNo, int currentRow) {
//        Elements rowList = baseTable.getElementsByTag("tr");
//        for (int i = currentRow; i < rowList.size(); i++) {
//            Element rowElement = rowList.get(i);
//            Elements cellElements = rowElement.getElementsByTag("td");
//
//            //匹配到“进展情况”退出整个循环
//            if (cellElements.get(0).text().contains("项目实施成效")) {
//                //退出前赋值当前行
//                currentRow = i;
//                break;
//            }
//        }
//        return currentRow;
//    }

}
