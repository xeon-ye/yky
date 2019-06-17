package com.deloitte.services.dss.scientific.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.scientific.vo.FundDetailVo;
import com.deloitte.platform.api.dss.scientific.vo.FundViewVo;;
import com.deloitte.platform.api.dss.scientific.vo.ResultVo;
import com.deloitte.platform.api.dss.scientific.vo.TotalVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectExpertForm;
import com.deloitte.services.dss.scientific.mapper.FundViewMapper;
import com.deloitte.services.dss.scientific.service.IFundViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


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
    public List<FundViewVo>  queryFund(Integer num){
        return fundViewMapper.queryFund(num);
    }

//    明细经费
    @Override
    public List<FundDetailVo> queryFundDetail(Integer num){
        return fundViewMapper.queryFundDetail(num);
    }
//    科研成果
    @Override
    public List<ResultVo> queryResult1(Integer num){
        return fundViewMapper.queryResult1(num);
    }
    @Override
    public Map<String,String > queryProjectDetail(Integer num){
        Map<String,String> map=new HashMap<>();

            TotalVo doctor= fundViewMapper.queryDoctor(num);
            TotalVo doctorc=fundViewMapper.queryDoctorc(num);
            if(doctor==null||doctorc==null){
//            if(("").equals(doctor.getTotal())||doctor.getTotal()==null||("").doctorc.getTotal().equals||doctor.getTotal()==null){
                map.put("doctor","0");
                map.put("docyorc","0");
            }else {
                map.put("doctor", doctor.getTotal());
                map.put("doctorc", doctorc.getTotal());
            }

            TotalVo height=fundViewMapper.queryHeight(num);
            TotalVo heightc=fundViewMapper.queryHeightc(num);
        if(height==null||heightc==null){
//            if(height.getTotal().equals("")||height.getTotal()==null||heightc.getTotal().equals("")||heightc.getTotal()==null){
                map.put("height","0");
                map.put("heightc","0");
            }else {
                map.put("height", height.getTotal());
                map.put("heightc", heightc.getTotal());
            }

             TotalVo hperson=fundViewMapper.queryHperson(num);
             TotalVo hpersonc=fundViewMapper.queryHpersonc(num);
            if(hperson==null||hpersonc==null){
//             if(hperson.getTotal().equals("")||hperson.getTotal()==null||hpersonc.getTotal().equals("")||hpersonc.getTotal()==null){
                 map.put("hperson","0");
                 map.put("hpersonc","0");
             }else {
                 map.put("hperson", hperson.getTotal());
                 map.put("hpersonc", hpersonc.getTotal());
             }
            TotalVo execution=fundViewMapper.queryExecution(num);
            TotalVo executionc=fundViewMapper.queryExecutionc(num);
        if(execution==null||executionc==null){
//            if(("").equals(execution.getTotal())||execution.getTotal()==null||executionc.getTotal().equals("")||executionc.getTotal()==null){
                map.put("execution","0");
                map.put("executionc","0");
            }else {
                map.put("execution", execution.getTotal());
                map.put("executionc", executionc.getTotal());
            }
            TotalVo resultratio=fundViewMapper.queryResultratio(num);
            TotalVo resultratioc=fundViewMapper.queryResultratioc(num);
                if(resultratio==null||resultratioc==null){
//            if(resultratio.getTotal().equals("")||resultratioc.getTotal()==null||resultratio.getTotal()==null||resultratioc.getTotal().equals("")){
                map.put("resultratio","0");
                map.put("resultratioc","0");
            }else {
                map.put("resultratio", resultratio.getTotal());
                map.put("resultratioc", resultratioc.getTotal());
            }
        return map;
    }
}
