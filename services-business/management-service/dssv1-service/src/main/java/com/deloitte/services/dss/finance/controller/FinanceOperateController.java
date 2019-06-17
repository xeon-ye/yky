package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.GrowthVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.api.dss.finance.vo.ParamVo;
import com.deloitte.platform.api.dss.finance.vo.TabsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.service.*;
import com.deloitte.services.dss.util.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description :   Income控制器实现类
 * @Modified :
 */
@Api(tags = "12.运营情况")
@Slf4j
@RestController
@RequestMapping("/dssv1/finance/opearte")
public class FinanceOperateController {
    @Autowired
    public IFinIncomeService finIncomeService;
    @Autowired
    public IFinRateService finRateService;
    @Autowired
    public IFinGrowthService finGrowthService;
    @Autowired
    public IFinExpenditureSerivice finExpenditureSerivice;
    @Autowired
    private IFinTabsService finTabsService;
    @Autowired
    private IFinCompanyService finCompanyService;

    @ApiOperation(value = "运营情况 - 收入分析 - 整体情况", notes = "运营情况 - 收入分析 - 整体情况")
    @PostMapping("/opearteIncome")
    public Result opearteIncome(@Valid @RequestBody ParamVo paramVo) {
        Map result = new HashMap();
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR)+"";
        String month = calendar.get(Calendar.MONTH) + 1 + "";
        String myMonth = TimeUtil.getTime();
        List<String> comCode = paramVo.getComCode();
        //当年累计收入金额
        Map map = new HashMap();
        map.put("month", TimeUtil.getTime());
        map.put("indexCode", "FIND0007");
        map.put("comCode", comCode);
        List<IncomeBudgetVo> incomeBudgetYear = finIncomeService.selectTotalIncome(map);
        result.put("incomeBudgetYear", incomeBudgetYear);
       /* if(null != incomeBudgetYear && incomeBudgetYear.size() > 0 ){
            result.put("incomeBudgetYearAmount",incomeBudgetYear.get(0).getYtd());
        }*/

