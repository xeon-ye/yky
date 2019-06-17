package com.deloitte.services.fssc.business.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.edu.param.FundsApplyLineQueryForm;
import com.deloitte.platform.api.fssc.edu.vo.FundsApplyLineVo;
import com.deloitte.services.fssc.business.edu.entity.FundsApplyLine;
import com.deloitte.services.fssc.business.edu.mapper.FundsApplyLineMapper;
import com.deloitte.services.fssc.business.edu.service.IFundsApplyLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-29
 * @Description :  FundsApplyLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FundsApplyLineServiceImpl extends ServiceImpl<FundsApplyLineMapper, FundsApplyLine> implements IFundsApplyLineService {


    @Autowired
    private FundsApplyLineMapper fundsApplyLineMapper;

    @Override
    public IPage<FundsApplyLine> selectPage(FundsApplyLineQueryForm queryForm) {
        QueryWrapper<FundsApplyLine> queryWrapper = new QueryWrapper();
        getQueryWrapper(queryWrapper, queryForm);
        return fundsApplyLineMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<FundsApplyLine> selectList(FundsApplyLineQueryForm queryForm) {
        QueryWrapper<FundsApplyLine> queryWrapper = new QueryWrapper<>();
        //getQueryWrapper(queryWrapper,queryForm);
        return fundsApplyLineMapper.selectList(queryWrapper);
    }

    @Override
    public FundsApplyLine getByKeyWord(Long documentId, Long lineNumber) {
        QueryWrapper<FundsApplyLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(FundsApplyLine.DOCUMENT_ID, documentId);
        queryWrapper.eq(FundsApplyLine.LINE_NUMBER, lineNumber);
        return this.getOne(queryWrapper);
    }

    @Override
    public IPage<FundsApplyLineVo> selectVoPage(FundsApplyLineQueryForm queryForm) {
        IPage<FundsApplyLineVo> iPage = new Page<>(queryForm.getCurrentPage(),queryForm.getPageSize());
        List<FundsApplyLineVo> voList = fundsApplyLineMapper.selectLineVo(queryForm.getUnitId(),
                queryForm.getSchoolId(),queryForm.getRecieveUserId(),queryForm.getDocumentNum(),iPage);
        iPage.setRecords(voList);
        return iPage;
    }

    private QueryWrapper<FundsApplyLine> getQueryWrapper(QueryWrapper<FundsApplyLine> queryWrapper, FundsApplyLineQueryForm queryForm) {
        //条件拼接
        if (queryForm.getCreateBy() != null) {
            queryWrapper.eq(FundsApplyLine.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateUserName())) {
            queryWrapper.like(FundsApplyLine.CREATE_USER_NAME, queryForm.getCreateUserName());
        }
        if (queryForm.getUpdateBy() != null) {
            queryWrapper.eq(FundsApplyLine.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (queryForm.getSchoolId() != null) {
            queryWrapper.eq(FundsApplyLine.SCHOOL_ID, queryForm.getSchoolId());
        }
        if (StringUtils.isNotBlank(queryForm.getUseType())) {
            queryWrapper.eq(FundsApplyLine.USE_TYPE, queryForm.getUseType());
        }
        if (queryForm.getRecieveUserId() != null) {
            queryWrapper.eq(FundsApplyLine.RECIEVE_USER_ID, queryForm.getRecieveUserId());
        }
        if (StringUtils.isNotBlank(queryForm.getRecieveUserName())) {
            queryWrapper.like(FundsApplyLine.RECIEVE_USER_NAME, queryForm.getRecieveUserName());
        }
        if (StringUtils.isNotBlank(queryForm.getRecieveEmpNo())) {
            queryWrapper.eq(FundsApplyLine.RECIEVE_EMP_NO, queryForm.getRecieveEmpNo());
        }
        if (StringUtils.isNotBlank(queryForm.getApplyUnitName())) {
            queryWrapper.eq(FundsApplyLine.APPLY_UNIT_NAME, queryForm.getApplyUnitName());
        }
        if (StringUtils.isNotBlank(queryForm.getApplyUserName())) {
            queryWrapper.eq(FundsApplyLine.APPLY_USER_NAME, queryForm.getApplyUserName());
        }
        if (StringUtils.isNotBlank(queryForm.getIsConnected())) {
            queryWrapper.eq(FundsApplyLine.IS_CONNECTED, queryForm.getIsConnected());
        }
        return queryWrapper;
    }
}

