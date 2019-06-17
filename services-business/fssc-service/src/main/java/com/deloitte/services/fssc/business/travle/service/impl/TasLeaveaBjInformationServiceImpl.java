package com.deloitte.services.fssc.business.travle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.travle.param.TasLeaveaBjInformationQueryForm;
import com.deloitte.services.fssc.business.travle.entity.TasLeaveaBjInformation;
import com.deloitte.services.fssc.business.travle.mapper.TasLeaveaBjInformationMapper;
import com.deloitte.services.fssc.business.travle.service.ITasLeaveaBjInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description :  TasLeaveaBjInformation服务实现类
 * @Modified :
 */
@Service
@Transactional
public class TasLeaveaBjInformationServiceImpl extends ServiceImpl<TasLeaveaBjInformationMapper, TasLeaveaBjInformation> implements ITasLeaveaBjInformationService {


    @Autowired
    private TasLeaveaBjInformationMapper tasLeaveaBjInformationMapper;

    @Override
    public IPage<TasLeaveaBjInformation> selectPage(TasLeaveaBjInformationQueryForm queryForm ) {
        QueryWrapper<TasLeaveaBjInformation> queryWrapper = new QueryWrapper <TasLeaveaBjInformation>();
        //getQueryWrapper(queryWrapper,queryForm);
        return tasLeaveaBjInformationMapper.selectPage(new Page<TasLeaveaBjInformation>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<TasLeaveaBjInformation> selectList(TasLeaveaBjInformationQueryForm queryForm) {
        QueryWrapper<TasLeaveaBjInformation> queryWrapper = new QueryWrapper <TasLeaveaBjInformation>();
        //getQueryWrapper(queryWrapper,queryForm);
        return tasLeaveaBjInformationMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<TasLeaveaBjInformation> getQueryWrapper(QueryWrapper<TasLeaveaBjInformation> queryWrapper,TasLeaveaBjInformationQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(TasLeaveaBjInformation.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(TasLeaveaBjInformation.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(TasLeaveaBjInformation.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(TasLeaveaBjInformation.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(TasLeaveaBjInformation.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getExt6())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT6,queryForm.getExt6());
        }
        if(StringUtils.isNotBlank(queryForm.getExt7())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT7,queryForm.getExt7());
        }
        if(StringUtils.isNotBlank(queryForm.getExt8())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT8,queryForm.getExt8());
        }
        if(StringUtils.isNotBlank(queryForm.getExt9())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT9,queryForm.getExt9());
        }
        if(StringUtils.isNotBlank(queryForm.getExt10())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT10,queryForm.getExt10());
        }
        if(StringUtils.isNotBlank(queryForm.getExt11())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT11,queryForm.getExt11());
        }
        if(StringUtils.isNotBlank(queryForm.getExt12())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT12,queryForm.getExt12());
        }
        if(StringUtils.isNotBlank(queryForm.getExt13())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT13,queryForm.getExt13());
        }
        if(StringUtils.isNotBlank(queryForm.getExt14())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT14,queryForm.getExt14());
        }
        if(StringUtils.isNotBlank(queryForm.getExt15())){
            queryWrapper.eq(TasLeaveaBjInformation.EXT15,queryForm.getExt15());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(TasLeaveaBjInformation.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getName())){
            queryWrapper.eq(TasLeaveaBjInformation.NAME,queryForm.getName());
        }
        if(StringUtils.isNotBlank(queryForm.getJobNumber())){
            queryWrapper.eq(TasLeaveaBjInformation.JOB_NUMBER,queryForm.getJobNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getLevelName())){
            queryWrapper.eq(TasLeaveaBjInformation.LEVEL_NAME,queryForm.getLevelName());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(TasLeaveaBjInformation.LINE_NUMBER,queryForm.getLineNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(TasLeaveaBjInformation.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(TasLeaveaBjInformation.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getPost())){
            queryWrapper.eq(TasLeaveaBjInformation.POST,queryForm.getPost());
        }
        if(StringUtils.isNotBlank(queryForm.getTravelAddress())){
            queryWrapper.eq(TasLeaveaBjInformation.TRAVEL_ADDRESS,queryForm.getTravelAddress());
        }
        if(StringUtils.isNotBlank(queryForm.getLeaveaBjTime())){
            queryWrapper.eq(TasLeaveaBjInformation.LEAVEA_BJ_TIME,queryForm.getLeaveaBjTime());
        }
        if(StringUtils.isNotBlank(queryForm.getReturnBjTime())){
            queryWrapper.eq(TasLeaveaBjInformation.RETURN_BJ_TIME,queryForm.getReturnBjTime());
        }
        if(StringUtils.isNotBlank(queryForm.getYearTravelNum())){
            queryWrapper.eq(TasLeaveaBjInformation.YEAR_TRAVEL_NUM,queryForm.getYearTravelNum());
        }
        if(StringUtils.isNotBlank(queryForm.getHeadWorkBj())){
            queryWrapper.eq(TasLeaveaBjInformation.HEAD_WORK_BJ,queryForm.getHeadWorkBj());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(TasLeaveaBjInformation.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(TasLeaveaBjInformation.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

