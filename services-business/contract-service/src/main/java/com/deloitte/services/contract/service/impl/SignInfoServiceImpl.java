package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.SignInfoQueryForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.vo.SignInfoForm;
import com.deloitte.platform.api.contract.vo.SignInfoVo;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.entity.BasicAttamentMap;
import com.deloitte.services.contract.entity.BasicInfo;
import com.deloitte.services.contract.entity.SignInfo;
import com.deloitte.services.contract.mapper.BasicAttamentMapMapper;
import com.deloitte.services.contract.mapper.SignInfoMapper;
import com.deloitte.services.contract.service.IBasicInfoService;
import com.deloitte.services.contract.service.ICommonService;
import com.deloitte.services.contract.service.ISignInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SignInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SignInfoServiceImpl extends ServiceImpl<SignInfoMapper, SignInfo> implements ISignInfoService {


    @Autowired
    private SignInfoMapper signInfoMapper;
    @Autowired
    private BasicAttamentMapMapper basicAttamentMapMapper;
    @Autowired
    private IBasicInfoService basicInfoService;
    @Autowired
    private ICommonService commonService;

    public SignInfoServiceImpl() {
    }

    @Override
    public IPage<SignInfo> selectPage(SignInfoQueryForm queryForm ) {
        QueryWrapper<SignInfo> queryWrapper = new QueryWrapper <SignInfo>();
        getQueryWrapper(queryWrapper,queryForm);
        return signInfoMapper.selectPage(new Page<SignInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SignInfo> selectList(SignInfoQueryForm queryForm) {
        QueryWrapper<SignInfo> queryWrapper = new QueryWrapper <SignInfo>();
        getQueryWrapper(queryWrapper,queryForm);
        return signInfoMapper.selectList(queryWrapper);
    }
    //插入或者修改CONTRACT_SIGN_INFO(合同签署信息表)
    @Override
    public SignInfo insertOrUpdateSign(SignInfoForm signInfoForm, UserVo userVo) {
        String contractId=signInfoForm.getContractId().toString();

        OrganizationVo organizationVo = commonService.getOrganization();
        //设置履行人信息
        BasicInfo contract = basicInfoService.getById(contractId);
        contract.setExecuter(userVo.getName());
        contract.setExecuterCode(userVo.getId());
        contract.setExecuterOrg(organizationVo.getName());
        contract.setExecuterOrgId(organizationVo.getCode());
        basicInfoService.updateById(contract);

        //查询是否已经插入表
        List<SignInfo> signInfo=signInfoMapper.querySignInfo(contractId);
       SignInfo signInfoInsert=new BeanUtils<SignInfo>().copyObj(signInfoForm,SignInfo.class);
        //修改
        if(null != signInfo && signInfo.size()>0){
            for(SignInfo signInfoData:signInfo){
                signInfoData.setOurSignContractNum(signInfoForm.getOurSignContractNum());
                signInfoData.setOurPrintContractNum(signInfoForm.getOurPrintContractNum());
                signInfoData.setStampWay(signInfoForm.getStampWay());
                signInfoData.setUpdateBy(userVo.getName());
                updateById(signInfoData);
            }
        }else{
            //新增
            //新增默认设置签署为第一步
//            signInfo1.setSignStage("1");
            signInfoInsert.setCreateBy(userVo.getName());
            save(signInfoInsert);
        }
        return null;
    }

    @Override
    public  List<BasicAttamentMap> downStampFile(SignInfoForm signInfoForm) {
        QueryWrapper<BasicAttamentMap> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BasicAttamentMap.CONTRACT_ID, signInfoForm.getContractId());
        //查询合同打印页面的正文及附件
        if(signInfoForm.getStampWay().equals("1")) {
            queryWrapper.in(BasicAttamentMap.FILE_TYPE, "FIL1000", "FIL2000", "FIL3000");
        }else{
            queryWrapper.in(BasicAttamentMap.FILE_TYPE, "FIL1000", "FIL2000");
        }
        List<BasicAttamentMap> list=basicAttamentMapMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public List<SignInfo> selectSignInfo(SignInfoForm signInfoForm) {
        return signInfoMapper.querySignInfo(signInfoForm.getContractId().toString());
    }

    @Override
    public List<SignInfo> querySignInfo(String contractId) {
        return signInfoMapper.querySignInfo(contractId);
    }

    @Override
    public void updateSignInfo(List<SignInfoVo> signInfoVoList) {
        for(SignInfoVo signInfoVo:signInfoVoList){
            if(null != signInfoVo &&null != signInfoVo.getId()){
                SignInfo signInfo=new BeanUtils<SignInfo>().copyObj(signInfoVo,SignInfo.class);
                if(null != signInfoVo.getOtherAuthorization()){
                    FileInfoVo fileInfoVo = signInfoVo.getOtherAuthorization();
                    signInfo.setOtherAuthorizationName(fileInfoVo.getFileName());
                    signInfo.setOtherAuthorizationUrl(fileInfoVo.getFileUrl());
                }
                signInfo.setId(Long.parseLong(signInfoVo.getId()));
                updateById(signInfo);
            }
        }
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<SignInfo> getQueryWrapper(QueryWrapper<SignInfo> queryWrapper,SignInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getContractId()+"")){
            queryWrapper.eq(SignInfo.CONTRACT_ID,queryForm.getContractId());
        }
        /*if(StringUtils.isNotBlank(queryForm.getContractName())){
            queryWrapper.eq(SignInfo.CONTRACT_NAME,queryForm.getContractName());
        }
        if(StringUtils.isNotBlank(queryForm.getOurPrintTime())){
            queryWrapper.eq(SignInfo.OUR_PRINT_TIME,queryForm.getOurPrintTime());
        }
        if(StringUtils.isNotBlank(queryForm.getOurPrintContractNum())){
            queryWrapper.eq(SignInfo.OUR_PRINT_CONTRACT_NUM,queryForm.getOurPrintContractNum());
        }
        if(StringUtils.isNotBlank(queryForm.getOurSignPerson())){
            queryWrapper.eq(SignInfo.OUR_SIGN_PERSON,queryForm.getOurSignPerson());
        }
        if(StringUtils.isNotBlank(queryForm.getOurSignTime())){
            queryWrapper.eq(SignInfo.OUR_SIGN_TIME,queryForm.getOurSignTime());
        }
        if(StringUtils.isNotBlank(queryForm.getOurSignContractNum())){
            queryWrapper.eq(SignInfo.OUR_SIGN_CONTRACT_NUM,queryForm.getOurSignContractNum());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherContractName())){
            queryWrapper.eq(SignInfo.OTHER_CONTRACT_NAME,queryForm.getOtherContractName());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherLegalPerson())){
            queryWrapper.eq(SignInfo.OTHER_LEGAL_PERSON,queryForm.getOtherLegalPerson());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherSignPerson())){
            queryWrapper.eq(SignInfo.OTHER_SIGN_PERSON,queryForm.getOtherSignPerson());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherSignTime())){
            queryWrapper.eq(SignInfo.OTHER_SIGN_TIME,queryForm.getOtherSignTime());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherAuthorization())){
            queryWrapper.eq(SignInfo.OTHER_AUTHORIZATION,queryForm.getOtherAuthorization());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherSignContractNum())){
            queryWrapper.eq(SignInfo.OTHER_SIGN_CONTRACT_NUM,queryForm.getOtherSignContractNum());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(SignInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SignInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(SignInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(SignInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(SignInfo.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(SignInfo.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(SignInfo.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(SignInfo.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(SignInfo.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(SignInfo.SPARE_FIELD_5,queryForm.getSpareField5());
        }*/
        return queryWrapper;
    }

}

