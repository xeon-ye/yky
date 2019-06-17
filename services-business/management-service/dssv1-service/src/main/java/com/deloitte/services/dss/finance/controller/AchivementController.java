package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.api.dss.finance.vo.TabsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.entity.FinEtlPretreatment;
import com.deloitte.services.dss.finance.service.IFinAchivementService;
import com.deloitte.services.dss.finance.service.IFinTabsService;
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
import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description : Budget服务类接口
 * @Modified :
 */
@Api(tags = "13.绩效结果")
@Slf4j
@RestController
@RequestMapping("/dss/finance")
public class AchivementController {
    @Autowired
    private IFinAchivementService finAchivementService;
    @Autowired
    private IFinTabsService finTabsService;

    @ApiOperation(value = "经费管控-绩效结果",notes = "经费管控-绩效结果")
    @PostMapping("/selectAvgAchive")
    public Result selectAvgAchive(@Valid @RequestBody AcceptVo myData){
        String year = TimeUtil.getYear();
        Map map = new HashMap();
        Map result = new HashMap();
        map.put("comCode",myData.getComCode());
        map.put("year",year);


        //平均项目预算绩效
        map.put("indexCode","FINC0091");
        List<IncomeBudgetVo> avgAchive = finAchivementService.selectAvgAchive(map);
        for(int i = 0; i < avgAchive.size(); i++){
            avgAchive.get(i).setPeriodCode(year);
        }
        result.put("avgAchive",avgAchive);

        //科研项目预算绩效
        map.put("indexCode","FINC0092");
        List<IncomeBudgetVo> avgFsscAchive = finAchivementService.selectAvgAchive(map);
        for(int i = 0; i < avgFsscAchive.size(); i++){
            avgFsscAchive.get(i).setPeriodCode(year);
        }
        result.put("avgFsscAchive",avgFsscAchive);

        //平均项目预算绩效 同比
        map.put("indexCode","FINC0093");
        List<IncomeBudgetVo> avgAchiveRate = finAchivementService.selectAvgAchiveRate(map);
            result.put("avgAchiveRate",avgAchiveRate);

        //科研项目预算绩效 同比
        map.put("indexCode","FINC0094");
        List<IncomeBudgetVo> avgFsscAchiveRate = finAchivementService.selectAvgAchiveRate(map);
        result.put("avgFsscAchiveRate",avgFsscAchiveRate);

        //平均项目预算绩效 柱状图
        map.put("year",Integer.parseInt(year) - 1 + "");
        map.put("indexCode","FINC0091");
        List<IncomeBudgetVo> avgAchiveBefore = finAchivementService.selectAvgAchive(map);
        map.put("indexCode","FINC0093");
        List<IncomeBudgetVo> avgAchiveRateBefore = finAchivementService.selectAvgAchive(map);
        IncomeBudgetVo IncomeBudgetVo = new IncomeBudgetVo();
        IncomeBudgetVo.setPeriodCode(Integer.parseInt(year) - 1 + "");
        if(null == avgAchiveBefore || (null != avgAchiveBefore && avgAchiveBefore.size() == 0)){
            IncomeBudgetVo.setYtd(BigDecimal.valueOf(0));
        }
        if(null == avgAchiveRateBefore || (null != avgAchiveRateBefore && avgAchiveRateBefore.size() == 0)){
            IncomeBudgetVo.setRate(BigDecimal.valueOf(0));
        }
        avgAchiveBefore.add(IncomeBudgetVo);
        result.put("avgAchiveBefore",avgAchiveBefore);


        //科研项目预算绩效 柱状图
        map.put("indexCode","FINC0092");
        List<IncomeBudgetVo> avgFsscAchiveBefore = finAchivementService.selectAvgAchive(map);
        map.put("indexCode","FINC0094");
        List<IncomeBudgetVo> avgFsscAchiveRateBefore = finAchivementService.selectAvgAchive(map);
        IncomeBudgetVo IncomeBudgetVobe = new IncomeBudgetVo();
        IncomeBudgetVobe.setPeriodCode(Integer.parseInt(year) - 1 + "");
        if(null == avgFsscAchiveBefore || (null != avgFsscAchiveBefore && avgFsscAchiveBefore.size() == 0)){
            IncomeBudgetVobe.setYtd(BigDecimal.valueOf(0));
        }
        if(null == avgFsscAchiveRateBefore || (null != avgFsscAchiveRateBefore && avgFsscAchiveRateBefore.size() == 0)){
            IncomeBudgetVobe.setRate(BigDecimal.valueOf(0));
        }
        avgFsscAchiveBefore.add(IncomeBudgetVobe);
        result.put("avgFsscAchiveBefore",avgFsscAchiveBefore);

        //去年平均项目预算绩效 同比
        map = new HashMap();
        map.put("indexCode","FINC0093");
        map.put("comCode",myData.getComCode());
        map.put("year",Integer.parseInt(year) - 1 + "");
        List<IncomeBudgetVo> avgBefore = finAchivementService.selectAvgAchiveRate(map);
        result.put("avgBefore",avgBefore);

        map.put("indexCode","FINC0094");
        List<IncomeBudgetVo> avgFsscBefore = finAchivementService.selectAvgAchiveRate(map);
        result.put("avgFsscBefore",avgFsscBefore);

        /*筛选标签*/
        //获取项目平均分
        Map paramMap = new HashMap();
        paramMap.put("type",1);
        paramMap.put("indexCode","FINJX001");
        String str = finTabsService.selectTabs(paramMap).getDes();
        Integer avg = Integer.parseInt(str.substring(str.lastIndexOf("分") - 2, str.lastIndexOf("分")));
        //项目平均绩效 比较 及格线
        //当年平均绩效 avgAchive avg
        int avg1 = avgAchive.get(0).getYtd().compareTo(BigDecimal.valueOf(avg));
        paramMap.put("type",avg1);
        TabsVo tabsUp = finTabsService.selectTabs(paramMap);
        //去年平均绩效 比较 当年平均绩效 avgAchive avgAchiveBefore
        int avg2 = avgAchive.get(0).getYtd().compareTo(avgAchiveBefore.get(0).getYtd());
        paramMap.put("type",avg2);
        paramMap.put("indexCode","FINJX002");
        TabsVo achive2 = finTabsService.selectTabs(paramMap);
        //获取去年年份
        String befaoreYear = (Integer.parseInt(year) - 1) + "";
        paramMap.put("indexCode","FINJX003");
        TabsVo achive3 = finTabsService.selectTabs(paramMap);
        tabsUp.setDes(tabsUp.getDes() + achive2.getDes().replace("yyyy",befaoreYear) + achive3.getDes().replace("yyyy",year));
        if(avg1 == 1 && avg2 == -1){
            tabsUp.setColor(achive2.getColor());
        }else if(avg1 == 0 && avg2 == -1){
            tabsUp.setColor("red");
        }
        result.put("tabsUp",tabsUp);

        //获取科研项目平均分
        paramMap.put("type",1);
        paramMap.put("indexCode","FINJXF001");
        String strF = finTabsService.selectTabs(paramMap).getDes();
        Integer avgF = Integer.parseInt(str.substring(str.lastIndexOf("分") - 2, str.lastIndexOf("分")));
        //科研项目平均绩效 比较 及格线
        //当年平均绩效 avgFsscAchive avgF
        int avgF1 = avgFsscAchive.get(0).getYtd().compareTo(BigDecimal.valueOf(avgF));
        paramMap.put("type",avgF1);
        TabsVo tabsDown = finTabsService.selectTabs(paramMap);
        //去年平均绩效 比较 当年平均绩效 avgAchive avgAchiveBefore
        int avgF2 = avgFsscAchive.get(0).getYtd().compareTo(avgFsscAchiveBefore.get(0).getYtd());
        paramMap.put("type",avgF2);
        paramMap.put("indexCode","FINJXF002");
        TabsVo achiveF2 = finTabsService.selectTabs(paramMap);
        paramMap.put("indexCode","FINJXF003");
        TabsVo achiveF3 = finTabsService.selectTabs(paramMap);
        tabsDown.setDes(tabsDown.getDes() + achiveF2.getDes().replace("yyyy",befaoreYear) + achiveF3.getDes().replace("yyyy",year));
        if(avgF1 == 1 && avgF2 == -1){
            tabsDown.setColor(achiveF2.getColor());
        }else if(avgF1 == 0 && avgF2 == -1){
            tabsDown.setColor("red");
        }
        result.put("tabsDown",tabsDown);




        /*TabsVo tabsVo = new TabsVo();
        tabsVo.setDes("整体项目平均绩效大于60分，高于2017年项目平均绩效，2018年绩效稳步提升。");
        tabsVo.setColor("green");
        result.put("tabsUp",tabsVo);

        tabsVo = new TabsVo();
        tabsVo.setDes("科研项目平均绩效大于60分，高于2017年项目平均绩效，2018年绩效稳步提升。");
        tabsVo.setColor("green");
        result.put("tabsDown",tabsVo);*/

        return new Result().sucess(result);

    }



}
