package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ExeRepHisQueryForm;
import com.deloitte.services.project.entity.ExeRepHis;
import com.deloitte.services.project.mapper.ExeRepHisMapper;
import com.deloitte.services.project.service.IExeRepHisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description :  ExeRepHis服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ExeRepHisServiceImpl extends ServiceImpl<ExeRepHisMapper, ExeRepHis> implements IExeRepHisService {


    @Autowired
    private ExeRepHisMapper exeRepHisMapper;

    @Override
    public IPage<ExeRepHis> selectPage(ExeRepHisQueryForm queryForm ) {
        QueryWrapper<ExeRepHis> queryWrapper = new QueryWrapper <ExeRepHis>();
        //getQueryWrapper(queryWrapper,queryForm);
        return exeRepHisMapper.selectPage(new Page<ExeRepHis>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ExeRepHis> selectList(ExeRepHisQueryForm queryForm) {
        QueryWrapper<ExeRepHis> queryWrapper = new QueryWrapper <ExeRepHis>();
        //getQueryWrapper(queryWrapper,queryForm);
        return exeRepHisMapper.selectList(queryWrapper);
    }

    @Override
    public List<ExeRepHis> getAllList(String exeRepId) {
        QueryWrapper<ExeRepHis> queryWrapper = new QueryWrapper <ExeRepHis>();
        queryWrapper.eq(ExeRepHis.EXE_REPLY_ID,exeRepId);
        return exeRepHisMapper.selectList(queryWrapper);
    }


    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ExeRepHis> getQueryWrapper(QueryWrapper<ExeRepHis> queryWrapper,ExeRepHisQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getRepHisId())){
            queryWrapper.eq(ExeRepHis.REP_HIS_ID,queryForm.getRepHisId());
        }
        if(StringUtils.isNotBlank(queryForm.getExeReplyId())){
            queryWrapper.eq(ExeRepHis.EXE_REPLY_ID,queryForm.getExeReplyId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyPartId())){
            queryWrapper.eq(ExeRepHis.REPLY_PART_ID,queryForm.getReplyPartId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyPartName())){
            queryWrapper.eq(ExeRepHis.REPLY_PART_NAME,queryForm.getReplyPartName());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyAdv())){
            queryWrapper.eq(ExeRepHis.REPLY_ADV,queryForm.getReplyAdv());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyEndCode())){
            queryWrapper.eq(ExeRepHis.REPLY_END_CODE,queryForm.getReplyEndCode());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyEndName())){
            queryWrapper.eq(ExeRepHis.REPLY_END_NAME,queryForm.getReplyEndName());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ExeRepHis.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ExeRepHis.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ExeRepHis.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ExeRepHis.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ExeRepHis.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ExeRepHis.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ExeRepHis.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(ExeRepHis.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(ExeRepHis.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

