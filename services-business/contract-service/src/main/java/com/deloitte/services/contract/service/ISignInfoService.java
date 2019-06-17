package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.SignInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SignInfoForm;
import com.deloitte.platform.api.contract.vo.SignInfoVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.contract.entity.BasicAttamentMap;
import com.deloitte.services.contract.entity.SignInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : SignInfo服务类接口
 * @Modified :
 */
public interface ISignInfoService extends IService<SignInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SignInfo>
     */
    IPage<SignInfo> selectPage(SignInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SignInfo>
     */
    List<SignInfo> selectList(SignInfoQueryForm queryForm);

    //新增或修改合同签署信息
    SignInfo insertOrUpdateSign(SignInfoForm signInfoForm,UserVo userVo);
    //合同打印页面下载正文及附件
    List<BasicAttamentMap> downStampFile(SignInfoForm signInfoForm);
    //合同签署界面查询
    List<SignInfo> selectSignInfo(SignInfoForm signInfoForm);
    //根据contractId查询合同签署信息
    List<SignInfo> querySignInfo(String contractId);
    //根据signInfoId修改信息
    void updateSignInfo(List<SignInfoVo> signInfoVoList);


}
