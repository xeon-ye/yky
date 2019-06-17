package com.deloitte.services.dss.scientific.service.impl;

import com.deloitte.platform.api.dss.scientific.vo.ProjectAnnualNumberVo;
import com.deloitte.platform.api.dss.scientific.vo.ResearchProjectVo;
import com.deloitte.services.dss.scientific.mapper.ResearchProjectMapper;
import com.deloitte.services.dss.scientific.service.IResearchProjectService;

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
        List<ResearchProjectVo> ratioList = new ArrayList<>();
        List<ResearchProjectVo> projectNumList = researchProjectMapper.queryProjectNum();
        List<ResearchProjectVo> projectFundList = researchProjectMapper.queryFund();
        double numSum = 0;
        double fundSum = 0;
        for (int i = 0; i < projectFundList.size(); i++) {
            ResearchProjectVo projectVo = new ResearchProjectVo();
            ResearchProjectVo fundVo = projectFundList.get(i);
            ResearchProjectVo numVo = projectNumList.get(i);
            projectVo.setCategory(numVo.getCategory());
            projectVo.setNumber(numVo.getNumber());
            projectVo.setMoney(fundVo.getMoney());
            numSum += (numVo.getNumber() == null ? 0 : numVo.getNumber());
            fundSum += (fundVo.getMoney() == null ? 0 : fundVo.getMoney());
            ratioList.add(projectVo);
        }
        for (int i = 0; i < projectFundList.size(); i++) {
            String numRatio = decimalFormatRatio(projectNumList.get(i).getNumber() == null ? 0 : projectNumList.get(i).getNumber(), numSum);
            String fundRatio = decimalFormatRatio(projectFundList.get(i).getMoney() == null ? 0 : projectFundList.get(i).getMoney(), fundSum);
            ResearchProjectVo vo = ratioList.get(i);
            vo.setQuantityRatio(numRatio);
            vo.setAmountRatio(fundRatio);

        }

        return ratioList;
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
