package com.deloitte.services.fssc.business.ppc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.ppc.param.NoProjectConfirmationQueryForm;
import com.deloitte.services.fssc.business.ppc.entity.NoProjectConfirmation;
import com.deloitte.services.fssc.business.ppc.mapper.NoProjectConfirmationMapper;
import com.deloitte.services.fssc.business.ppc.service.INoProjectConfirmationService;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :  NoProjectConfirmation服务实现类
 * @Modified :
 */
@Service
@Transactional
public class NoProjectConfirmationServiceImpl extends ServiceImpl<NoProjectConfirmationMapper, NoProjectConfirmation> implements INoProjectConfirmationService {


    @Autowired
    private NoProjectConfirmationMapper noProjectConfirmationMapper;

    @Override
    public IPage<NoProjectConfirmation> selectPage(NoProjectConfirmationQueryForm queryForm ) {
        QueryWrapper<NoProjectConfirmation> queryWrapper = new QueryWrapper <NoProjectConfirmation>();
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getRecieveStatus()),"RECIEVE_STATUS",queryForm.getRecieveStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getInComeMainTypeId()),"IN_COME_MAIN_TYPE_ID",queryForm.getInComeMainTypeId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"EXPENSE_AMOUNT",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"EXPENSE_AMOUNT",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.orderByDesc("UPDATE_TIME");
        return noProjectConfirmationMapper.selectPage(new Page<NoProjectConfirmation>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<NoProjectConfirmation> selectList(NoProjectConfirmationQueryForm queryForm) {
        QueryWrapper<NoProjectConfirmation> queryWrapper = new QueryWrapper <NoProjectConfirmation>();
        //getQueryWrapper(queryWrapper,queryForm);
        return noProjectConfirmationMapper.selectList(queryWrapper);
    }


    @Override
    public boolean existsByReceivePayment(List<Long> incomeMainTypeIds) {
        QueryWrapper<NoProjectConfirmation> countWrapper = new QueryWrapper<>();
        countWrapper.in(NoProjectConfirmation.IN_COME_MAIN_TYPE_ID,incomeMainTypeIds);
        return this.count(countWrapper) > 0;
    }
}

