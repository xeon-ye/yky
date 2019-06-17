package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.FinanceInfoQueryForm;
import com.deloitte.platform.api.contract.vo.*;
import com.deloitte.platform.api.fssc.basecontract.feign.FsscBaseContractInfoFeignService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.entity.BasicFinanceMap;
import com.deloitte.services.contract.entity.BasicInfo;
import com.deloitte.services.contract.entity.BasicProjectMap;
import com.deloitte.services.contract.entity.FinanceInfo;
import com.deloitte.services.contract.mapper.BasicFinanceMapMapper;
import com.deloitte.services.contract.mapper.BasicInfoMapper;
import com.deloitte.services.contract.mapper.FinanceInfoMapper;
import com.deloitte.services.contract.service.IFinanceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.contract.service.ISysProjectInfoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  FinanceInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinanceInfoServiceImpl extends ServiceImpl<FinanceInfoMapper, FinanceInfo> implements IFinanceInfoService {


    @Autowired
    private FinanceInfoMapper financeInfoMapper;
    @Autowired
    private BasicInfoMapper basicInfoMapper;
    @Autowired
    private BasicFinanceMapMapper basicFinanceMapMapper;
    @Autowired
    FsscBaseContractInfoFeignService fsscBaseContractInfoFeignService;
    @Autowired
    private ISysProjectInfoService iSysProjectInfoService;

    @Override
    public IPage<FinanceInfo> selectPage(FinanceInfoQueryForm queryForm) {
        QueryWrapper<FinanceInfo> queryWrapper = new QueryWrapper<FinanceInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return financeInfoMapper.selectPage(new Page<FinanceInfo>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<FinanceInfo> selectList(FinanceInfoQueryForm queryForm) {
        QueryWrapper<FinanceInfo> queryWrapper = new QueryWrapper<FinanceInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return financeInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<FinanceInfoVo> selectFinanceInfo(String contractId) {
        return financeInfoMapper.selectFinanceInfo(contractId);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return public QueryWrapper<FinanceInfo> getQueryWrapper(QueryWrapper<FinanceInfo> queryWrapper,FinanceInfoQueryForm queryForm){
    //条件拼接
    if(StringUtils.isNotBlank(queryForm.getOurContractEntityCode())){
    queryWrapper.eq(FinanceInfo.OUR_CONTRACT_ENTITY_CODE,queryForm.getOurContractEntityCode());
    }
    if(StringUtils.isNotBlank(queryForm.getOurContractEntity())){
    queryWrapper.eq(FinanceInfo.OUR_CONTRACT_ENTITY,queryForm.getOurContractEntity());
    }
    if(StringUtils.isNotBlank(queryForm.getOtherContractEntityCode())){
    queryWrapper.eq(FinanceInfo.OTHER_CONTRACT_ENTITY_CODE,queryForm.getOtherContractEntityCode());
    }
    if(StringUtils.isNotBlank(queryForm.getOtherContractEntity())){
    queryWrapper.eq(FinanceInfo.OTHER_CONTRACT_ENTITY,queryForm.getOtherContractEntity());
    }
    if(StringUtils.isNotBlank(queryForm.getOtherBankCode())){
    queryWrapper.eq(FinanceInfo.OTHER_BANK_CODE,queryForm.getOtherBankCode());
    }
    if(StringUtils.isNotBlank(queryForm.getOtherBank())){
    queryWrapper.eq(FinanceInfo.OTHER_BANK,queryForm.getOtherBank());
    }
    if(StringUtils.isNotBlank(queryForm.getOtherAccountName())){
    queryWrapper.eq(FinanceInfo.OTHER_ACCOUNT_NAME,queryForm.getOtherAccountName());
    }
    if(StringUtils.isNotBlank(queryForm.getOtherAccount())){
    queryWrapper.eq(FinanceInfo.OTHER_ACCOUNT,queryForm.getOtherAccount());
    }
    if(StringUtils.isNotBlank(queryForm.getAppointPayStage())){
    queryWrapper.eq(FinanceInfo.APPOINT_PAY_STAGE,queryForm.getAppointPayStage());
    }
    if(StringUtils.isNotBlank(queryForm.getAppointPayCondition())){
    queryWrapper.eq(FinanceInfo.APPOINT_PAY_CONDITION,queryForm.getAppointPayCondition());
    }
    if(StringUtils.isNotBlank(queryForm.getAppointPayRate())){
    queryWrapper.eq(FinanceInfo.APPOINT_PAY_RATE,queryForm.getAppointPayRate());
    }
    if(StringUtils.isNotBlank(queryForm.getAppointPayAmount())){
    queryWrapper.eq(FinanceInfo.APPOINT_PAY_AMOUNT,queryForm.getAppointPayAmount());
    }
    if(StringUtils.isNotBlank(queryForm.getActPayRate())){
    queryWrapper.eq(FinanceInfo.ACT_PAY_RATE,queryForm.getActPayRate());
    }
    if(StringUtils.isNotBlank(queryForm.getPlanPayTime())){
    queryWrapper.eq(FinanceInfo.PLAN_PAY_TIME,queryForm.getPlanPayTime());
    }
    if(StringUtils.isNotBlank(queryForm.getActPayTime())){
    queryWrapper.eq(FinanceInfo.ACT_PAY_TIME,queryForm.getActPayTime());
    }
    if(StringUtils.isNotBlank(queryForm.getIsManual())){
    queryWrapper.eq(FinanceInfo.IS_MANUAL,queryForm.getIsManual());
    }
    if(StringUtils.isNotBlank(queryForm.getIsUsed())){
    queryWrapper.eq(FinanceInfo.IS_USED,queryForm.getIsUsed());
    }
    if(StringUtils.isNotBlank(queryForm.getCreateTime())){
    queryWrapper.eq(FinanceInfo.CREATE_TIME,queryForm.getCreateTime());
    }
    if(StringUtils.isNotBlank(queryForm.getCreateBy())){
    queryWrapper.eq(FinanceInfo.CREATE_BY,queryForm.getCreateBy());
    }
    if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
    queryWrapper.eq(FinanceInfo.UPDATE_TIME,queryForm.getUpdateTime());
    }
    if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
    queryWrapper.eq(FinanceInfo.UPDATE_BY,queryForm.getUpdateBy());
    }
    if(StringUtils.isNotBlank(queryForm.getContractId())){
    queryWrapper.eq(FinanceInfo.CONTRACT_ID,queryForm.getContractId());
    }
    if(StringUtils.isNotBlank(queryForm.getContractName())){
    queryWrapper.eq(FinanceInfo.CONTRACT_NAME,queryForm.getContractName());
    }
    if(StringUtils.isNotBlank(queryForm.getSpareField1())){
    queryWrapper.eq(FinanceInfo.SPARE_FIELD_1,queryForm.getSpareField1());
    }
    if(StringUtils.isNotBlank(queryForm.getSpareField2())){
    queryWrapper.eq(FinanceInfo.SPARE_FIELD_2,queryForm.getSpareField2());
    }
    if(StringUtils.isNotBlank(queryForm.getSpareField3())){
    queryWrapper.eq(FinanceInfo.SPARE_FIELD_3,queryForm.getSpareField3());
    }
    if(StringUtils.isNotBlank(queryForm.getSpareField4())){
    queryWrapper.eq(FinanceInfo.SPARE_FIELD_4,queryForm.getSpareField4());
    }
    if(StringUtils.isNotBlank(queryForm.getSpareField5())){
    queryWrapper.eq(FinanceInfo.SPARE_FIELD_5,queryForm.getSpareField5());
    }
    return queryWrapper;
    }
     */

    /**
     * 保存财务信息
     *
     * @param basicInfoVo
     * @return
     */
    public List<FinanceInfoVo> saveFinanceInfoList(BasicInfoVo basicInfoVo, UserVo userVo) {
        List<FinanceInfoVo> financeInfoVoList = basicInfoVo.getListFinanceInfoVo();
//        List<FinanceInfo> financeInfoList = new BeanUtils<FinanceInfo>().copyObjs(financeInfoVoList, FinanceInfo.class);
        List<FinanceInfoVo> financeInfos = new ArrayList<>();

        AssertUtils.asserts(financeInfoVoList == null && financeInfoVoList.size() <= 0, ContractErrorType.FROM_FINANCEINFO);
        Long contractId = Long.parseLong(basicInfoVo.getId());
        // 查询合同关联信息
        List<BasicInfoVo> basicInfoVos = basicInfoMapper.queryCorrelationBasic(contractId.toString());
        AssertUtils.asserts(basicInfoVos == null || basicInfoVos.size() <= 0, ContractErrorType.CONTRACT_IS_NULL);
        // 合同关联状态
        String relationCode = basicInfoVos.get(0).getRelationCode();
        if (relationCode != null && relationCode.equals("REL3000")){
            BasicInfoVo basicInfoVoRel  = basicInfoVos.get(0);
            basicInfoVos = new ArrayList<>();
            basicInfoVos.add(basicInfoVoRel);
        }

        // 收支类型
        String incomeExpenditureTypeCode = basicInfoVos.get(0).getIncomeExpenditureTypeCode();
        // 根据合同id查询原有财务与合同关联信息
//        QueryWrapper<BasicFinanceMap> queryWrapperQuer = new QueryWrapper<>();
//        queryWrapperQuer.eq(BasicFinanceMap.CONTRACT_ID, contractId);
//        List<BasicFinanceMap> deleteList = basicFinanceMapMapper.selectList(queryWrapperQuer);
        // 查询在前端删除的财务信息，即不在前端传回数据里的数据(查询条件 not in)
        List<Long> financeInfoIds = new ArrayList<>();
        for (int i = 0; i < financeInfoVoList.size(); i++){
            Long id = null;
            String idStr = financeInfoVoList.get(i).getId();
            if (idStr != null && !idStr.equals("")){
                id = Long.parseLong(idStr);
                financeInfoIds.add(id);
            }
        }
        QueryWrapper<BasicFinanceMap> queryWrapperQuer = new QueryWrapper<>();
        queryWrapperQuer.eq(BasicFinanceMap.CONTRACT_ID, contractId);
        queryWrapperQuer.notIn(BasicFinanceMap.FINANCE_ID, financeInfoIds);
        List<BasicFinanceMap> deleteList = basicFinanceMapMapper.selectList(queryWrapperQuer);
        // 根据财务信息ID删除财务与合同关联信息,并删除财务信息
        for (int i = 0; i < deleteList.size(); i++) {
            Long financeId = deleteList.get(i).getFinanceId();
            if (financeId != null) {
                QueryWrapper<BasicFinanceMap> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(BasicFinanceMap.FINANCE_ID, financeId);
                // 删除财务与合同关联信息
                basicFinanceMapMapper.delete(queryWrapper);
                // 删除财务信息
                financeInfoMapper.deleteById(financeId);
            }
        }
        // 保存新的财务信息
        for (int i = 0; i < financeInfoVoList.size(); i++) {
            FinanceInfoVo financeInfoVo = financeInfoVoList.get(i);
            FinanceInfo financeInfo = new BeanUtils<FinanceInfo>().copyObj(financeInfoVo, FinanceInfo.class);
            String strId = financeInfoVo.getId();
            if (strId != null && !strId.equals("")){
                // 修改财务信息
                Long financeInfoId = Long.parseLong(financeInfoVo.getId());
                financeInfo.setId(financeInfoId);
                financeInfoMapper.updateById(financeInfo);
            }else{
                // 保存财务信息
                financeInfo.setCreateBy(userVo.getId());
                financeInfo.setContractType(incomeExpenditureTypeCode);
                financeInfoMapper.insert(financeInfo);

                //保存财务与合同关联信息
                for (int j = 0; j < basicInfoVos.size(); j++){
                    Long id = Long.parseLong(basicInfoVos.get(j).getId());
                    BasicFinanceMap basicFinanceMap = new BasicFinanceMap();
                    basicFinanceMap.setContractId(id);
                    basicFinanceMap.setFinanceId(financeInfo.getId());
                    basicFinanceMap.setCreateBy(userVo.getId());
                    basicFinanceMapMapper.insert(basicFinanceMap);
                }
            }

            FinanceInfoVo financeInfoVoNew = new BeanUtils<FinanceInfoVo>().copyObj(financeInfo, FinanceInfoVo.class);
            financeInfos.add(financeInfoVoNew);
        }
        return financeInfos;
    }

    /**
     * 保存财务系统返回财务信息
     * @param listFinanceInfoVoToFssc
     * @return
     */
    public boolean saveFinanceInfo(List<FinanceInfoVoToFssc> listFinanceInfoVoToFssc){
        List<FinanceInfoVo> listFinanceInfoVo = new BeanUtils<FinanceInfoVo>().copyObjs(listFinanceInfoVoToFssc, FinanceInfoVo.class);
        for (int i = 0; i < listFinanceInfoVo.size(); i++){
            FinanceInfoVo financeInfoVo = listFinanceInfoVo.get(i);
            Long financeId = Long.parseLong(financeInfoVo.getId());
            FinanceInfo financeInfo = financeInfoMapper.selectById(financeId);
            String type = financeInfo.getType();
            if (type != null && !type.equals("")){
                if (type.equals("1")){
                    financeInfo.setActPayRate(financeInfoVo.getActPayRate());
                    financeInfo.setActPayTime(financeInfoVo.getActPayTime());
                    financeInfo.setIsManual("0");
                }else if (type.equals("2")){
                    financeInfo.setActIncomeRate(financeInfoVo.getActIncomeRate());
                    financeInfo.setActIncomeTime(financeInfoVo.getActIncomeTime());
                    financeInfo.setIsManualIncome("0");
                }
            }else {
                AssertUtils.asserts(true, ContractErrorType.NO_FINANCE_TYPE);
            }
            financeInfoMapper.updateById(financeInfo);
        }
        return true;
    }

    public Result selectFinanceToFssc(String contractId){
        List<FinanceInfoVo> financeInfo = selectFinanceInfo(contractId);
        List<FinanceInfoVoToFssc> financeInfoVoToFssc = new BeanUtils<FinanceInfoVoToFssc>().copyObjs(financeInfo, FinanceInfoVoToFssc.class);
        BasicInfo basicInfo = basicInfoMapper.selectById(contractId);
        BasicInfoVoToFssc basicInfoVo = new BeanUtils<BasicInfoVoToFssc>().copyObj(basicInfo, BasicInfoVoToFssc.class);
        basicInfoVo.setListFinanceInfoVo(financeInfoVoToFssc);
        List<SysProjectInfoVo> basicProjectMapVoList = iSysProjectInfoService.querySysProjectInfo(contractId);
        List<SysProjectInfoVoFssc> sysProjectInfoVoFssc = new BeanUtils<SysProjectInfoVoFssc>().copyObjs(basicProjectMapVoList, SysProjectInfoVoFssc.class);
        basicInfoVo.setListSysProjectInfoVo(sysProjectInfoVoFssc);
        Result result = fsscBaseContractInfoFeignService.receiveFinance(basicInfoVo);
        //发送成功，将财务信息发送财务系统标记修改为1
        if (result.isSuccess()){
            basicInfo.setIsToFssc("1");
            basicInfoMapper.updateById(basicInfo);
        }
        return result;
    }
}

