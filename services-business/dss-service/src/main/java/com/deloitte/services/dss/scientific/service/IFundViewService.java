package com.deloitte.services.dss.scientific.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.scientific.vo.FundDetailVo;
import com.deloitte.platform.api.dss.scientific.vo.FundViewVo;
import com.deloitte.platform.api.dss.scientific.vo.ResultVo;
import com.deloitte.platform.api.dss.scientific.vo.TotalVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import java.util.Map;

import java.util.List;

public interface IFundViewService extends IService{
//    历年项目经费
    List<FundViewVo> queryFund(Integer num);
//    List<TotalVo> queryBsTotal();
//    明细经费
    List<FundDetailVo>queryFundDetail(Integer num);
//    科研成果
    List<ResultVo>queryResult1(Integer num);
    //明细评审
    Map<String,String> queryProjectDetail(Integer num);
        }
