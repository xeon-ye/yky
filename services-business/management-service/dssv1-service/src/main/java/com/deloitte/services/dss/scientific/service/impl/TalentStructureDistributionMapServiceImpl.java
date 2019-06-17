package com.deloitte.services.dss.scientific.service.impl;

import com.deloitte.platform.api.dss.scientific.vo.TalentStructureDistributionMapVo;
import com.deloitte.services.dss.scientific.mapper.TalentStructureDistributionMapMapper;
import com.deloitte.services.dss.scientific.service.ITalentStructureDistributionMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: TalentStructureDistributionMapServiceImpl
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-03-11
 * @version: v1.0
 */
@Service
@Transactional
public class TalentStructureDistributionMapServiceImpl implements ITalentStructureDistributionMapService {

    @Autowired
    private TalentStructureDistributionMapMapper mapMapper;

    @Override
    public TalentStructureDistributionMapVo getProjectTalentStructureDistribution() {
        return mapMapper.getProjectTalentStructureDistribution();
    }

    @Override
    public TalentStructureDistributionMapVo getTalentStructureDistribution(String category) {
        return mapMapper.getTalentStructureDistribution(category);
    }

    @Override
    public List<TalentStructureDistributionMapVo> getDeptTalentStructureDistribution(String category) {
        return mapMapper.getDeptTalentStructureDistribution(category);
    }
}
