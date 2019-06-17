package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeUnitQueryForm;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypeUnit;
import com.deloitte.services.fssc.base.mapper.BaseDocumentTypeUnitMapper;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeUnitService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-18
 * @Description :  BaseDocumentTypeUnit服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseDocumentTypeUnitServiceImpl extends
        ServiceImpl<BaseDocumentTypeUnitMapper, BaseDocumentTypeUnit> implements
        IBaseDocumentTypeUnitService {


    @Autowired
    private BaseDocumentTypeUnitMapper baseDocumentTypeUnitMapper;

    @Override
    public IPage<BaseDocumentTypeUnit> selectPage(BaseDocumentTypeUnitQueryForm queryForm) {
        QueryWrapper<BaseDocumentTypeUnit> queryWrapper = new QueryWrapper<BaseDocumentTypeUnit>();
        getQueryWrapper(queryWrapper, queryForm);
        return baseDocumentTypeUnitMapper
                .selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                        queryWrapper);

    }

    @Override
    public List<BaseDocumentTypeUnit> selectList(BaseDocumentTypeUnitQueryForm queryForm) {
        QueryWrapper<BaseDocumentTypeUnit> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return baseDocumentTypeUnitMapper.selectList(queryWrapper);
    }

    @Override
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<BaseDocumentTypeUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseDocumentTypeUnit.ID, idList);
        List<BaseDocumentTypeUnit> docTypeUnitList = this.list(queryWrapper);
        for (BaseDocumentTypeUnit docTypeUnit : docTypeUnitList) {
            docTypeUnit.setValidFlag(validFlag);
        }
        return this.updateBatchById(docTypeUnitList);
    }

    /**
     * 通用查询
     */
    public QueryWrapper<BaseDocumentTypeUnit> getQueryWrapper(
            QueryWrapper<BaseDocumentTypeUnit> queryWrapper,
            BaseDocumentTypeUnitQueryForm queryForm) {
        //条件拼接
        if (queryForm.getOrgUnitId() == null) {
            queryWrapper.eq(BaseDocumentTypeUnit.ORG_UNIT_ID, queryForm.getOrgUnitId());
        }
        if (queryForm.getDocumentTypeId() == null) {
            queryWrapper.eq(BaseDocumentTypeUnit.DOCUMENT_TYPE_ID, queryForm.getDocumentTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseDocumentTypeUnit.EXT5, queryForm.getValidFlag());
        }
        return queryWrapper;
    }
}

