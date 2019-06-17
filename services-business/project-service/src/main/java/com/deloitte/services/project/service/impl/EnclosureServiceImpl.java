package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.project.param.EnclosureQueryForm;
import com.deloitte.platform.api.project.vo.CancelProjectFrom;
import com.deloitte.platform.api.project.vo.EnclosureForm;
import com.deloitte.platform.api.project.vo.FileVo;
import com.deloitte.platform.api.project.vo.ProjectsForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.common.util.AssertUtils;
import com.deloitte.services.project.common.util.ConstantUtils;
import com.deloitte.services.project.entity.Enclosure;
import com.deloitte.services.project.entity.Projects;
import com.deloitte.services.project.mapper.EnclosureMapper;
import com.deloitte.services.project.service.IEnclosureService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Enclosure服务实现类
 * @Modified :
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EnclosureServiceImpl extends ServiceImpl<EnclosureMapper, Enclosure> implements IEnclosureService {


    @Autowired
    private EnclosureMapper enclosureMapper;
    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @Override
    public IPage<Enclosure> selectPage(EnclosureQueryForm queryForm ) {
        QueryWrapper<Enclosure> queryWrapper = new QueryWrapper <Enclosure>();
        //getQueryWrapper(queryWrapper,queryForm);
        return enclosureMapper.selectPage(new Page<Enclosure>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Enclosure> selectList(EnclosureQueryForm queryForm) {
        QueryWrapper<Enclosure> queryWrapper = new QueryWrapper <Enclosure>();
        //getQueryWrapper(queryWrapper,queryForm);
        return enclosureMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveEnclosureFile(CancelProjectFrom cancelProjectFrom) {
        AssertUtils.asserts(Objects.isNull(cancelProjectFrom), ProjectErrorType.DATA_IS_NULL);
        /*ProjectsForm projectsForm = cancelProjectFrom.getProjectsForm();
        List<FileVo> fileVoList = cancelProjectFrom.getFileVoList();
        if (Objects.nonNull(projectsForm) && CollectionUtils.isNotEmpty(fileVoList)) {
            List<Enclosure> enclosureList = Lists.newArrayList();
            for (FileVo fileVo : fileVoList) {
                Enclosure enclosure = new Enclosure();
                enclosure.setProjectId(projectsForm.getProjectId());
                enclosure.setUploadTime(fileVo.getUploadTime());
                enclosure.setEnclosureType(fileVo.getFileExt());
                enclosure.setEnclosureName(fileVo.getFileName());
                enclosure.setEnclosureUrl(fileVo.getFileUrl());
                enclosure.setFileId(fileVo.getId());
                enclosureList.add(enclosure);
            }
            if (CollectionUtils.isNotEmpty(enclosureList)) {
                this.saveBatch(enclosureList);
                for (Enclosure enclosure : enclosureList) {
                    enclosure.setEnclosureId(String.valueOf(enclosure.getId()));
                }
                this.saveOrUpdateBatch(enclosureList);
            }
        }*/
    }

    @Override
    public List<Enclosure> selectListByAppId(String applicationId) {
        QueryWrapper<Enclosure> queryWrapper = new QueryWrapper <Enclosure>();
        queryWrapper.eq(Enclosure.APPLICATION_ID,applicationId);
        return enclosureMapper.selectList(queryWrapper);
    }

    @Override
    public List<Enclosure> selectListByRevId(String reviewId) {
        QueryWrapper<Enclosure> queryWrapper = new QueryWrapper <Enclosure>();
        queryWrapper.eq(Enclosure.REVIEW_ID,reviewId);
        return enclosureMapper.selectList(queryWrapper);
    }

    @Override
    public void delListByRev(String reviewId) {
        QueryWrapper<Enclosure> queryWrapper = new QueryWrapper <Enclosure>();
        queryWrapper.eq(Enclosure.REVIEW_ID,reviewId);
        enclosureMapper.delete(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Enclosure> getQueryWrapper(QueryWrapper<Enclosure> queryWrapper,EnclosureQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getEnclosureId())){
            queryWrapper.eq(Enclosure.ENCLOSURE_ID,queryForm.getEnclosureId());
        }
        if(StringUtils.isNotBlank(queryForm.getEnclosureName())){
            queryWrapper.eq(Enclosure.ENCLOSURE_NAME,queryForm.getEnclosureName());
        }
        if(StringUtils.isNotBlank(queryForm.getEnclosureType())){
            queryWrapper.eq(Enclosure.ENCLOSURE_TYPE,queryForm.getEnclosureType());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(Enclosure.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.eq(Enclosure.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(Enclosure.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Enclosure.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Enclosure.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Enclosure.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Enclosure.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Enclosure.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Enclosure.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Enclosure.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Enclosure.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Enclosure.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Enclosure.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Enclosure.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

