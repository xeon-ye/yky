package com.deloitte.services.dss.scientific.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.scientific.vo.RegionalAgeVo;
import com.deloitte.platform.api.dss.scientific.vo.RegionalDataVo;
import com.deloitte.platform.api.dss.scientific.vo.RegionalProScaleVo;
import com.deloitte.platform.api.dss.scientific.vo.ResultVo;
import com.deloitte.services.dss.scientific.mapper.FundViewMapper;
import com.deloitte.services.dss.scientific.mapper.RegionalMapper;
import com.deloitte.services.dss.scientific.service.IRegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地区分布数据
 */
@Service
@Transactional
public class RegionalServiceImpl extends ServiceImpl implements IRegionalService {

    @Autowired
    public RegionalMapper regionalMapper;

    @Autowired
    public FundViewMapper fundViewMapper;


    @Override
    public Map queryCurYearData(Map map){
        Long applayNum = regionalMapper.queryApplayNum(map);
        Long totalNum = regionalMapper.queryTotalNum(map);
        Double recevieOutlay = regionalMapper.queryReceviceOutlay(map);
        Map<String,Object>  resultMap = new HashMap();
        resultMap.put("totalNum",totalNum);
        resultMap.put("applayNum",applayNum);
        resultMap.put("recevieOutlay",recevieOutlay);
        List<RegionalDataVo> regionProNum = regionalMapper.queryRegionProNum(map);
        List<RegionalDataVo> regionProMoney = regionalMapper.queryRegionProMoney(map);
        List<RegionalDataVo> regionData = regionalMapper.queryRegionData(map);
        RegionalProScaleVo scaleData = regionalMapper.queryScaleData(map);
        RegionalAgeVo ageData = regionalMapper.queryAgeData(map);

        //查询论文、专利 相关数据
        List<ResultVo> resultVos = fundViewMapper.queryResult();

        resultMap.put("resultData",resultVos);
        resultMap.put("regionProNum",regionProNum);
        resultMap.put("regionProMoney",regionProMoney);
        resultMap.put("regionData",regionData);
        resultMap.put("scaleData",scaleData);
        resultMap.put("ageData",ageData);

        return resultMap;
    }

    @Override
    public List<RegionalDataVo> queryColumnarData(Map map) {
        List<RegionalDataVo> mapList = regionalMapper.queryColumnarData(map);
        Collections.sort(mapList, new Comparator<RegionalDataVo>() {
            @Override
            public int compare(RegionalDataVo o1, RegionalDataVo o2) {
                return (int) (o1.getId()-o2.getId());
            }
        });
        return mapList;
    }

}

