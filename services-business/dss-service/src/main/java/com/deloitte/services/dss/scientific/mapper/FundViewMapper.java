package com.deloitte.services.dss.scientific.mapper;

import com.deloitte.platform.api.dss.scientific.vo.FundDetailVo;
import com.deloitte.platform.api.dss.scientific.vo.FundViewVo;
import com.deloitte.platform.api.dss.scientific.vo.ResultVo;
import com.deloitte.platform.api.dss.scientific.vo.TotalVo;
import com.deloitte.platform.common.core.entity.vo.Result;

import java.util.List;

public interface FundViewMapper {
//    历年项目经费
    List<FundViewVo> queryFund(Integer num);
//    明细经费
    List<FundDetailVo> queryFundDetail(Integer num);
//  科研成果
    List<ResultVo> queryResult1(Integer number);
    List<ResultVo> queryResult();
//    明细评审
    //博士后
    TotalVo queryDoctor(Integer num);
    TotalVo queryDoctorc(Integer num);
    //高级人才
    TotalVo queryHeight(Integer num);
    TotalVo queryHeightc(Integer num);
    //高层次人才
    TotalVo queryHperson(Integer num);
    TotalVo queryHpersonc(Integer num);
    //执行率
    TotalVo queryExecution(Integer num);
    TotalVo queryExecutionc(Integer num);
    //成果比
    TotalVo queryResultratio(Integer num);
    TotalVo queryResultratioc(Integer num);
}

