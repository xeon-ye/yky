package com.deloitte.services.dss.scientific.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.scientific.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import java.util.Map;

import java.util.List;

public interface IFundViewService extends IService{
//    历年项目经费
    List<FundViewVo> queryFund(String num);
//    List<TotalVo> queryBsTotal();
//    明细经费
    List<FundDetailVo>queryFundDetail(String num);
//    科研成果
    List<ResultVo>queryResult1(String num);
    //明细评审
    Map<String,String> queryProjectDetail(Integer num);

    /**
     * 获取占比
     * @param deptCode
     * @return
     */
    List<FundViewProportionVo>  queryProportion(String deptCode);
    //查询依托单位接口
    List<DeptVo> queryDept();
}
