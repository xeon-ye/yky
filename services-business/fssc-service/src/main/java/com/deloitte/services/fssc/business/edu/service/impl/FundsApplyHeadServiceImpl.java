package com.deloitte.services.fssc.business.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.edu.param.FundsApplyHeadQueryForm;
import com.deloitte.platform.api.isump.OrganizationClient;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.budget.entity.BudgetBasicBudget;
import com.deloitte.services.fssc.budget.service.IBudgetBasicBudgetService;
import com.deloitte.services.fssc.business.edu.entity.FundsApplyHead;
import com.deloitte.services.fssc.business.edu.mapper.FundsApplyHeadMapper;
import com.deloitte.services.fssc.business.edu.service.IFundsApplyHeadService;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import com.deloitte.services.fssc.util.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-29
 * @Description :  FundsApplyHead服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FundsApplyHeadServiceImpl extends ServiceImpl<FundsApplyHeadMapper, FundsApplyHead> implements IFundsApplyHeadService {

    @Autowired
    private OrganizationClient organizationClient;

    @Autowired
    IBudgetBasicBudgetService basicBudgetService;
    @Autowired
    private FundsApplyHeadMapper fundsApplyHeadMapper;

    @Override
    public IPage<FundsApplyHead> selectPage(FundsApplyHeadQueryForm queryForm) {
        QueryWrapper<FundsApplyHead> queryWrapper = new QueryWrapper<FundsApplyHead>();
        getQueryWrapper(queryWrapper, queryForm);
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId()!=null,"CREATE_BY", FsscCommonUtil.getCurrentUserId());
        return fundsApplyHeadMapper.selectPage(new Page<FundsApplyHead>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<FundsApplyHead> selectList(FundsApplyHeadQueryForm queryForm) {
        QueryWrapper<FundsApplyHead> queryWrapper = new QueryWrapper<FundsApplyHead>();
        //getQueryWrapper(queryWrapper,queryForm);
        return fundsApplyHeadMapper.selectList(queryWrapper);
    }

    @Override
    public void modifyAmount(boolean isSubmit, Long documentId,String documentType) {
        if(FsscTableNameEums. EDU_FUNDS_APPLY_HEAD.equals(documentType)){
            FundsApplyHead fundsApplyHead = fundsApplyHeadMapper.selectById(documentId);
            Result<List<OrganizationVo>> fictionOrgResult = organizationClient.getOrgFinctionByCode(fundsApplyHead.getUnitCode());
            if (fictionOrgResult.isFail() || CollectionUtils.isEmpty(fictionOrgResult.getData())){
                throw new FSSCException(FsscErrorType.GET_FICTION_ORG_FAIL);
            }
            String budgetAnnual = DateUtil.dateToString(LocalDateTimeUtils.convertLDTToDate(fundsApplyHead.getCreateTime()),DateUtil.FM_YYYY);
            BudgetBasicBudget cashPool = basicBudgetService.selectByKeyWord(fundsApplyHead.getUnitCode(),
                    fictionOrgResult.getData().get(0).getCode(), FsscEums.BUDGET_EDU_ACCOUNT_CODE.getValue(),budgetAnnual);
            if (cashPool == null){
                throw new FSSCException(FsscErrorType.CASH_POOLING_NOT_FIND);
            }
            BigDecimal budgetOccupyAmount = BigDecimalUtil.convert( cashPool.getBudgetOccupyAmount());
            BigDecimal totalOcc=BigDecimal.ZERO;
            if(isSubmit){
                totalOcc = budgetOccupyAmount.add(fundsApplyHead.getTotalAmount());
            }else {
                totalOcc = budgetOccupyAmount.subtract(fundsApplyHead.getTotalAmount());
            }

            AssertUtils.asserts(totalOcc.compareTo(cashPool.getBudgetAmount())<=0,FsscErrorType.BUDGET_MORE_THAN_100_PERCENT);
            cashPool.setBudgetOccupyAmount(totalOcc);
            basicBudgetService.saveOrUpdate(cashPool);
        }

    }

    /**
     * @param queryWrapper
     * @param queryForm
     * @return
     */
    public QueryWrapper<FundsApplyHead> getQueryWrapper(QueryWrapper<FundsApplyHead> queryWrapper, FundsApplyHeadQueryForm queryForm) {
        //条件拼接

        if (StringUtils.isNotBlank(queryForm.getCreateUserName())) {
            queryWrapper.like(FundsApplyHead.CREATE_USER_NAME, queryForm.getCreateUserName());
        }


        if (StringUtils.isNotBlank(queryForm.getDocumentNum())) {
            queryWrapper.like(FundsApplyHead.DOCUMENT_NUM, queryForm.getDocumentNum());
        }
        if (StringUtils.isNotBlank(queryForm.getDocumentStatus())) {
            queryWrapper.eq(FundsApplyHead.DOCUMENT_STATUS, queryForm.getDocumentStatus());
        }

        if (queryForm.getUnitId() != null) {
            queryWrapper.eq(FundsApplyHead.UNIT_ID, queryForm.getUnitId());
        }


        if (queryForm.getDeptId() != null) {
            queryWrapper.eq(FundsApplyHead.DEPT_ID, queryForm.getDeptId());
        }

        if (StringUtils.isNotBlank(queryForm.getFundType())) {
            queryWrapper.eq(FundsApplyHead.FUND_TYPE, queryForm.getFundType());
        }
        queryWrapper.ge(queryForm.getTimeStart()!=null,FundsApplyHead.CREATE_TIME, queryForm.getTimeStart());
        queryWrapper.le(queryForm.getTimeEnd()!=null,FundsApplyHead.CREATE_TIME, queryForm.getTimeEnd());
        queryWrapper.ge(queryForm.getMoneyStart()!=null,FundsApplyHead.TOTAL_AMOUNT, queryForm.getMoneyStart());
        queryWrapper.le(queryForm.getMoneyEnd()!=null,FundsApplyHead.TOTAL_AMOUNT, queryForm.getMoneyEnd());
        queryWrapper.orderByDesc(FundsApplyHead.CREATE_TIME);
        return queryWrapper;
    }

}

