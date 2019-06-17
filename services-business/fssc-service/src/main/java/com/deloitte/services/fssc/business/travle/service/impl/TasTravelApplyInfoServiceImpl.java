package com.deloitte.services.fssc.business.travle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.travle.param.TasTravelApplyInfoQueryForm;
import com.deloitte.services.fssc.business.travle.entity.TasTravelApplyInfo;
import com.deloitte.services.fssc.business.travle.mapper.TasTravelApplyInfoMapper;
import com.deloitte.services.fssc.business.travle.service.ITasTravelApplyInfoService;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description :  TasTravleApplyInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class TasTravelApplyInfoServiceImpl extends ServiceImpl<TasTravelApplyInfoMapper, TasTravelApplyInfo> implements ITasTravelApplyInfoService {


    @Autowired
    private TasTravelApplyInfoMapper tasTravelApplyInfoMapper;

    @Override
    public IPage<TasTravelApplyInfo> selectPage(TasTravelApplyInfoQueryForm queryForm ) {
        QueryWrapper<TasTravelApplyInfo> queryWrapper = new QueryWrapper <TasTravelApplyInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId()!=null,"CREATE_BY", FsscCommonUtil.getCurrentUserId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getMainTypeId()),"MAIN_TYPE_ID",queryForm.getMainTypeId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"TOTAL_SUM",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"TOTAL_SUM",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),"PROJECT_ID",queryForm.getProjectId());
        queryWrapper.orderByDesc("CREATE_TIME");
        return tasTravelApplyInfoMapper.selectPage(new Page<TasTravelApplyInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<TasTravelApplyInfo> selectList(TasTravelApplyInfoQueryForm queryForm) {
        QueryWrapper<TasTravelApplyInfo> queryWrapper = new QueryWrapper <TasTravelApplyInfo>();
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getMainTypeId()),"MAIN_TYPE_ID",queryForm.getMainTypeId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"TOTAL_SUM",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"TOTAL_SUM",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),"PROJECT_ID",queryForm.getProjectId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateBy()),"CREATE_BY",queryForm.getCreateBy());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getWhetherBorrow()),"WHETHER_BORROW",queryForm.getWhetherBorrow());
        queryWrapper.ne(StringUtil.isNotEmpty(queryForm.getIsBorrowConnect()),"IS_BORROW_CONNECT",queryForm.getIsBorrowConnect());
        queryWrapper.orderByDesc("CREATE_TIME");
        return tasTravelApplyInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<TasTravelApplyInfo> listReimBurse(TasTravelApplyInfoQueryForm queryForm) {
        QueryWrapper<TasTravelApplyInfo> queryWrapper = new QueryWrapper <TasTravelApplyInfo>();
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getMainTypeId()),"MAIN_TYPE_ID",queryForm.getMainTypeId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"TOTAL_SUM",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"TOTAL_SUM",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getProjectId()),"PROJECT_ID",queryForm.getProjectId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateBy()),"CREATE_BY",queryForm.getCreateBy());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getWhetherBorrow()),"WHETHER_BORROW",queryForm.getWhetherBorrow());
        queryWrapper.ne(StringUtil.isNotEmpty(queryForm.getIsReimburConnect()),"IS_REIMBUR_CONNECT",queryForm.getIsReimburConnect());
        queryWrapper.orderByDesc("CREATE_TIME");
        return tasTravelApplyInfoMapper.selectList(queryWrapper);
    }

    @Override
    public boolean existsByExpenseMainTypeTaIds(List<Long> idList) {
        return tasTravelApplyInfoMapper.countByExpenseMainTypeIds(idList) > 0 ? true : false;
    }


    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<TasTravelApplyInfo> getQueryWrapper(QueryWrapper<TasTravelApplyInfo> queryWrapper,TasTravelApplyInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(TasTravelApplyInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(TasTravelApplyInfo.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(TasTravelApplyInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(TasTravelApplyInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(TasTravelApplyInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(TasTravelApplyInfo.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){
            queryWrapper.eq(TasTravelApplyInfo.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentStatus())){
            queryWrapper.eq(TasTravelApplyInfo.DOCUMENT_STATUS,queryForm.getDocumentStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitId())){
            queryWrapper.eq(TasTravelApplyInfo.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptId())){
            queryWrapper.eq(TasTravelApplyInfo.DEPT_ID,queryForm.getDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(TasTravelApplyInfo.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getMainTypeId())){
            queryWrapper.eq(TasTravelApplyInfo.MAIN_TYPE_ID,queryForm.getMainTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalSum())){
            queryWrapper.eq(TasTravelApplyInfo.TOTAL_SUM,queryForm.getTotalSum());
        }
        if(StringUtils.isNotBlank(queryForm.getIsAgreedPromis())){
            queryWrapper.eq(TasTravelApplyInfo.IS_AGREED_PROMIS,queryForm.getIsAgreedPromis());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(TasTravelApplyInfo.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitName())){
            queryWrapper.eq(TasTravelApplyInfo.UNIT_NAME,queryForm.getUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(TasTravelApplyInfo.DEPT_NAME,queryForm.getDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getMainTypeName())){
            queryWrapper.eq(TasTravelApplyInfo.MAIN_TYPE_NAME,queryForm.getMainTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.eq(TasTravelApplyInfo.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(TasTravelApplyInfo.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(TasTravelApplyInfo.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(TasTravelApplyInfo.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(TasTravelApplyInfo.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(TasTravelApplyInfo.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getExt6())){
            queryWrapper.eq(TasTravelApplyInfo.EXT6,queryForm.getExt6());
        }
        if(StringUtils.isNotBlank(queryForm.getExt7())){
            queryWrapper.eq(TasTravelApplyInfo.EXT7,queryForm.getExt7());
        }
        if(StringUtils.isNotBlank(queryForm.getExt8())){
            queryWrapper.eq(TasTravelApplyInfo.EXT8,queryForm.getExt8());
        }
        if(StringUtils.isNotBlank(queryForm.getExt9())){
            queryWrapper.eq(TasTravelApplyInfo.EXT9,queryForm.getExt9());
        }
        if(StringUtils.isNotBlank(queryForm.getExt10())){
            queryWrapper.eq(TasTravelApplyInfo.EXT10,queryForm.getExt10());
        }
        if(StringUtils.isNotBlank(queryForm.getExt11())){
            queryWrapper.eq(TasTravelApplyInfo.EXT11,queryForm.getExt11());
        }
        if(StringUtils.isNotBlank(queryForm.getExt12())){
            queryWrapper.eq(TasTravelApplyInfo.EXT12,queryForm.getExt12());
        }
        if(StringUtils.isNotBlank(queryForm.getExt13())){
            queryWrapper.eq(TasTravelApplyInfo.EXT13,queryForm.getExt13());
        }
        if(StringUtils.isNotBlank(queryForm.getExt14())){
            queryWrapper.eq(TasTravelApplyInfo.EXT14,queryForm.getExt14());
        }
        if(StringUtils.isNotBlank(queryForm.getExt15())){
            queryWrapper.eq(TasTravelApplyInfo.EXT15,queryForm.getExt15());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitCode())){
            queryWrapper.eq(TasTravelApplyInfo.UNIT_CODE,queryForm.getUnitCode());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptCode())){
            queryWrapper.eq(TasTravelApplyInfo.DEPT_CODE,queryForm.getDeptCode());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCode())){
            queryWrapper.eq(TasTravelApplyInfo.PROJECT_CODE,queryForm.getProjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getMainTypeCode())){
            queryWrapper.eq(TasTravelApplyInfo.MAIN_TYPE_CODE,queryForm.getMainTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getCurrencyCode())){
            queryWrapper.eq(TasTravelApplyInfo.CURRENCY_CODE,queryForm.getCurrencyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getWhetherLeaveBj())){
            queryWrapper.eq(TasTravelApplyInfo.WHETHER_LEAVE_BJ,queryForm.getWhetherLeaveBj());
        }
        if(StringUtils.isNotBlank(queryForm.getWhetherBorrow())){
            queryWrapper.eq(TasTravelApplyInfo.WHETHER_BORROW,queryForm.getWhetherBorrow());
        }
        if(StringUtils.isNotBlank(queryForm.getWhetherReception())){
            queryWrapper.eq(TasTravelApplyInfo.WHETHER_RECEPTION,queryForm.getWhetherReception());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(TasTravelApplyInfo.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(TasTravelApplyInfo.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

