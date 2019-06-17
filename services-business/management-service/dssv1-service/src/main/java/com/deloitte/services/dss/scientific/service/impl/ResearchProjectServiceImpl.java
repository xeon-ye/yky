package com.deloitte.services.dss.scientific.service.impl;

import com.deloitte.platform.api.dss.scientific.vo.ProjectAnnualNumberVo;
import com.deloitte.platform.api.dss.scientific.vo.ResearchProjectVo;
import com.deloitte.services.dss.scientific.mapper.ResearchProjectMapper;
import com.deloitte.services.dss.scientific.service.IResearchProjectService;

import com.deloitte.services.dss.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ResearchProjectServiceImpl
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-02-28
 * @version: v1.0
 */
@Service
@Transactional
public class ResearchProjectServiceImpl implements IResearchProjectService {

    @Autowired
    private ResearchProjectMapper researchProjectMapper;


    /**
     * 百分比格式化
     *
     * @param arg1
     * @param arg2
     * @return
     */
    private String decimalFormatRatio(Double arg1, Double arg2) {
        if (arg1 != null && arg2 != null) {
            DecimalFormat df = new DecimalFormat("0%");
            String formatarg = df.format(arg1 / arg2);
            return formatarg;

        }
        return "0%";
    }


    @Override
    public List<ResearchProjectVo> queryNumAndFundRatio() {
        List<ResearchProjectVo> projectNumList = researchProjectMapper.queryProjectNumAndAmount();
        double totalNum = 0;
        double totalMoney = 0;
        double sumFund = 0;
        double sumProject = 0;
        double fundSum = 0;
        double projectSum = 0;
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        for(ResearchProjectVo researchProjectVo : projectNumList){
            totalNum = totalNum+ researchProjectVo.getNumber();
            totalMoney = totalMoney+ researchProjectVo.getMoney();
        }
        for(int i = 0 ; i < projectNumList.size() ; i++ ) {
            if (i != projectNumList.size() - 1) {
                if (totalNum != 0) {
                    projectNumList.get(i).setAmountRatio(decimalFormat.format(projectNumList.get(i).getMoney() * 1.0 / totalMoney) + "");
                    projectNumList.get(i).setQuantityRatio(decimalFormat.format(projectNumList.get(i).getNumber() * 1.0 / totalNum) + "");
                    sumFund = projectNumList.get(i).getMoney() * 1.0 / totalMoney;
                    sumProject = projectNumList.get(i).getNumber() * 1.0 / totalNum;
                    fundSum += sumFund;
                    projectSum += sumProject;
                } else {
                    projectNumList.get(i).setAmountRatio("0");
                    projectNumList.get(i).setQuantityRatio("0");
                }
            }
            else{
                projectNumList.get(i).setAmountRatio(decimalFormat.format(1-fundSum) + "");
                projectNumList.get(i).setQuantityRatio(decimalFormat.format(1-projectSum) + "");
            }
        }

        return projectNumList;
    }

    @Override
    public List<ProjectAnnualNumberVo> queryAnnualProjectNum() {
        List<ProjectAnnualNumberVo> voList = researchProjectMapper.queryAnnualProjectNum();
        Collections.sort(voList, new Comparator<ProjectAnnualNumberVo>() {
            @Override
            public int compare(ProjectAnnualNumberVo o1, ProjectAnnualNumberVo o2) {
                return Integer.parseInt(o1.getAnnual())-Integer.parseInt(o2.getAnnual());
            }
        });

        return voList;
    }
}
