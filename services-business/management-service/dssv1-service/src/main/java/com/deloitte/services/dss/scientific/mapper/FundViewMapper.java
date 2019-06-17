package com.deloitte.services.dss.scientific.mapper;

import com.deloitte.platform.api.dss.scientific.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;

import java.util.List;
import java.util.Map;

public interface FundViewMapper {
//    历年项目经费
    List<FundViewVo> queryFund(Long num);
//    明细经费
    List<FundDetailVo> queryFundDetail(Long num);
//  科研成果
    List<ResultVo> queryResult1(Long number);
    List<ResultVo> queryResult(String category);
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
    List<FundViewAuditVo> queryResultratio(Integer num);
    List<FundViewAuditVo> queryResultratioc(Integer num);


    /**
     * 查询个类别到位经费、支出经费数、项目数 、成果数等
     * @param deptID
     * @return
     */
    List<FundViewAuditVo> queryProportionData(Long  deptID);

//    依托单位接口
    List<DeptVo> queryDept();
}

