package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.ArchiveQueryForm;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.entity.Archive;
import com.deloitte.services.contract.mapper.ArchiveMapper;
import com.deloitte.services.contract.service.IArchiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.contract.service.ICommonService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : yangyq
 * @Date : Create in 2019-06-10
 * @Description :  Archive服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ArchiveServiceImpl extends ServiceImpl<ArchiveMapper, Archive> implements IArchiveService {


    @Autowired
    private ArchiveMapper archiveMapper;
    @Autowired
    public ICommonService commonService;

    @Override
    public IPage<Archive> selectPage(ArchiveQueryForm queryForm ) {
        QueryWrapper<Archive> queryWrapper = new QueryWrapper <Archive>();
        //getQueryWrapper(queryWrapper,queryForm);
        return archiveMapper.selectPage(new Page<Archive>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Archive> selectList(ArchiveQueryForm queryForm) {
        QueryWrapper<Archive> queryWrapper = new QueryWrapper <Archive>();
        //getQueryWrapper(queryWrapper,queryForm);
        return archiveMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Archive> getQueryWrapper(QueryWrapper<Archive> queryWrapper,ArchiveQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getId())){
            queryWrapper.eq(Archive.ID,queryForm.getId());
        }
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(Archive.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getIsArchive())){
            queryWrapper.eq(Archive.IS_ARCHIVE,queryForm.getIsArchive());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(Archive.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(Archive.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(Archive.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(Archive.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(Archive.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Archive.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Archive.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Archive.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Archive.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */

    public Result saveArchiveByContractId(Archive archive){
        UserVo userVo = commonService.getCurrentUser();
        QueryWrapper<Archive> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq(Archive.CONTRACT_ID, archive.getContractId());
        Archive archiveOld = archiveMapper.selectOne(queryWrapper);
        if (archiveOld != null){
            archiveOld.setContractId(archive.getContractId());
            archiveOld.setIsArchive(archive.getIsArchive());
            archiveOld.setUpdateBy(userVo.getId());
            archiveMapper.updateById(archiveOld);
        }else {
            archive.setCreateBy(userVo.getId());
            archiveMapper.insert(archive);
        }
        return Result.success();
    }
}

