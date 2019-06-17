package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseContentCommitmentQueryForm;
import com.deloitte.services.fssc.base.entity.BaseContentCommitment;
import com.deloitte.services.fssc.base.mapper.BaseContentCommitmentMapper;
import com.deloitte.services.fssc.base.service.IBaseContentCommitmentService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-07
 * @Description :  BaseContentCommitment服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseContentCommitmentServiceImpl extends
        ServiceImpl<BaseContentCommitmentMapper, BaseContentCommitment>
        implements IBaseContentCommitmentService {


    @Autowired
    private BaseContentCommitmentMapper baseContentCommitmentMapper;

    @Override
    public BaseContentCommitment getByDocumentTypeId(Long documentTypeId) {
        QueryWrapper<BaseContentCommitment> queryWrapper = new QueryWrapper();
        queryWrapper.eq(BaseContentCommitment.DOCUMENT_TYPE_ID,documentTypeId);
        return this.getOne(queryWrapper);
    }

    @Override
    public IPage<BaseContentCommitment> selectPage(BaseContentCommitmentQueryForm queryForm) {
        QueryWrapper<BaseContentCommitment> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return baseContentCommitmentMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);
    }

    @Override
    public List<BaseContentCommitment> selectList(BaseContentCommitmentQueryForm queryForm) {
        QueryWrapper<BaseContentCommitment> queryWrapper = new QueryWrapper<BaseContentCommitment>();
        return baseContentCommitmentMapper.selectList(queryWrapper);
    }

    @Override
    public boolean updateContentCommitment(BaseContentCommitment baseContentCommitment) {
        if (baseContentCommitmentMapper.updateById(baseContentCommitment) < 1) {
            throw new FSSCException(FsscErrorType.SAVE_FAIL);
        }
        return true;
    }

    @Override
    public boolean saveContentCommitment(BaseContentCommitment baseContentCommitment) {
        return this.save(baseContentCommitment);
    }

    @Override
    public boolean modifyStatus(List<Long> ids, String status) {
        QueryWrapper<BaseContentCommitment> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseContentCommitment.ID, ids);
        List<BaseContentCommitment> manyTypeList = this.list(queryWrapper);
        for (BaseContentCommitment baseContentCommitment : manyTypeList) {
            baseContentCommitment.setValidFlag(status);
            if (FsscEums.VALID.getValue().equals(status)) {
                baseContentCommitment.setValidDate(LocalDateTime.now());
            } else if (FsscEums.UN_VALID.getValue().equals(status)) {
                baseContentCommitment.setInvalidDate(LocalDateTime.now());
            }
        }
        return this.updateBatchById(manyTypeList);
    }

    /**
     * 通用查询
     */
    public QueryWrapper<BaseContentCommitment> getQueryWrapper(
            QueryWrapper<BaseContentCommitment> queryWrapper,
            BaseContentCommitmentQueryForm queryForm) {
        //条件拼接
        if (queryForm.getDocumentTypeId() == null) {
            queryWrapper.eq(BaseContentCommitment.DOCUMENT_TYPE_ID, queryForm.getDocumentTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getUnitCode())) {
            queryWrapper.eq(BaseContentCommitment.UNIT_CODE, queryForm.getUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getBillName())) {
            queryWrapper.like(BaseContentCommitment.BILL_NAME, queryForm.getBillName());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseContentCommitment.VALID_FLAG, queryForm.getValidFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getContentCommitment())) {
            queryWrapper.like(BaseContentCommitment.CONTENT_COMMITMENT,
                    queryForm.getContentCommitment());
        }
        return queryWrapper;
    }

}

