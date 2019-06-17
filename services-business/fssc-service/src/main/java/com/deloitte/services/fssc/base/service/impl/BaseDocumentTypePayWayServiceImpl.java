package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypePayWayQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypePayWayForm;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypePayWay;
import com.deloitte.services.fssc.base.mapper.BaseDocumentTypePayWayMapper;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypePayWayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import java.util.ArrayList;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-11
 * @Description :  BaseDocumentTypePayWay服务实现类
 * @Modified :
 */
@Service
public class BaseDocumentTypePayWayServiceImpl extends
        ServiceImpl<BaseDocumentTypePayWayMapper, BaseDocumentTypePayWay> implements
        IBaseDocumentTypePayWayService {

    @Autowired
    private BaseDocumentTypePayWayMapper payWayMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateOrSaveBatch(List<BaseDocumentTypePayWayForm> payWayFormList) {
        List<BaseDocumentTypePayWay> addPayWayList = new ArrayList<>();
        List<BaseDocumentTypePayWay> updatePayWayList = new ArrayList<>();
        for (BaseDocumentTypePayWayForm payWayForm : payWayFormList) {
            BaseDocumentTypePayWay payWay;
            if (payWayForm.getId() != null) {
                List<Long> ids = new ArrayList<>();
                ids.add(Long.valueOf(payWayForm.getId()));
                payWay = this.getById(payWayForm.getId());
                if (payWay != null) {
                    payWay.setCode(payWayForm.getCode());
                    payWay.setName(payWayForm.getName().trim());
                    payWay.setValidFlag(payWayForm.getValidFlag());
                    payWay.setDescription(payWayForm.getDescription());
                } else {
                    throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
                }
                updatePayWayList.add(payWay);
            } else {
                payWay = new BeanUtils<BaseDocumentTypePayWay>()
                        .copyObj(payWayForm, BaseDocumentTypePayWay.class);
                payWay.setName(payWay.getName().trim());
                payWay.setValidFlag(FsscEums.VALID.getValue());
                addPayWayList.add(payWay);
            }
        }
        if (CollectionUtils.isNotEmpty(updatePayWayList)) {
            this.updateBatchById(updatePayWayList);
        }
        if (CollectionUtils.isNotEmpty(addPayWayList)) {
            this.saveBatch(addPayWayList);
        }
    }

    @Override
    public BaseDocumentTypePayWay getPayWayByCode(String code, Long documentTypeId) {
        QueryWrapper<BaseDocumentTypePayWay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseDocumentTypePayWay.CODE,code);
        queryWrapper.eq(BaseDocumentTypePayWay.DOCUMENT_TYPE_ID,documentTypeId);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<BaseDocumentTypePayWay> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseDocumentTypePayWay.ID, idList);
        List<BaseDocumentTypePayWay> payWayList = this.list(queryWrapper);
        for (BaseDocumentTypePayWay payWay : payWayList) {
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                if (validFlag.equals(payWay.getValidFlag())) {
                    throw new FSSCException(FsscErrorType.CANNOT_REPEAT_VALID);
                }
            } else if (validFlag.equals(payWay.getValidFlag())) {
                throw new FSSCException(FsscErrorType.CANNOT_INVALID);
            }
            payWay.setValidFlag(validFlag);
        }
        return this.updateBatchById(payWayList);
    }

    @Override
    public IPage<BaseDocumentTypePayWay> selectPage(BaseDocumentTypePayWayQueryForm queryForm) {
        QueryWrapper<BaseDocumentTypePayWay> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper,queryForm);
        return payWayMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(),
                        queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<BaseDocumentTypePayWay> selectList(BaseDocumentTypePayWayQueryForm queryForm) {
        QueryWrapper<BaseDocumentTypePayWay> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return payWayMapper.selectList(queryWrapper);
    }

    /**
     * 通用查询
     * @param queryWrapper
     * @param queryForm
     * @return
     */
    private QueryWrapper<BaseDocumentTypePayWay> getQueryWrapper(
            QueryWrapper<BaseDocumentTypePayWay> queryWrapper,
            BaseDocumentTypePayWayQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getCode())) {
            queryWrapper.eq(BaseDocumentTypePayWay.CODE, queryForm.getCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.eq(BaseDocumentTypePayWay.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getDescription())) {
            queryWrapper.eq(BaseDocumentTypePayWay.DESCRIPTION, queryForm.getDescription());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseDocumentTypePayWay.VALID_FLAG, queryForm.getValidFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getDocumentTypeId())) {
            queryWrapper.eq(BaseDocumentTypePayWay.DOCUMENT_TYPE_ID, queryForm.getDocumentTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BaseDocumentTypePayWay.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BaseDocumentTypePayWay.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getExt1())) {
            queryWrapper.eq(BaseDocumentTypePayWay.EXT1, queryForm.getExt1());
        }
        if (StringUtils.isNotBlank(queryForm.getExt2())) {
            queryWrapper.eq(BaseDocumentTypePayWay.EXT2, queryForm.getExt2());
        }
        if (StringUtils.isNotBlank(queryForm.getExt3())) {
            queryWrapper.eq(BaseDocumentTypePayWay.EXT3, queryForm.getExt3());
        }
        if (StringUtils.isNotBlank(queryForm.getExt4())) {
            queryWrapper.eq(BaseDocumentTypePayWay.EXT4, queryForm.getExt4());
        }
        if (StringUtils.isNotBlank(queryForm.getExt5())) {
            queryWrapper.eq(BaseDocumentTypePayWay.EXT5, queryForm.getExt5());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BaseDocumentTypePayWay.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.eq(BaseDocumentTypePayWay.ORG_PATH, queryForm.getOrgPath());
        }
        return queryWrapper;
    }
}

