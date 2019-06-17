package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.service.*;
import com.deloitte.services.dss.util.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Api(tags = "11.财务 经费管控-预算执行")
@Slf4j
@RestController
@RequestMapping("/dss/finance")
public class BudgetExecutionController {
    @Autowired
    private IFinIncomeService finIncomeService;
    @Autowired
    private IFinanceExecutionService financeExecutionService;
    @Autowired
    private IFinRateService finRateService;
    @Autowired
    private IFinGrowthService finGrowthService;
    @Autowired
    private IFinExpenditureSerivice finExpenditureSerivice;
    @Autowired
    private IIncomeBudgetExecutionService incomeBudgetExecutionService;
    @Autowired
    private IFinTabsService finTabsService;
    @Autowired
    private IFinCompanyService finCompanyService;

    @ApiOperation(value = "经费管控-预算执行",notes = "经费管控-预算执行")
    @PostMapping("/budgetExecution")
    public Result budgetExecution(@Valid @RequestBody AcceptVo myData){
        //parameter table
        Map table = new Hashtable();
        //response table
        Map result = new Hashtable();
        String year = TimeUtil.getYear();
        String month = TimeUtil.getTime();
        List<String> comCode = myData.getComCode();
        table.put("comCode",myData.getComCode());
        //机构数量
        Integer companys = finCompanyService.selectCompany().size();

        if(myData.getBudgetType().equals("1")) {
            //当年累计收入 FIND0007
            table.put("month", month);
            table.put("indexCode", "FIND0007");
            List<IncomeBudgetVo> incomeBudgetVos = finIncomeService.selectTotalIncome(table);
            result.put("totalIncome", incomeBudgetVos);

            //收入预算达成率
            table.put("indexCode", "FINC0001");
            List<FinanceExecutionVo> financeExecutionVos = financeExecutionService.queryExecution(table);
            if(null != financeExecutionVos && financeExecutionVos.size()>0) {
                BigDecimal incomeBudExe = financeExecutionVos.get(0).getExecution();
                result.put("incomeBudExe", incomeBudExe);

                //累计收入往年同比 FINC0007
                table = new Hashtable();
                table.put("indexCode", "FINC0007");
                table.put("month", month);
                table.put("comCode", comCode);
                List<IncomeBudgetVo> incomeBudgetVoList = finRateService.selectRate(table);
//            if(null != list && list.size()>0){
                BigDecimal incomeRate = incomeBudgetVoList.get(0).getRate();
                result.put("incomeRate", incomeRate);

                //收入预算达成率 往年同比
                table = new Hashtable();
                table.put("indexCode", "FINC0001");
                table.put("month", Integer.parseInt(month.split("-")[0]) - 1 + "-" + month.split("-")[1]);
                List<FinanceExecutionVo> financeExecutionVoList = financeExecutionService.queryExecution(table);
                if (null != financeExecutionVoList && financeExecutionVoList.size() != 0) {
                    BigDecimal incomeBudExeBefore = financeExecutionVoList.get(0).getExecution();
                    BigDecimal incomeBudExeRate = incomeBudExe.subtract(incomeBudExeBefore);
                    result.put("incomeBudExeRate", incomeBudExeRate);
                } else {
                    result.put("incomeBudExeRate", incomeBudExe);
                }

                //累计收入柱状图
                table = new Hashtable();
                table.put("indexCode", "FIND0007");
                table.put("month", month);
                table.put("comCode", comCode);
                List<IncomeBudgetVo> incomeMonth = finIncomeService.selectComIncomeMonth(table);
                result.put("incomeMonth", incomeMonth);

                //收入同比增长
                table = new Hashtable();
                table.put("month", month);
                table.put("indexCode", "FINC0007");
                table.put("comCode", comCode);
                List<IncomeBudgetVo> rateMonth = incomeBudgetExecutionService.queryIncomeRate(table);
                result.put("rateMonth", rateMonth);

                //收入预算达成率
                table = new Hashtable();
                table.put("month", month);
                table.put("indexCode", "FINC0001");
                table.put("comCode", comCode);
                List<IncomeBudgetVo> comExecution = incomeBudgetExecutionService.queryIncomeBudExe(table);
                result.put("comExecution", comExecution);

                //整体收入预算达成率 虚线
                table = new Hashtable();
                table.put("month", month);
                table.put("indexCode", "FINC0001");
                table.put("comCode", comCode);
                List<FinanceExecutionVo> list3 = financeExecutionService.queryExecution(table);
                result.put("totalIncomeExecution", list3);

                //整体收入计划增长率
                table = new Hashtable();
                table.put("periodCode", year);
                table.put("indexCode", "FINC0013");
                table.put("comCode", comCode);
                List<GrowthVo> growthVos = finGrowthService.quesryGrowth(table);
                if (null != growthVos && growthVos.size() > 0) {
                    BigDecimal totalIncomeGrowth = growthVos.get(0).getYtdN()
                            .divide(growthVos.get(0).getYtdD(), 3, BigDecimal.ROUND_HALF_UP)
                            .subtract(BigDecimal.valueOf(1));
                    result.put("totalIncomeGrowth", totalIncomeGrowth);

                    //机构收入预算达成率 柱状图
                    table.put("month", month);
                    table.put("indexCode", "FINC0001");
                    table.put("comCode", comCode);
                    List<FinanceExecutionVo> comIncomeBudExe = financeExecutionService.queryComExecution(table);
                    result.put("comIncomeBudExe", comIncomeBudExe);

                    //机构收入计划增长 图三 X坐标
                    table.put("month", month);
                    table.put("comCode", comCode);
                    table.put("indexCode", "FINC0013");
                    List<GrowthVo> comIncomeGrowth = finGrowthService.quesryComGrowth(table);
                    result.put("comIncomeGrowth", comIncomeGrowth);

                    //机构收入同比增长 图三 Y坐标
                    table = new HashMap();
                    table.put("month", month);
                    table.put("indexCode", "FINC0007");
                    table.put("comCode", comCode);
                    List<IncomeBudgetVo> list2 = finRateService.selectComRate(table);


                    //机构收入 图三 圆点数据
                    table.put("month", month);
                    table.put("indexCode", "FIND0007");
                    List<IncomeBudgetVo> comIncome = incomeBudgetExecutionService.queryComIncome(table);
                    result.put("comIncome", comIncome);
                    if(null!= comIncomeGrowth && comIncomeGrowth.size()>0&& null!=list2 && list2.size()>0) {
                        for(GrowthVo growthVo:comIncomeGrowth) {
                            for (int i = 0; i < list2.size(); i++) {
                                if (growthVo.getComCode().equals(list2.get(i).getComCode())) {
                                    list2.get(i).setGrowthRate(growthVo.getExecution());
                                }
                            }
                        }
                    }
                    if(null != comIncomeGrowth && comIncomeGrowth.size()>0 && null!=list2 && list2.size()>0) {
                        for(IncomeBudgetVo incomeBudgetVo:comIncome) {
                            for (int i = 0; i < list2.size(); i++) {
                                if(incomeBudgetVo.getComCode().equals(list2.get(i).getComCode())) {
                                    list2.get(i).setYtd(incomeBudgetVo.getYtd());
                                }
                            }
                        }
                    }
                    result.put("comIncomeRate", list2);

                    //机构收入预算达成率 X坐标
                    table = new Hashtable();
                    table.put("month", month);
                    table.put("indexCode", "FINC0001");
                    table.put("comCode", comCode);
                    List<IncomeBudgetVo> comIncomeBudExecution = incomeBudgetExecutionService.queryComIncomeBudExe(table);
                    result.put("comIncomeBudExecution", comIncomeBudExecution);

                    //收入类型同比增长 图四 Y坐标
                    table = new Hashtable();
                    table.put("month", month);
                    table.put("comCode", comCode);
                    List<IncomeBudgetVo> incomeTypeRate = finRateService.selectRate(table);

                    //收入类型预算达成率 图四 X坐标
                    List<IncomeBudgetVo> incomeTypeExe = incomeBudgetExecutionService.queryTypeIncomeBudExe(table);

                    //机构收入预算 图四 圆点数据
                    List<IncomeBudgetVo> comIncomeBud = incomeBudgetExecutionService.queryComIncomeBud(table);

                    for (int i = 0; i < comIncomeBud.size(); i++) {
                        comIncomeBud.get(i).setRate(incomeTypeRate.get(i).getRate());
                        comIncomeBud.get(i).setExecution(incomeTypeExe.get(i).getExecution());
                    }
                    result.put("comIncomeBud", comIncomeBud);

            /*收入预算达成情况 筛选标签*/
                    table = new HashMap();
                    table.put("indexCode", "FINC0007");
                    //年累计收入同比增长 比较 计划增长
                    int type = incomeRate.compareTo(totalIncomeGrowth);
                    table.put("type", type);
                    TabsVo tabsUp = finTabsService.selectTabs(table);
                    result.put("tabsUp", tabsUp);

                    //计算整体预算达成率
                    table.put("indexCode", "FINC0001");
                    BigDecimal temp1 = list3.get(0).getExecution();
                    //统计机构数量
                    Integer count = 0;
                    //机构收入预算达成率 比较 整体预算达成率 统计不达标的机构数量
                    for (int i = 0; i < comIncomeBudExe.size(); i++) {
                        if (comIncomeBudExe.get(i).getExecution().compareTo(temp1) == -1) {
                            count++;
                        }
                    }
                    TabsVo tabsDown = new TabsVo();
                    //如果不达标机构数量为0，获取所有机构数量 返回全部机构达标,否则返回{count}个机构不达标
                    if (count == 0) {
                        table.put("type", 1);
                        tabsDown = finTabsService.selectTabs(table);
                        tabsDown.setDes(comIncomeBudExe.size() + tabsDown.getDes());
                        result.put("tabsDown", tabsDown);
                    } else {
                        table.put("type", -1);
                        tabsDown = finTabsService.selectTabs(table);
                        tabsDown.setDes(count + tabsDown.getDes());
                        result.put("tabsDown", tabsDown);
                    }

            /*tabsVo.setDes("10家机构收入同比增长低于计划增长，收入预算达成情况不及预期。");
            tabsVo.setColor("yellow");
            result.put("tabsUp1",tabsVo);*/

            /*tabsVo.setDes("2个收入类型收入同比增长低于收入计划增长，且预算达成率低于整体水平。");
            tabsVo.setColor("yellow");
            result.put("tabsDown1",tabsVo);*/

            /*收入预算达成分布 筛选标签*/
                    //初始化机构数量 list2 totalIncomeGrowth
                    TabsVo tabsVo = new TabsVo();
                    count = 0;
                    Map map = new HashMap();
                    map.put("indexCode", "FINC0011");
                    for (IncomeBudgetVo temp : list2) {
                        int typeRate = temp.getRate().compareTo(temp.getGrowthRate());
                        if (typeRate == 1) count++;
                    }
                    if (count == 0) {
                        map.put("type", -1);
                        TabsVo rate = finTabsService.selectTabs(map);
                        rate.setDes(list2.size() + rate.getDes());
                        result.put("tabsUp1", rate);
                    } else {
                        map.put("type", 1);
                        TabsVo rate = finTabsService.selectTabs(map);
                        rate.setDes(count + rate.getDes());
                        result.put("tabsUp1", rate);
                    }

                    //初始化机构数量
                    count = 0;
                   Integer total = 0;
                    //获取收入类型同比增长数据 rate
            /*table = new Hashtable();
            table.put("month",month);
            table.put("comCode",comCode);
            List<IncomeBudgetVo> incomeTypeRate = finRateService.selectRate(table);*/
                    //获取收入类型预算达成率数据 execution
          /*  table = new Hashtable();
            table.put("month",month);
            table.put("comCode",comCode);
            table.put("indexCode","FINC0001");
            List<IncomeBudgetVo> executionTypeExe = incomeBudgetExecutionService.queryComIncomeBudExe(table);*/
                    //收入类型同比增长 比较 收入类型预算达成率
                    for (int i = 1; i < incomeTypeRate.size(); i++) {
                        if (incomeTypeRate.get(i).getRate().compareTo(totalIncomeGrowth)== 1 && comIncomeBud.get(i).getExecution().compareTo(incomeBudExe)==-1) {
                            count++;
                        }
                        if (incomeTypeRate.get(i).getRate().compareTo(totalIncomeGrowth)== -1 && comIncomeBud.get(i).getExecution().compareTo(incomeBudExe)== -1) {
                            total++;
                        }
                    }
                    map.put("indexCode", "FINC0003");
                    if (count != 0) {
                        map.put("type", 1);
                        TabsVo tabsDown1 = finTabsService.selectTabs(map);
                        tabsDown1.setDes(count + tabsDown1.getDes());
                        result.put("tabsDown1", tabsDown1);
                    } else if(total != 0) {
                        map.put("type", -1);
                        TabsVo tabsDown1 = finTabsService.selectTabs(map);
                        tabsDown1.setDes(total + tabsDown1.getDes());
                        result.put("tabsDown1", tabsDown1);
                    }else {
                        map.put("type", 1);
                        TabsVo tabsDown1 = finTabsService.selectTabs(map);
                        tabsDown1.setDes(0 + tabsDown1.getDes());
                        result.put("tabsDown1", tabsDown1);
                    }

                    //end of budgetType == 1
                }
            }
        } else if (myData.getBudgetType().equals("2"))

            {

                //当年累计支出
                table = new Hashtable();
                table.put("month", month);
                table.put("indexCode", "FIND0036");
                if (null != myData && null != myData.getComCode()) {
                    table.put("comCode", myData.getComCode());
                }
                List<IncomeBudgetVo> totalExp = finExpenditureSerivice.selectTotalExp(table);
                result.put("totalExp", totalExp);

                //当年累计支出 往年同比
                table = new Hashtable();
                table.put("month", month);
                table.put("indexCode", "FINC0045");
                table.put("comCode", comCode);
                List<IncomeBudgetVo> list = finRateService.selectExpRate(table);
           /* BigDecimal expRate = list.get(0).getYtdN()
                    .divide(list.get(0).getYtdD(), 3, BigDecimal.ROUND_HALF_UP)
                    .subtract(BigDecimal.valueOf(1));*/
                    BigDecimal expRate = null ;
                if (null != list && list.size() > 0) {
                    expRate = list.get(0).getRate();
                }
                if(expRate!=null) {
                    result.put("expRate", expRate);
                }else {
                    result.put("expRate", 0);
                }

                    //支出预算执行率
                    table = new Hashtable();
                    table.put("periodCode", month);
                    table.put("indexCode", "FINC0022");
                    table.put("comCode", comCode);
                    List<FinanceExecutionVo> list1 = financeExecutionService.queryExpExecution(table);
                    BigDecimal expExecution = list1.get(0).getExecution();
            /*BigDecimal expExecution = expExecutionlist.get(0).getYtdN()
                    .divide(expExecutionlist.get(0).getYtdD(), 3, BigDecimal.ROUND_HALF_UP)
                    .subtract(BigDecimal.valueOf(1));*/
                    result.put("expExecution", expExecution);


                    //支出预算执行率 往年同比
                    table = new Hashtable();
                    table.put("indexCode", "FINC0022");
                    table.put("periodCode", Integer.parseInt(month.split("-")[0]) - 1 + "-" + month.split("-")[1]);
                    List<FinanceExecutionVo> expExecutionBefore = financeExecutionService.queryExpExecution(table);
                    if (expExecutionBefore.size() != 0) {
                        BigDecimal expExeRate = list1.get(0).getExecution()
                                .subtract(expExecutionBefore.get(0).getExecution());
                        result.put("expExeRate", expExeRate);
                    } else {
                        result.put("expExeRate", list1.get(0).getExecution());
                    }

                    //项目支出 柱状图
                    table = new Hashtable();
                    table.put("periodCodeNow", month);
                    table.put("periodCodeBefore", Integer.parseInt(month.split("-")[0]) + "-1");
                    table.put("comCode", comCode);
                    table.put("month", month);
                    List<IncomeBudgetVo> comExpend = finExpenditureSerivice.selectExpMonth(table);
                    result.put("comExpend", comExpend);

                    //支出预算执行率 折线图
                    table = new Hashtable();
                    table.put("month", month);
                    table.put("indexCode", "FINC0022");
                    table.put("comCode", comCode);
                    List<IncomeBudgetVo> expBudExecution = incomeBudgetExecutionService.expBudExeMonth(table);
                    result.put("expBudExecution", expBudExecution);

                    //支出同比增长 折线图
                    table = new Hashtable();
                    table.put("month", month);
                    table.put("indexCode", "FINC0045");
                    table.put("comCode", comCode);
                    List<IncomeBudgetVo> expRateMonth = incomeBudgetExecutionService.expRateMonth(table);
                    result.put("expRateMonth", expRateMonth);

                    //整体支出计划增长 虚线
                    table = new Hashtable();
                    table.put("periodCode", month);
                    table.put("indexCode", "FINC0068");
                    table.put("comCode",comCode);
                    List<GrowthVo> list2 = finGrowthService.quesryExpGrowth(table);
                    BigDecimal totalGrowth = list2.get(0).getYtdN()
                            .divide(list2.get(0).getYtdD(), 4, BigDecimal.ROUND_HALF_UP)
                            .subtract(BigDecimal.valueOf(1));
                    result.put("totalGrowth", totalGrowth);

                    //整体支出预算执行率 虚线
                    table.put("periodCode", month);
                    table.put("indexCode", "FINC0022");
                    List<IncomeBudgetVo> totalExpBudExe = incomeBudgetExecutionService.queryTotalExpExe(table);
                    result.put("totalExpBudExe", totalExpBudExe);

                    //支出预算执行率 柱状图
                    table.put("month", month);
                    table.put("indexCode", "FINC0022");
                    table.put("comCode", comCode);
                    List<IncomeBudgetVo> comExpBudExe = incomeBudgetExecutionService.queryComExpBudExe(table);
                    result.put("comExpBudExe", comExpBudExe);

                    //机构支出同比增长 Y坐标
                    table.put("indexCode", "FINC0045");
                    table.put("month", month);
                    List<IncomeBudgetVo> comExpRate = incomeBudgetExecutionService.queryComExpRate(table);

                    //机构支出计划增长 X坐标
                    table.put("indexCode", "FINC0068");
                    List<IncomeBudgetVo> comExpGrowth = incomeBudgetExecutionService.queryComExpGrowth(table);

                    //机构支出 圆点数据
                    table.put("month", month);
                    table.put("indexCode", "FIND0036");
                    List<IncomeBudgetVo> comExp = incomeBudgetExecutionService.queryComExp(table);
                    for (int i = 0; i < comExp.size(); i++) {
                        for (IncomeBudgetVo temp : comExpRate) {
                            if (comExp.get(i).getComCode().equals(temp.getComCode())) {
                                comExp.get(i).setRate(temp.getRate());
                            }
                        }
                    }
                    for (int i = 0; i < comExp.size(); i++) {
                        for (IncomeBudgetVo temp : comExpGrowth) {
                            if (comExp.get(i).getComCode().equals(temp.getComCode())) {
                                comExp.get(i).setGrowthRate(temp.getGrowthRate());
                            }
                        }
                    }
                    for (int i = 0; i < comExp.size(); i++) {
                        if (comExp.get(i).getRate() == null) {
                            comExp.get(i).setRate(BigDecimal.valueOf(0));
                        }
                        if (comExp.get(i).getGrowthRate() == null) {
                            comExp.get(i).setGrowthRate(BigDecimal.valueOf(0));
                        }
                    }
                    result.put("comExp", comExp);

                    //项目支出同比增长 Y坐标
                    table = new HashMap();
                    table.put("month", month);
                    table.put("comCode", comCode);
                    List<IncomeBudgetVo> expTypeRate = incomeBudgetExecutionService.queryExpTypeRate(table);

                    //项目支出计划增长 X坐标
                    List<IncomeBudgetVo> expTypeGrowthRate = incomeBudgetExecutionService.queryExpTypeGrowthRate(table);

                    //项目支出 圆点数据
                    List<IncomeBudgetVo> expTypeExp = incomeBudgetExecutionService.queryExpTypeExp(table);
                    for (int i = 0; i < expTypeExp.size(); i++) {
                        if (expTypeRate.get(i) == null) {
                            expTypeExp.get(i).setRate(BigDecimal.valueOf(0));
                        } else {
//                            if() {
                                expTypeExp.get(i).setRate(expTypeRate.get(i).getRate());
//                            }
                        }
                    }
                    for (int i = 0; i < expTypeExp.size(); i++) {
                        if (expTypeGrowthRate.get(i) == null) {
                            expTypeExp.get(i).setGrowthRate(BigDecimal.valueOf(0));
                        } else {
                            expTypeExp.get(i).setGrowthRate(expTypeGrowthRate.get(i).getRate());
                        }
                    }
                    result.put("expTypeExp", expTypeExp);

            /*筛选标签*/
                    Map map = new HashMap();
                    //初始化机构数量
                    Integer count = 0;
                    //当年累计支出同比 比较 计划增长  expRate totalGrowth
                    int typeRate = 0;
                    if(expRate!=null) {
                         typeRate = expRate.compareTo(totalGrowth);
                    }
                    map.put("indexCode", "FINC0068");
                    map.put("type", typeRate);
                    TabsVo tabsUp = finTabsService.selectTabs(map);
                    result.put("tabsUp", tabsUp);

                    //机构支出预算执行率 比较 整体支出预算执行率 comExpBudExe expExecution
                    map.put("indexCode", "FINC0022");
                    for (IncomeBudgetVo temp : comExpBudExe) {
                        int typeExe = temp.getExecution().compareTo(expExecution);
                        if (typeExe == -1) count++;
                    }
                    if (count == 0) {
                        map.put("type", 1);
                        TabsVo tabsDown = finTabsService.selectTabs(map);
                        tabsDown.setDes(companys + tabsDown.getDes());
                        result.put("tabsDown", tabsDown);
                    } else {
                        map.put("type", -1);
                        TabsVo tabsDown = finTabsService.selectTabs(map);
                        tabsDown.setDes(count + tabsDown.getDes());
                        result.put("tabsDown", tabsDown);
                    }

                    //初始化机构数量
                    count = 0;
                    //机构支出同比 比较 机构计划增长 comExpRate comExpGrowth
                    map.put("indexCode", "FINC0069");
                    for (int i = 0; i < comExpRate.size(); i++) {
                        int typeRateToGrowth = comExpRate.get(i).getRate().compareTo(comExpGrowth.get(i).getGrowthRate());
                        if (typeRateToGrowth == -1) count++;
                    }
                    if (count == 0) {
                        map.put("type", 1);
                        TabsVo tabsUp1 = finTabsService.selectTabs(map);
                        tabsUp1.setDes(comExpRate.size() + tabsUp1.getDes());
                        result.put("tabsUp1", tabsUp1);
                    } else {
                        map.put("type", -1);
                        TabsVo tabsUp1 = finTabsService.selectTabs(map);
                        tabsUp1.setDes(count + tabsUp1.getDes());
                        result.put("tabsUp1", tabsUp1);
                    }

                    //初始化机构数量
                    count = 0;
                    //项目支出同比 比较 机构计划增长 expTypeRate expTypeGrowthRate
                    map.put("indexCode", "FINC0067");
                    for (int i = 0; i < expTypeRate.size(); i++) {
                        int typeTypeRate = expTypeRate.get(i).getRate().compareTo(expTypeGrowthRate.get(i).getRate());
                        if (typeTypeRate == -1) count++;
                    }
                    if (count == 0) {
                        map.put("type", 1);
                        TabsVo tabsDown1 = finTabsService.selectTabs(map);
                        tabsDown1.setDes(expTypeRate.size() + tabsDown1.getDes());
                        result.put("tabsDown1", tabsDown1);
                    } else {
                        map.put("type", -1);
                        TabsVo tabsDown1 = finTabsService.selectTabs(map);
                        tabsDown1.setDes(count + tabsDown1.getDes());
                        result.put("tabsDown1", tabsDown1);
                    }

            }

        /*tabsVo.setDes("当年累计支出同比增长低于计划增长，支出预算执行情况不及预期。");
        tabsVo.setColor("yellow");
        result.put("tabsUp",tabsVo);
        tabsVo = new TabsVo();
        tabsVo.setDes("共13个机构支出预算执行率高于整体水平，有超支风险。");
        tabsVo.setColor("yellow");
        result.put("tabsDown",tabsVo);
        tabsVo = new TabsVo();
        tabsVo.setDes("3家机构支出同比增长高于其计划增长，有超支风险。");
        tabsVo.setColor("yellow");
        result.put("tabsUp1",tabsVo);
        tabsVo = new TabsVo();
        tabsVo.setDes("3个支出类型支出同比增长高于其计划增长，有超支风险。");
        tabsVo.setColor("yellow");
        result.put("tabsDown1",tabsVo);*/
        return new Result().success(result);
    }
}