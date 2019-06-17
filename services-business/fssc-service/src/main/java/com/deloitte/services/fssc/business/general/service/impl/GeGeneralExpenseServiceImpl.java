package com.deloitte.services.fssc.business.general.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.general.param.GeExpenseBorrowPrepayQueryForm;
import com.deloitte.platform.api.fssc.general.param.GeGeneralExpenseQueryForm;
import com.deloitte.platform.api.fssc.general.vo.BorrowPrepayListVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpense;
import com.deloitte.services.fssc.business.general.mapper.GeGeneralExpenseMapper;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :  GeGeneralExpense服务实现类
 * @Modified :
 */
@Service
@Transactional
public class GeGeneralExpenseServiceImpl extends ServiceImpl<GeGeneralExpenseMapper, GeGeneralExpense> implements IGeGeneralExpenseService {


    @Autowired
    private GeGeneralExpenseMapper geGeneralExpenseMapper;

    @Autowired
    private FsscCommonServices commonServices;
    @Override
    public IPage<GeGeneralExpense> selectPage(GeGeneralExpenseQueryForm queryForm ) {
        QueryWrapper<GeGeneralExpense> queryWrapper = new QueryWrapper <GeGeneralExpense>();
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId()!=null,"CREATE_BY", FsscCommonUtil.getCurrentUserId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getPayStatus()),"PAY_STATUS",queryForm.getPayStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),"PROJECT_ID",queryForm.getProjectId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getMainTypeId()),"MAIN_TYPE_ID",queryForm.getMainTypeId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"EXPENSE_AMOUNT",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"EXPENSE_AMOUNT",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.orderByDesc("UPDATE_TIME");
        return geGeneralExpenseMapper.selectPage(new Page<GeGeneralExpense>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<GeGeneralExpense> selectList(GeGeneralExpenseQueryForm queryForm) {
        QueryWrapper<GeGeneralExpense> queryWrapper = new QueryWrapper <GeGeneralExpense>();
        //getQueryWrapper(queryWrapper,queryForm);
        return geGeneralExpenseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean existsByExpenseMainTypeGeIds(List<Long> idList) {
        return geGeneralExpenseMapper.countByExpenseMainTypeIds(idList) > 0 ? true : false;
    }

    @Override
    public List<BorrowPrepayListVo> findBorrowPrepayList(GeExpenseBorrowPrepayQueryForm form) {
        DeptVo currentDept = commonServices.getCurrentDept();
        UserVo currentUser = commonServices.getCurrentUser();
        form.setCreateBy(StringUtil.castTolong(currentUser.getId()));
        form.setUnitId(StringUtil.castTolong(currentDept.getId()));
        String paymentTypes = form.getPaymentTypes();
        if(StringUtil.isNotEmpty(paymentTypes)){
            String[] split = paymentTypes.split(",");
            List<String> strings = Arrays.asList(split);
            form.setPaymentTypeList(strings);
        }else {
            return new ArrayList<BorrowPrepayListVo>();
        }
        return geGeneralExpenseMapper.findBorrowPrepayList(form);
    }

}

