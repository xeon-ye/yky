package com.deloitte.services.fssc.business.poi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.poi.param.PepaymentOrderInfoQueryForm;
import com.deloitte.services.fssc.business.poi.entity.PepaymentOrderInfo;
import com.deloitte.services.fssc.business.poi.mapper.PepaymentOrderInfoMapper;
import com.deloitte.services.fssc.business.poi.service.IPepaymentOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-05-13
 * @Description :  PepaymentOrderInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PepaymentOrderInfoServiceImpl extends ServiceImpl<PepaymentOrderInfoMapper, PepaymentOrderInfo> implements IPepaymentOrderInfoService {


    @Autowired
    private PepaymentOrderInfoMapper pepaymentOrderInfoMapper;

    @Override
    public IPage<PepaymentOrderInfo> selectPage(PepaymentOrderInfoQueryForm queryForm ) {
        QueryWrapper<PepaymentOrderInfo> queryWrapper = new QueryWrapper <PepaymentOrderInfo>();
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId()!=null,"CREATE_BY", FsscCommonUtil.getCurrentUserId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"RE_AMOUNT_TATOL",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"RE_AMOUNT_TATOL",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),"PROJECT_ID",queryForm.getProjectId());
        queryWrapper.orderByDesc("CREATE_TIME");
        return pepaymentOrderInfoMapper.selectPage(new Page<PepaymentOrderInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<PepaymentOrderInfo> selectList(PepaymentOrderInfoQueryForm queryForm) {
        QueryWrapper<PepaymentOrderInfo> queryWrapper = new QueryWrapper <PepaymentOrderInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return pepaymentOrderInfoMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<PepaymentOrderInfo> getQueryWrapper(QueryWrapper<PepaymentOrderInfo> queryWrapper,PepaymentOrderInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(PepaymentOrderInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(PepaymentOrderInfo.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(PepaymentOrderInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(PepaymentOrderInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(PepaymentOrderInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(PepaymentOrderInfo.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){
            queryWrapper.eq(PepaymentOrderInfo.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentStatus())){
            queryWrapper.eq(PepaymentOrderInfo.DOCUMENT_STATUS,queryForm.getDocumentStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitId())){
            queryWrapper.eq(PepaymentOrderInfo.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptId())){
            queryWrapper.eq(PepaymentOrderInfo.DEPT_ID,queryForm.getDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(PepaymentOrderInfo.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getCurrencyCode())){
            queryWrapper.eq(PepaymentOrderInfo.CURRENCY_CODE,queryForm.getCurrencyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getIsAgreedPromis())){
            queryWrapper.eq(PepaymentOrderInfo.IS_AGREED_PROMIS,queryForm.getIsAgreedPromis());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(PepaymentOrderInfo.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitName())){
            queryWrapper.eq(PepaymentOrderInfo.UNIT_NAME,queryForm.getUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(PepaymentOrderInfo.DEPT_NAME,queryForm.getDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.eq(PepaymentOrderInfo.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(PepaymentOrderInfo.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(PepaymentOrderInfo.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(PepaymentOrderInfo.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(PepaymentOrderInfo.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(PepaymentOrderInfo.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getExt6())){
            queryWrapper.eq(PepaymentOrderInfo.EXT6,queryForm.getExt6());
        }
        if(StringUtils.isNotBlank(queryForm.getExt7())){
            queryWrapper.eq(PepaymentOrderInfo.EXT7,queryForm.getExt7());
        }
        if(StringUtils.isNotBlank(queryForm.getExt8())){
            queryWrapper.eq(PepaymentOrderInfo.EXT8,queryForm.getExt8());
        }
        if(StringUtils.isNotBlank(queryForm.getExt9())){
            queryWrapper.eq(PepaymentOrderInfo.EXT9,queryForm.getExt9());
        }
        if(StringUtils.isNotBlank(queryForm.getExt10())){
            queryWrapper.eq(PepaymentOrderInfo.EXT10,queryForm.getExt10());
        }
        if(StringUtils.isNotBlank(queryForm.getExt11())){
            queryWrapper.eq(PepaymentOrderInfo.EXT11,queryForm.getExt11());
        }
        if(StringUtils.isNotBlank(queryForm.getExt12())){
            queryWrapper.eq(PepaymentOrderInfo.EXT12,queryForm.getExt12());
        }
        if(StringUtils.isNotBlank(queryForm.getExt13())){
            queryWrapper.eq(PepaymentOrderInfo.EXT13,queryForm.getExt13());
        }
        if(StringUtils.isNotBlank(queryForm.getExt14())){
            queryWrapper.eq(PepaymentOrderInfo.EXT14,queryForm.getExt14());
        }
        if(StringUtils.isNotBlank(queryForm.getExt15())){
            queryWrapper.eq(PepaymentOrderInfo.EXT15,queryForm.getExt15());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCode())){
            queryWrapper.eq(PepaymentOrderInfo.PROJECT_CODE,queryForm.getProjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getRepaymentType())){
            queryWrapper.eq(PepaymentOrderInfo.REPAYMENT_TYPE,queryForm.getRepaymentType());
        }
        if(StringUtils.isNotBlank(queryForm.getReAmountTatol())){
            queryWrapper.eq(PepaymentOrderInfo.RE_AMOUNT_TATOL,queryForm.getReAmountTatol());
        }
        if(StringUtils.isNotBlank(queryForm.getIsAfterPatch())){
            queryWrapper.eq(PepaymentOrderInfo.IS_AFTER_PATCH,queryForm.getIsAfterPatch());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(PepaymentOrderInfo.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(PepaymentOrderInfo.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