        //当年累计收入同比
        map = new Hashtable<String, String>();
        map.put("month", TimeUtil.getTime());
        map.put("indexCode", "FINC0007");
        map.put("comCode", paramVo.getComCode());
        List<IncomeBudgetVo> incomeBudgetYearList = finRateService.selectRate(map);
        if(null != incomeBudgetYearList && incomeBudgetYearList.size()>0){
        result.put("incomeBudgetYearList", incomeBudgetYearList);
        /*if(null != incomeBudgetYearList && incomeBudgetYearList.size() > 0 ){
            result.put("incomeBudgetYearRate",incomeBudgetYearList.get(0).getYtdN().divide(incomeBudgetYearList.get(0).getYtdD(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1")));
        }*/

        //当月累计收入金额
        map = new HashMap<String, String>();
        map.put("month", year + "-" + month);
        map.put("indexCode", "FIND0007");
        List<IncomeBudgetVo> incomeBudgetMonth = finIncomeService.selectTotalIncome(map);
        result.put("incomeBudgetMonth", incomeBudgetMonth);
       /* if(null != incomeBudgetMonth && incomeBudgetMonth.size() > 0 ){
            result.put("incomeBudgetMonthAmount",incomeBudgetMonth.get(0).getPtd());
        }*/
        //当月累计收入同比
        map = new Hashtable<String, String>();
        map.put("month", year + "-" + month);
        map.put("indexCode", "FINC0007");
        List<IncomeBudgetVo> incomeBudgetMonthList = finRateService.selectRate(map);
        result.put("incomeBudgetMonthList", incomeBudgetMonthList);
       /* if(null != incomeBudgetMonthList && incomeBudgetMonthList.size() > 0 ){
            result.put("incomeBudgetMonthRate",incomeBudgetMonthList.get(0).getYtdN().divide(incomeBudgetMonthList.get(0).getYtdD(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1")));
        }*/


        //当年收入整体分布情况和同比 FIND0007
        map = new HashMap<>();
        map.put("month", year + "-" + month);
        map.put("comCode", paramVo.getComCode());
        map.put("indexCode", "FIND0007");
        List<IncomeBudgetVo> incomeBudgetGroupMonth = finIncomeService.selectComIncomeMonth(map);
        result.put("incomeBudgetGroupMonth", incomeBudgetGroupMonth);

        //环比上月
        if(null != incomeBudgetGroupMonth && incomeBudgetGroupMonth.size()>0) {
            if (myMonth != "1") {
                int size = incomeBudgetGroupMonth.size();
                BigDecimal sequential = incomeBudgetGroupMonth.get(size - 1).getPtd()
                        .divide(incomeBudgetGroupMonth.get(size - 2).getPtd(), 4, BigDecimal.ROUND_HALF_UP)
                        .subtract(BigDecimal.valueOf(1));
                result.put("sequential", sequential);
            } else {
                String str = Integer.parseInt(TimeUtil.getYear().split("-")[0]) - 1 + "-12";
                Map newMap = new HashMap();
                newMap.put("indexCode", "FIND0007");
                newMap.put("month", str);
                newMap.put("comCode", comCode);
                List<IncomeBudgetVo> list1 = finIncomeService.selectTotalIncome(newMap);
                BigDecimal sequential = incomeBudgetGroupMonth.get(0).getYtd()
                        .divide(list1.get(list1.size() - 1).getYtd(), 3, BigDecimal.ROUND_HALF_UP)
                        .subtract(BigDecimal.valueOf(1));
                result.put("sequential", sequential);
            }
        }


        //每月收入同比增长 折线图
        map = new HashMap();
        map.put("indexCode","FINC0007");
        map.put("month",myMonth);
        map.put("comCode",comCode);
        List<IncomeBudgetVo> totalRateMonth = finRateService.selectComRateMonth(map);
        result.put("totalRateMonth",totalRateMonth);

        //每月财政收入同比增长 折线图
        map.put("indexCode","FINC0009");
        List<IncomeBudgetVo> list = finRateService.selectComRateMonth(map);
        result.put("fcRateMonth",list);


        //整体收入计划增长 FINC0013
        map = new Hashtable();
        map.put("periodCode",TimeUtil.getTime());
        map.put("indexCode","FINC0013");
        map.put("comCode",comCode);
        List<GrowthVo> totalRate = finGrowthService.quesryGrowth(map);
        if(null != totalRate && totalRate.size()>0) {
            result.put("totalRate", totalRate);
       /* if(null != totalRate && totalRate.size() > 0 ){
            result.put("totalRate",totalRate.get(0).getYtdN().divide(totalRate.get(0).getYtdD(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1")));
        }*/

            //运营情况 收入分析 筛选标签
            //计算当年累计收入同比
            BigDecimal bigBudget = incomeBudgetYearList.get(0).getRate();
            //计算当年收入计划增长
            BigDecimal bigRate = totalRate.get(0).getYtdN()
                    .divide(totalRate.get(0).getYtdD(), 3, BigDecimal.ROUND_HALF_UP)
                    .subtract(BigDecimal.valueOf(1));
            //当年累计收入同比 比较 计算当年收入计划增长
//            int type = bigBudget.compareTo(bigRate);
            int type = bigBudget.compareTo(bigRate);
            map = new HashMap();
            map.put("type", type);
            map.put("indexCode", "FINC0013");
            TabsVo tabsUp = finTabsService.selectTabs(map);
            result.put("tabsUp", tabsUp);
            //去年累计收入同比
            map = new Hashtable<String, String>();
//            map.put("month", Integer.parseInt(year) - 1 + "-" + month);
            map.put("month",year+"-"+(Integer.parseInt(month)-1));
            map.put("indexCode", "FINC0007");
            map.put("comCode", comCode);
            List<IncomeBudgetVo> incomeBudgetYearListBefore = finRateService.selectRate(map);
            BigDecimal bigBudgetBefore = BigDecimal.valueOf(1);
            if (null != incomeBudgetYearListBefore && incomeBudgetYearListBefore.size() > 0) {
                bigBudgetBefore = incomeBudgetYearListBefore.get(0).getRate();
            }
            //当年累计收入同比 比较 去年累计收入同比
            int type1 = bigBudget.compareTo(bigBudgetBefore);
//            int type1 = bigBudgetBefore.compareTo(bigBudget);
            map = new HashMap();
            map.put("type", type1);
            map.put("indexCode", "FINC0012");
        }
        }
        TabsVo tabsDown = finTabsService.selectTabs(map);
        result.put("tabsDown",tabsDown);

