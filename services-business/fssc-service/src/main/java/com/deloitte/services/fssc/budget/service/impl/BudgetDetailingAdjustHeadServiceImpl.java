package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.budget.param.BudgetDetailingAdjustHeadQueryForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetDetailingAdjustHeadVo;
import com.deloitte.platform.api.fssc.budget.vo.BudgetDetailingAdjustLineVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.budget.entity.BudgetDetailingAdjustHead;
import com.deloitte.services.fssc.budget.entity.BudgetDetailingAdjustLine;
import com.deloitte.services.fssc.budget.mapper.BudgetDetailingAdjustHeadMapper;
import com.deloitte.services.fssc.budget.mapper.BudgetDetailingAdjustLineMapper;
import com.deloitte.services.fssc.budget.service.IBudgetDetailingAdjustHeadService;
import com.deloitte.services.fssc.budget.service.IBudgetDetailingAdjustLineService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.util.AssertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-06
 * @Description :  BudgetDetailingAdjustHead服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetDetailingAdjustHeadServiceImpl extends ServiceImpl<BudgetDetailingAdjustHeadMapper, BudgetDetailingAdjustHead>
        implements IBudgetDetailingAdjustHeadService {


    @Autowired
    private BudgetDetailingAdjustHeadMapper adjustHeadMapper;

    @Autowired
    private IBudgetDetailingAdjustLineService adjustLineService;

    @Override
    public BudgetDetailingAdjustHeadVo findInfoById(Long adjustId) {
        BudgetDetailingAdjustHead adjustHead = this.getById(adjustId);
        AssertUtils.asserts(adjustHead != null && adjustHead.getId() != null, FsscErrorType.NOT_FIND_DATA);
        BudgetDetailingAdjustHeadVo adjustHeadVo = new BeanUtils<BudgetDetailingAdjustHeadVo>().copyObj(adjustHead,BudgetDetailingAdjustHeadVo.class);
        List<BudgetDetailingAdjustLine> lineList = adjustLineService.selectList(adjustHead.getId());
        List<BudgetDetailingAdjustLineVo> lineVoList = new BeanUtils<BudgetDetailingAdjustLineVo>().copyObjs(lineList,BudgetDetailingAdjustLineVo.class);
        adjustHeadVo.setLineVoList(lineVoList);
        return adjustHeadVo;
    }

    @Override
    public IPage<BudgetDetailingAdjustHead> selectPage(BudgetDetailingAdjustHeadQueryForm queryForm) {
        QueryWrapper<BudgetDetailingAdjustHead> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return adjustHeadMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<BudgetDetailingAdjustHead> selectList(BudgetDetailingAdjustHeadQueryForm queryForm) {
        QueryWrapper<BudgetDetailingAdjustHead> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return adjustHeadMapper.selectList(queryWrapper);
    }

    /**
     * 通用查询
     *
     * @param queryWrapper,queryForm
     * @return
     **/
    public QueryWrapper<BudgetDetailingAdjustHead> getQueryWrapper(QueryWrapper<BudgetDetailingAdjustHead> queryWrapper,
                                                                   BudgetDetailingAdjustHeadQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getDocumentNum())) {
            queryWrapper.like(BudgetDetailingAdjustHead.DOCUMENT_NUM, queryForm.getDocumentNum());
        }
        if (StringUtils.isNotBlank(queryForm.getDocumentStatus())) {
            queryWrapper.eq(BudgetDetailingAdjustHead.DOCUMENT_STATUS, queryForm.getDocumentStatus());
        }
        if (queryForm.getUnitId() != null) {
            queryWrapper.eq(BudgetDetailingAdjustHead.UNIT_ID, queryForm.getUnitId());
        }
        if (StringUtils.isNotBlank(queryForm.getUnitCode())) {
            queryWrapper.eq(BudgetDetailingAdjustHead.UNIT_CODE, queryForm.getUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getUnitName())) {
            queryWrapper.like(BudgetDetailingAdjustHead.UNIT_NAME, queryForm.getUnitName());
        }
        if (queryForm.getDeptId() != null) {
            queryWrapper.eq(BudgetDetailingAdjustHead.DEPT_ID, queryForm.getDeptId());
        }
        if (StringUtils.isNotBlank(queryForm.getDeptCode())) {
            queryWrapper.eq(BudgetDetailingAdjustHead.DEPT_CODE, queryForm.getDeptCode());
        }
        if (StringUtils.isNotBlank(queryForm.getDeptName())) {
            queryWrapper.like(BudgetDetailingAdjustHead.DEPT_NAME, queryForm.getDeptName());
        }
        if (StringUtils.isNotBlank(queryForm.getFundType())) {
            queryWrapper.eq(BudgetDetailingAdjustHead.FUND_TYPE, queryForm.getFundType());
        }
        if (queryForm.getApplyTotalAmountDown() != null) {
            queryWrapper.ge(BudgetDetailingAdjustHead.APPLY_TOTAL, queryForm.getApplyTotalAmountDown());
        }
        if (queryForm.getApplyTotalAmountUp() != null) {
            queryWrapper.le(BudgetDetailingAdjustHead.APPLY_TOTAL, queryForm.getApplyTotalAmountUp());
        }
        if (queryForm.getMakeDocumentTimeStart() != null) {
            queryWrapper.ge(BudgetDetailingAdjustHead.CREATE_TIME, queryForm.getMakeDocumentTimeStart());
        }
        if (queryForm.getMakeDocumentTimeEnd() != null) {
            queryWrapper.le(BudgetDetailingAdjustHead.CREATE_TIME, queryForm.getMakeDocumentTimeEnd());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BudgetDetailingAdjustHead.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.like(BudgetDetailingAdjustHead.ORG_PATH, queryForm.getOrgPath());
        }
        if (StringUtils.isNotBlank(queryForm.getApplyForPerson())) {
            queryWrapper.eq(BudgetDetailingAdjustHead.APPLY_FOR_PERSON, queryForm.getApplyForPerson());
        }
        if (StringUtils.isNotBlank(queryForm.getApplyForPersonName())) {
            queryWrapper.like(BudgetDetailingAdjustHead.APPLY_FOR_PERSON_NAME, queryForm.getApplyForPersonName());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BudgetDetailingAdjustHead.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BudgetDetailingAdjustHead.UPDATE_BY, queryForm.getUpdateBy());
        }
        queryWrapper.orderByDesc(BudgetDetailingAdjustHead.CREATE_TIME);
        return queryWrapper;
    }
}

