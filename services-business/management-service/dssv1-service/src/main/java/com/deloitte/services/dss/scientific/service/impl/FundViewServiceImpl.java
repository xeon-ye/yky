package com.deloitte.services.dss.scientific.service.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.scientific.vo.*;
;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectExpertForm;
import com.deloitte.services.dss.scientific.mapper.FundViewMapper;
import com.deloitte.services.dss.scientific.service.IFundViewService;
import com.deloitte.services.dss.util.MathUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FundViewServiceImpl extends ServiceImpl implements IFundViewService {
    @Autowired
    public FundViewMapper fundViewMapper;
//    历年经费
    @Override
    public List<FundViewVo>  queryFund(String num){
        Long deptId = Long.parseLong(num);
//        List<FundViewVo> ll = fundViewMapper.queryFund(deptId);
        return fundViewMapper.queryFund(deptId);
    }

//    明细经费
    @Override
    public List<FundDetailVo> queryFundDetail(String num){
        Long deptId = Long.parseLong(num);
        return fundViewMapper.queryFundDetail(deptId);
    }
//    科研成果
    @Override
    public List<ResultVo> queryResult1(String num){
        Long deptId = Long.parseLong(num);
        return fundViewMapper.queryResult1(deptId);
    }
    @Override
    public Map<String,String > queryProjectDetail(Integer num){
        Map<String,String> map=new HashMap<>();
        return map;
    }


    /**
     * 获取占比
     * @param deptCode
     * @return
     */

    @Override
    public List<FundViewProportionVo>  queryProportion(String deptCode){
        Long deptId = Long.parseLong(deptCode);
        List<FundViewAuditVo> auditVos = fundViewMapper.queryProportionData(deptId);
        if (CollectionUtils.isEmpty(auditVos)){
            return null;
        }
        List<FundViewProportionVo>  proportionVos  = new ArrayList<>();
        for (FundViewAuditVo vo:auditVos) {
            FundViewProportionVo  proportionVo  =  new FundViewProportionVo();
            proportionVo.setProType(vo.getProType());
            Long menberNum = vo.getMenberNum();
            if (null == menberNum  || menberNum == NumberUtils.LONG_ZERO){
                proportionVo.setHighProp(NumberUtils.DOUBLE_ZERO);
                proportionVo.setHighTalentProp(NumberUtils.DOUBLE_ZERO);
                proportionVo.setCategroyProp(NumberUtils.DOUBLE_ZERO);
            }else
            {
                proportionVo.setHighProp(MathUtils.div(vo.getHighNum(),menberNum,4));
                proportionVo.setHighTalentProp(MathUtils.div(vo.getHighTalent(),menberNum,4));
                proportionVo.setCategroyProp(MathUtils.div(vo.getCategroyNum(),menberNum,4));
            }

            Double reciveFunds = vo.getReciveFunds();
            if (null == reciveFunds  || reciveFunds == NumberUtils.DOUBLE_ZERO){
                proportionVo.setExceuteProp(NumberUtils.DOUBLE_ZERO);
            }else{
                proportionVo.setExceuteProp(MathUtils.div(vo.getPayFunds(),reciveFunds,4));
            }

            Long proNum = vo.getProNum();
            if (null == proNum  || proNum == NumberUtils.LONG_ZERO){
                proportionVo.setResultProp(NumberUtils.DOUBLE_ZERO);
            }else{
                proportionVo.setResultProp(MathUtils.div(vo.getProResultNum(),proNum,4));
            }
            proportionVos.add(proportionVo);

        }
        return proportionVos;
    }

    @Override
    public List<DeptVo> queryDept() {
        return fundViewMapper.queryDept();
    }
}
