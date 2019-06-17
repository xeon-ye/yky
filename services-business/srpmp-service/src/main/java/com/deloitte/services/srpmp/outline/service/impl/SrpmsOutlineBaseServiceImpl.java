package com.deloitte.services.srpmp.outline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineBaseQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlineBaseMapper;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : pengchao
 * @Date : Create in 2019-02-14
 * @Description :  SrpmsOutlineBase服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsOutlineBaseServiceImpl extends ServiceImpl<SrpmsOutlineBaseMapper, SrpmsOutlineBase> implements ISrpmsOutlineBaseService {

    @Autowired
    private SrpmsOutlineBaseMapper srpmsOutlineBaseMapper;

    @Override
    public IPage<SrpmsOutlineBase> selectPage(BaseQueryForm<SrpmsOutlineBaseQueryParam> queryForm ) {
        QueryWrapper<SrpmsOutlineBase> queryWrapper = new QueryWrapper <SrpmsOutlineBase>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsOutlineBaseMapper.selectPage(new Page<SrpmsOutlineBase>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SrpmsOutlineBase> selectList(BaseQueryForm<SrpmsOutlineBaseQueryParam> queryForm) {
        QueryWrapper<SrpmsOutlineBase> queryWrapper = new QueryWrapper <SrpmsOutlineBase>();
        queryWrapper.eq(SrpmsOutlineBase.MONTH, queryForm.toParam(SrpmsOutlineBaseQueryParam.class).getMonth());
        queryWrapper.eq(SrpmsOutlineBase.ORG_ID,queryForm.toParam(SrpmsOutlineBaseQueryParam.class).getOrgId());
        queryWrapper.eq(SrpmsOutlineBase.TYPE, queryForm.toParam(SrpmsOutlineBaseQueryParam.class).getType());
        queryWrapper.eq(SrpmsOutlineBase.YEAR, queryForm.toParam(SrpmsOutlineBaseQueryParam.class).getYear());
        return srpmsOutlineBaseMapper.selectList(queryWrapper);
    }

    /**
     * 根据条件查询题录基础数据service接口实现
     *
     * @param type
     * @param orgId
     * @param year
     * @param month
     * @return
     */
    @Override
    public SrpmsOutlineBase getSrpmsOutlineBase(String type, Long orgId, String year, String month) {
        QueryWrapper<SrpmsOutlineBase> queryWrapper = new QueryWrapper <SrpmsOutlineBase>();

        queryWrapper.eq(SrpmsOutlineBase.MONTH, month);
        queryWrapper.eq(SrpmsOutlineBase.ORG_ID,orgId);
        queryWrapper.eq(SrpmsOutlineBase.TYPE, type);
        queryWrapper.eq(SrpmsOutlineBase.YEAR, year);
        List<SrpmsOutlineBase> srpmsOutlineBaseList =  srpmsOutlineBaseMapper.selectList(queryWrapper);

        if (srpmsOutlineBaseList.size() == 0) {
            return null;
        } else {
            return srpmsOutlineBaseList.get(0);
        }
    }

}

