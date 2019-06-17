package com.deloitte.services.fssc.business.travle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.travle.param.TasTravelReimburseQueryForm;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.mapper.BmBorrowBankMapper;
import com.deloitte.services.fssc.business.travle.entity.TasTravelReimburse;
import com.deloitte.services.fssc.business.travle.mapper.TasTravelReimburseMapper;
import com.deloitte.services.fssc.business.travle.service.ITasTravelReimburseService;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-04-08
 * @Description :  TasTravelReimburse服务实现类
 * @Modified :
 */
@Service
@Transactional
public class TasTravelReimburseServiceImpl extends ServiceImpl<TasTravelReimburseMapper, TasTravelReimburse> implements ITasTravelReimburseService {


    @Autowired
    private TasTravelReimburseMapper tasTravelReimburseMapper;

    @Autowired
    private BmBorrowBankMapper bmBorrowBankMapper;

    @Override
    public IPage<TasTravelReimburse> selectPage(TasTravelReimburseQueryForm queryForm ) {
        QueryWrapper<TasTravelReimburse> queryWrapper = new QueryWrapper <TasTravelReimburse>();
        //getQueryWrapper(queryWrapper,queryForm);
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId()!=null,"CREATE_BY", FsscCommonUtil.getCurrentUserId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPayStatus()),"PAY_STATUS",queryForm.getPayStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getMainTypeId()),"MAIN_TYPE_ID",queryForm.getMainTypeId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"TOTAL_SUM",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"TOTAL_SUM",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),"PROJECT_ID",queryForm.getProjectId());
        queryWrapper.orderByDesc("CREATE_TIME");
        return tasTravelReimburseMapper.selectPage(new Page<TasTravelReimburse>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<TasTravelReimburse> selectList(TasTravelReimburseQueryForm queryForm) {
        QueryWrapper<TasTravelReimburse> queryWrapper = new QueryWrapper <TasTravelReimburse>();
        //getQueryWrapper(queryWrapper,queryForm);
        return tasTravelReimburseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean existsByExpenseMainTypeTrIds(List<Long> idList) {
        return tasTravelReimburseMapper.countByExpenseMainTypeIds(idList) > 0 ? true : false;
    }

    public List<BmBorrowBank> queryList (QueryWrapper<BmBorrowBank> queryWrapper){
        return   bmBorrowBankMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<TasTravelReimburse> getQueryWrapper(QueryWrapper<TasTravelReimburse> queryWrapper,TasTravelReimburseQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(TasTravelReimburse.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(TasTravelReimburse.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(TasTravelReimburse.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(TasTravelReimburse.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(TasTravelReimburse.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(TasTravelReimburse.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){
            queryWrapper.eq(TasTravelReimburse.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentStatus())){
            queryWrapper.eq(TasTravelReimburse.DOCUMENT_STATUS,queryForm.getDocumentStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitId())){
            queryWrapper.eq(TasTravelReimburse.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptId())){
            queryWrapper.eq(TasTravelReimburse.DEPT_ID,queryForm.getDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(TasTravelReimburse.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getMainTypeId())){
            queryWrapper.eq(TasTravelReimburse.MAIN_TYPE_ID,queryForm.getMainTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalSum())){
            queryWrapper.eq(TasTravelReimburse.TOTAL_SUM,queryForm.getTotalSum());
        }
        if(StringUtils.isNotBlank(queryForm.getIsAgreedPromis())){
            queryWrapper.eq(TasTravelReimburse.IS_AGREED_PROMIS,queryForm.getIsAgreedPromis());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(TasTravelReimburse.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitName())){
            queryWrapper.eq(TasTravelReimburse.UNIT_NAME,queryForm.getUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(TasTravelReimburse.DEPT_NAME,queryForm.getDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getMainTypeName())){
            queryWrapper.eq(TasTravelReimburse.MAIN_TYPE_NAME,queryForm.getMainTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.eq(TasTravelReimburse.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(TasTravelReimburse.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(TasTravelReimburse.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(TasTravelReimburse.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(TasTravelReimburse.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(TasTravelReimburse.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getExt6())){
            queryWrapper.eq(TasTravelReimburse.EXT6,queryForm.getExt6());
        }
        if(StringUtils.isNotBlank(queryForm.getExt7())){
            queryWrapper.eq(TasTravelReimburse.EXT7,queryForm.getExt7());
        }
        if(StringUtils.isNotBlank(queryForm.getExt8())){
            queryWrapper.eq(TasTravelReimburse.EXT8,queryForm.getExt8());
        }
        if(StringUtils.isNotBlank(queryForm.getExt9())){
            queryWrapper.eq(TasTravelReimburse.EXT9,queryForm.getExt9());
        }
        if(StringUtils.isNotBlank(queryForm.getExt10())){
            queryWrapper.eq(TasTravelReimburse.EXT10,queryForm.getExt10());
        }
        if(StringUtils.isNotBlank(queryForm.getExt11())){
            queryWrapper.eq(TasTravelReimburse.EXT11,queryForm.getExt11());
        }
        if(StringUtils.isNotBlank(queryForm.getExt12())){
            queryWrapper.eq(TasTravelReimburse.EXT12,queryForm.getExt12());
        }
        if(StringUtils.isNotBlank(queryForm.getExt13())){
            queryWrapper.eq(TasTravelReimburse.EXT13,queryForm.getExt13());
        }
        if(StringUtils.isNotBlank(queryForm.getExt14())){
            queryWrapper.eq(TasTravelReimburse.EXT14,queryForm.getExt14());
        }
        if(StringUtils.isNotBlank(queryForm.getExt15())){
            queryWrapper.eq(TasTravelReimburse.EXT15,queryForm.getExt15());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitCode())){
            queryWrapper.eq(TasTravelReimburse.UNIT_CODE,queryForm.getUnitCode());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptCode())){
            queryWrapper.eq(TasTravelReimburse.DEPT_CODE,queryForm.getDeptCode());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCode())){
            queryWrapper.eq(TasTravelReimburse.PROJECT_CODE,queryForm.getProjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getMainTypeCode())){
            queryWrapper.eq(TasTravelReimburse.MAIN_TYPE_CODE,queryForm.getMainTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getCurrencyCode())){
            queryWrapper.eq(TasTravelReimburse.CURRENCY_CODE,queryForm.getCurrencyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getPaymentType())){
            queryWrapper.eq(TasTravelReimburse.PAYMENT_TYPE,queryForm.getPaymentType());
        }
        if(StringUtils.isNotBlank(queryForm.getTravelPeopleNum())){
            queryWrapper.eq(TasTravelReimburse.TRAVEL_PEOPLE_NUM,queryForm.getTravelPeopleNum());
        }
        if(StringUtils.isNotBlank(queryForm.getPaidAmount())){
            queryWrapper.eq(TasTravelReimburse.PAID_AMOUNT,queryForm.getPaidAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getNoPaidAmount())){
            queryWrapper.eq(TasTravelReimburse.NO_PAID_AMOUNT,queryForm.getNoPaidAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getAssociatTravelApply())){
            queryWrapper.eq(TasTravelReimburse.ASSOCIAT_TRAVEL_APPLY,queryForm.getAssociatTravelApply());
        }
        if(StringUtils.isNotBlank(queryForm.getPayStatus())){
            queryWrapper.eq(TasTravelReimburse.PAY_STATUS,queryForm.getPayStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getSupportFileNum())){
            queryWrapper.eq(TasTravelReimburse.SUPPORT_FILE_NUM,queryForm.getSupportFileNum());
        }
        if(StringUtils.isNotBlank(queryForm.getIsAfterPatch())){
            queryWrapper.eq(TasTravelReimburse.IS_AFTER_PATCH,queryForm.getIsAfterPatch());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseAmount())){
            queryWrapper.eq(TasTravelReimburse.EXPENSE_AMOUNT,queryForm.getExpenseAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getBusinussAmount())){
            queryWrapper.eq(TasTravelReimburse.BUSINUSS_AMOUNT,queryForm.getBusinussAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getVerificationAmount())){
            queryWrapper.eq(TasTravelReimburse.VERIFICATION_AMOUNT,queryForm.getVerificationAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getPayCompanyAmount())){
            queryWrapper.eq(TasTravelReimburse.PAY_COMPANY_AMOUNT,queryForm.getPayCompanyAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getPaySalaryAmount())){
            queryWrapper.eq(TasTravelReimburse.PAY_SALARY_AMOUNT,queryForm.getPaySalaryAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(TasTravelReimburse.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(TasTravelReimburse.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