        return new Result().sucess(result) ;
    }

    @ApiOperation(value = "运营情况 - 收入分析 - 收入结构情况", notes = "运营情况 - 收入分析 - 收入结构情况")
    @PostMapping("/opearteIncomeStruct")
    public Result opearteIncomeItem(@Valid @RequestBody ParamVo paramVo) {
        Map result = new HashMap();
        String year = TimeUtil.getYear();
        String month = TimeUtil.getTime();
        List<String> comCode = paramVo.getComCode();
        Integer companys = finCompanyService.selectCompany().size();
        Map paramMap = new HashMap();
        paramMap.put("month", month);
        paramMap.put("comCode", paramVo.getComCode());
        List<IncomeBudgetVo> incomeNow = finIncomeService.selectTotalIncome(paramMap);
        if (null != incomeNow && incomeNow.size() > 0) {
            for (int i = 0; i < incomeNow.size(); i++) {
                incomeNow.get(i).setPeriodCode(year);
            }
        }
        String periodCode = Integer.parseInt(TimeUtil.getYear()) - 1 + "-" + TimeUtil.getMonth();
        paramMap.put("month", periodCode);
        List<IncomeBudgetVo> incomeBefore = finIncomeService.selectTotalIncome(paramMap);
        if (null != incomeBefore && incomeBefore.size() > 0){
            for (int i = 0; i < incomeBefore.size(); i++) {
                incomeBefore.get(i).setPeriodCode(periodCode);
            }
    }
        result.put("incomeNow",incomeNow);
        result.put("incomeBefore",incomeBefore);



        /*收入结构情况 筛选标签*/
        //获取整体收入同比增长
        paramMap = new Hashtable();
        paramMap.put("month",month);
        paramMap.put("indexCode","FINC0007");
        List<IncomeBudgetVo> incomeBudgetMonthList = finRateService.selectRate(paramMap);
        //计算当年累计收入同比
        if(null != incomeBudgetMonthList && incomeBudgetMonthList.size()>0) {
            BigDecimal bigBudget = incomeBudgetMonthList.get(0).getRate();
            //获取财政收入同比增长
            paramMap.put("month", month);
            paramMap.put("indexCode", "FINC0009");
            List<IncomeBudgetVo> fsscRate = finRateService.selectRate(paramMap);
            BigDecimal fsscBudget = fsscRate.get(0).getRate();
            //财政收入同比增长 比较 当年累计收入同比
            int type = fsscBudget.compareTo(bigBudget);
            paramMap.put("type", type);
            paramMap.put("indexCode", "FINC0009");
            TabsVo tabsUp = finTabsService.selectTabs(paramMap);

            BigDecimal fsscYtd = BigDecimal.valueOf(0);
            BigDecimal ytd = BigDecimal.valueOf(0);
            //获取当年财政收入占比 和总收入
            for (IncomeBudgetVo temp : incomeNow) {
                if ("FIND0009".equals(temp.getIndexCode())) {
                    fsscYtd = temp.getYtd();
                } else if ("FIND0007".equals(temp.getIndexCode())) {
                    ytd = temp.getYtd();
                }
            }
            //计算当年财政收入占比
            BigDecimal divideNow = fsscYtd.divide(ytd, 3, BigDecimal.ROUND_HALF_UP);

            //获取去年年财政收入占比 和总收入
            for (IncomeBudgetVo temp : incomeBefore) {
                if ("FIND0009".equals(temp.getIndexCode())) {
                    fsscYtd = temp.getYtd();
                } else if ("FIND0007".equals(temp.getIndexCode())) {
                    ytd = temp.getYtd();
                }
            }
            //获取去年财政收入占比
            BigDecimal divideBefore = fsscYtd.divide(ytd, 3, BigDecimal.ROUND_HALF_UP);
            //当年财政收入占比 比较 去年财政收入占比
            int type2 = divideNow.compareTo(divideBefore);
            paramMap = new HashMap();
            paramMap.put("type", type2);
            paramMap.put("indexCode", "FINC0010");
            TabsVo tabsUp1 = finTabsService.selectTabs(paramMap);
            tabsUp.setDes(tabsUp.getDes() + tabsUp1.getDes());
            result.put("tabsUp", tabsUp);

            //获取机构当年累计收入同比
            paramMap = new HashMap();
            paramMap.put("month", month);
            paramMap.put("comCode", comCode);
            paramMap.put("indexCode", "FINC0007");
            List<IncomeBudgetVo> list = finRateService.selectComYearRate(paramMap);

            //遍历 同比集合 并与整体同比进行比较
            //初始化机构数量
            Integer count = 0;
            if(null != list && list.size()>0) {
                for (int i = 0; i < list.size(); i++) {
                    int type3 = list.get(i).getRate().compareTo(bigBudget);
                    if (type3 == -1) count++;
                }

                TabsVo tabsDown = new TabsVo();
                paramMap.put("indexCode", "FINC0008");
                if (count == 0) {
                    paramMap.put("type", 1);
                    tabsDown = finTabsService.selectTabs(paramMap);
                    tabsDown.setDes(list.size() + tabsDown.getDes());
                } else {
                    paramMap.put("type", -1);
                    tabsDown = finTabsService.selectTabs(paramMap);
                    tabsDown.setDes(count + tabsDown.getDes());
                }
                result.put("tabsDown", tabsDown);

                paramMap = new HashMap();
                paramMap.put("month", month);
//                paramMap.put("comCode", comCode);
                paramMap.put("indexCode", "FINC0007");
                Map map = new HashMap();
                map.put("month", TimeUtil.getTime());
                map.put("indexCode", "FIND0007");
//                map.put("comCode", comCode);
                List<IncomeBudgetVo> incomeBudgetYear = finIncomeService.selectIncomeTotal(map);
                List<IncomeBudgetVo> incomeBudgetVoList = finRateService.selectComYearRate(paramMap);
                //计算区间
                //获取最小和最大值
                BigDecimal minRate = incomeBudgetVoList.get(0).getRate().setScale(2, BigDecimal.ROUND_DOWN);
                BigDecimal maxRate = incomeBudgetVoList.get(incomeBudgetVoList.size() - 1).getRate().setScale(2, BigDecimal.ROUND_UP);
                //计算总数
                BigDecimal total = maxRate.subtract(minRate).add(BigDecimal.valueOf(0.01));
                //计算增量
                BigDecimal num = total.divide(BigDecimal.valueOf(5), 2, BigDecimal.ROUND_UP).subtract(BigDecimal.valueOf(0.01));
                //获取5个区间
                BigDecimal left = minRate;
                BigDecimal right = left.add(num);
                List sections = new ArrayList();
                for (int i = 0; i < 5; i++) {
                    TabsVo section = new TabsVo();
                    if (i == 0) {
                        section.setLeft(left);
                        section.setRight(right);
                        count = 0;
                        for (IncomeBudgetVo temp : incomeBudgetVoList) {
                            if ((temp.getRate().compareTo(left) == 1 || temp.getRate().compareTo(left) == 0)
                                    && (temp.getRate().compareTo(right) == -1 || temp.getRate().compareTo(right) == 0)) {
                                section.setComCode(temp.getComCode());
                                section.setComDes(temp.getComDes());
                                if(null != incomeBudgetYear && incomeBudgetYear.size()>0) {
                                    for (IncomeBudgetVo incomeBudgetVo : incomeBudgetYear) {
                                        if (temp.getComCode().equals(incomeBudgetVo.getComCode())) {
                                            section.setYtd(incomeBudgetVo.getYtd());
                                        }
                                    }
                                }
//                                section.setYtd(new BigDecimal(123));
//                                section.setPtd(new BigDecimal(123));
                                count++;
                            }
                        }
                        section.setCount(count);
                    } else {
                        left = right.add(BigDecimal.valueOf(0.01));
                        right = left.add(num);
                        section.setLeft(left);
                        section.setRight(right);
                        count = 0;
                        for (IncomeBudgetVo temp : incomeBudgetVoList) {
                            if ((temp.getRate().compareTo(left) == 1 || temp.getRate().compareTo(left) == 0)
                                    && (temp.getRate().compareTo(right) == -1 || temp.getRate().compareTo(right) == 0)) {
                                section.setComCode(temp.getComCode());
                                section.setComDes(temp.getComDes());
                                if(null != incomeBudgetYear && incomeBudgetYear.size()>0) {
                                    for (IncomeBudgetVo incomeBudgetVo : incomeBudgetYear) {
                                        if (temp.getComCode().equals(incomeBudgetVo.getComCode())) {
                                            section.setYtd(incomeBudgetVo.getYtd());
                                        }
                                    }
                                }
//                                section.setYtd(new BigDecimal(123));
//                                section.setPtd(new BigDecimal(123));
                                count++;
                            }
                        }
                        section.setCount(count);
                    }
                    sections.add(section);
                }
                result.put("sections", sections);
            }
        }

        return new Result().sucess(result);
    }

    @ApiOperation(value = "运营情况 - 支出分析 - 支出整体情况", notes = "运营情况 - 支出分析 - 支出整体情况")
    @PostMapping("/opearteExpStruct")
    public Result opearteExpStruct(@Valid @RequestBody ParamVo paramVo){
        Map paramMap = new HashMap();
        Map result = new HashMap();
        String year = TimeUtil.getYear();
        String month = TimeUtil.getTime();
        Integer companys = finCompanyService.selectCompany().size();
        List<String> comCode = paramVo.getComCode();

        //当年累计支出
        paramMap.put("indexCode","FIND0036");
        paramMap.put("comCode",comCode);
        paramMap.put("month",month);
        List<IncomeBudgetVo> totalExp = finExpenditureSerivice.selectTotalExp(paramMap);
        result.put("totalExp",totalExp);

        //当年累计支出 往年同比
        paramMap.put("periodCode",month);
        paramMap.put("indexCode","FINC0045");
        List<IncomeBudgetVo> list = finRateService.selectExpRate(paramMap);
        /*BigDecimal totalExpRate = list.get(0).getYtdN()
                .divide(list.get(0).getYtdD(), 3, BigDecimal.ROUND_HALF_UP)
                .subtract(BigDecimal.valueOf(1));*/
        if(null != list && list.size()>0) {
            BigDecimal totalExpRate = list.get(0).getRate();
            result.put("totalExpRate", totalExpRate);


            //当月支出
            paramMap = new HashMap();
            paramMap.put("month", month);
            paramMap.put("comCode", comCode);
            paramMap.put("indexCode", "FIND0036");
            List<IncomeBudgetVo> monthExp = finExpenditureSerivice.selectExpPerMonth(paramMap);
            result.put("monthExp", monthExp);

            //当月环比
            String monthBefore = null;
            if (month.split("-")[1].equals("1")) {
                monthBefore = Integer.parseInt(year) - 1 + "-12";
            } else {
                monthBefore = month.split("-")[0] + "-" + (Integer.parseInt(month.split("-")[1]) - 1 + "");
            }
            paramMap.put("month", monthBefore);
            List<IncomeBudgetVo> monthExpBefore = finExpenditureSerivice.selectExpPerMonth(paramMap);
            BigDecimal monthExpRate = null;
            if (monthExpBefore.size() > 0 && monthExpBefore != null) {
                monthExpRate = monthExp.get(0).getPtd()
                        .divide(monthExpBefore.get(0).getPtd(), 5, BigDecimal.ROUND_HALF_UP)
                        .subtract(BigDecimal.valueOf(1));
            } else {
                monthExpRate = BigDecimal.valueOf(1);
            }
            BigDecimal bigDecimal = monthExpRate.setScale(4, BigDecimal.ROUND_HALF_UP);
            result.put("monthExpRate", bigDecimal);

            //累计支出 柱状图
            paramMap = new HashMap();
            paramMap.put("month", month);
            paramMap.put("comCode", comCode);
            paramMap.put("indexCode", "FIND0036");
            List<IncomeBudgetVo> expComPerMonth = finExpenditureSerivice.expComPerMonth(paramMap);
            result.put("expComPerMonth", expComPerMonth);

            //支出同比增长 折线图
            paramMap.put("indexCode", "FINC0045");
            List<IncomeBudgetVo> expComRate = finExpenditureSerivice.selectExpComRate(paramMap);
            result.put("expComRate", expComRate);

            //整体支出计划增长 虚线
            paramMap = new HashMap();
            paramMap.put("periodCode", month);
            paramMap.put("indexCode", "FINC0068");
            paramMap.put("comCode", comCode);
            List<GrowthVo> list1 = finGrowthService.quesryExpGrowth(paramMap);
            BigDecimal totalGrowth = list1.get(0).getYtdN()
                    .divide(list1.get(0).getYtdD(), 4, BigDecimal.ROUND_HALF_UP)
                    .subtract(BigDecimal.valueOf(1));
            result.put("totalGrowth", totalGrowth);

            //项目累计支出 柱状图
            paramMap = new HashMap();
            paramMap.put("month", month);
            paramMap.put("comCode", comCode);
            paramMap.put("indexCode", "FIND0040");
            List<IncomeBudgetVo> profectExp = finExpenditureSerivice.expComPerMonth(paramMap);
            result.put("profectExp", profectExp);

            //项目支出同比增长 折线图
            paramMap.put("indexCode", "FINC0049");
            List<IncomeBudgetVo> profectExpRate = finExpenditureSerivice.selectExpComRate(paramMap);
            result.put("profectExpRate", profectExpRate);

            //项目支出占比 折线图


            //机构支出同比增长 Y坐标
            paramMap.put("indexCode", "FINC0045");
            List<IncomeBudgetVo> expRateY = finExpenditureSerivice.selectExpComRateY(paramMap);
        /*result.put("expRateY",expRateY);*/

            //机构支出计划增长 X坐标  FIND0036
            paramMap.put("indexCode", "FINC0068");
            List<IncomeBudgetVo> expComGrowthX = finExpenditureSerivice.selectExpComGrowthX(paramMap);
        /*result.put("expComGrowthX",expComGrowthX);*/

            //支出 圆点面积 FIND0036
            paramMap.put("month", month);
            paramMap.put("indexCode", "FIND0036");
            List<IncomeBudgetVo> expCarcle = finExpenditureSerivice.selectExpCarcle(paramMap);
        /*result.put("expCarcle",expCarcle);*/

            for (int i = 0; i < expRateY.size(); i++) {
                for (IncomeBudgetVo temp : expComGrowthX) {
                    if (expRateY.get(i).getComCode().equals(temp.getComCode())) {
                        expRateY.get(i).setGrowthRate(temp.getRate());
                    }
                }
            }
            for (int i = 0; i < expRateY.size(); i++) {
                for (IncomeBudgetVo temp : expCarcle) {
                    if (expRateY.get(i).getComCode().equals(temp.getComCode())) {
                        expRateY.get(i).setYtd(temp.getYtd());
                    }
                }
            }
            for (int i = 0; i < expRateY.size(); i++) {
                if (expRateY.get(i).getYtd() == null) {
                    expRateY.get(i).setYtd(BigDecimal.valueOf(0));
                }
            }
            result.put("expRateY", expRateY);

        /*支出分析 筛选标签*/
            //年累计支出同比 与 计划增长 比较 totalExpRate totalGrowth
            int type = totalExpRate.compareTo(totalGrowth);
            paramMap = new HashMap();
            paramMap.put("type", type);
            paramMap.put("indexCode", "FINC0045");
            TabsVo tabsUp = finTabsService.selectTabs(paramMap);
            result.put("tabsUp", tabsUp);

            //获取上月年累计支出同比增长
            paramMap.put("indexCode", "FINC0045");
            paramMap.put("comCode", comCode);
            Integer reMonth = Integer.parseInt(month.split("-")[1]);
            String monthPrev;
            if (reMonth == 1) {
                monthPrev = Integer.parseInt(month.split("-")[1]) - 1 + "-12";
            } else {
                monthPrev = month.split("-")[0] + "-" + (reMonth - 1);
            }
            paramMap.put("month", monthPrev);

            List<IncomeBudgetVo> list2 = finRateService.selectExpRate(paramMap);

            TabsVo tabsDown = new TabsVo();
            paramMap.put("indexCode", "FINC0046");

            //如果上个月无年累计收入同比数据 则当月同比大于上月同比
            if (null == list2 || (null != list2 && list2.size() == 0)) {
                paramMap.put("type", 1);
                tabsDown = finTabsService.selectTabs(paramMap);
            } else {
                BigDecimal temp = list2.get(0).getRate();
                int type1 = totalExpRate.compareTo(temp);
                paramMap.put("type", type1);
                tabsDown = finTabsService.selectTabs(paramMap);
            }
            result.put("tabsDown", tabsDown);


            //项目支出同比增长 比较 整体支出计划增长 profectExpRate totalGrowth
            //获取当月支出同比增长数据
            int size = profectExpRate.size() - 1;
            BigDecimal totalRate = profectExpRate.get(size).getRate();
            //进行比较 FINC0049
            int type2 = totalRate.compareTo(totalGrowth);
            paramMap.put("indexCode", "FINC0049");
            paramMap.put("type", type2);
            TabsVo tabsUp1 = finTabsService.selectTabs(paramMap);

            //当月项目累计支出占比 比较 上月项目累计支出占比 profectExp  expComPerMonth
            //获取当月项目累计支出占比
            int lastIndex = profectExp.size() - 1;
            BigDecimal nowRate = profectExp.get(lastIndex).getYtd()
                    .divide(expComPerMonth.get(lastIndex).getYtd(), 3, BigDecimal.ROUND_HALF_UP);
            //获取上月项目累计支出占比
            int secondIndex = lastIndex - 1;
            BigDecimal beforeRate = profectExp.get(secondIndex).getYtd()
                    .divide(expComPerMonth.get(secondIndex).getYtd(), 3, BigDecimal.ROUND_HALF_UP);
            //进项比较 FIND0040
            int type3 = nowRate.compareTo(beforeRate);
            paramMap.put("indexCode", "FIND0040");
            paramMap.put("type", type3);
            TabsVo tabsVo2 = finTabsService.selectTabs(paramMap);
            tabsUp1.setDes(tabsUp1.getDes() + tabsVo2.getDes());
            result.put("tabsUp1", tabsUp1);


            //机构支出同比增长 比较 计划增长 expRateY expComGrowthX
            Integer count = 0;
            paramMap.put("indexCode", "FINC0047");
            TabsVo tabsVo3 = new TabsVo();

            for (int i = 0; i < expRateY.size(); i++) {
                for (IncomeBudgetVo temp : expComGrowthX) {
                    if (expRateY.get(i).getComCode().equals(temp.getComCode())) {
                        int i1 = expRateY.get(i).getRate().compareTo(temp.getRate());
                        if (i1 == -1) {
                            paramMap.put("type", -1);
                            count++;
                        }
                    }
                }
            }

//            for (int i = 0; i < expRateY.size(); i++) {
//                if (expRateY.get(i).getComCode().equals(expComGrowthX.get(i).getComCode())) {
//                    int i1 = expRateY.get(i).getRate().compareTo(expComGrowthX.get(i).getRate());
//                    if (i1 == -1) {
//                        paramMap.put("type", -1);
//                        count++;
//                    }
//                }
//            }
            if (count<=expRateY.size()/2) {
                paramMap.put("type", 1);
                tabsVo3 = finTabsService.selectTabs(paramMap);
                tabsVo3.setDes((expRateY.size()-count) + tabsVo3.getDes());
            } else {
                tabsVo3 = finTabsService.selectTabs(paramMap);
                tabsVo3.setDes(count + tabsVo3.getDes());
            }
            result.put("tabsDown1", tabsVo3);
        }
        return new Result().sucess(result);
    }
}



