package com.deloitte.services.fssc.business.ito.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.ito.param.IncomeTurnedOverQueryForm;
import com.deloitte.platform.api.fssc.ito.vo.DetailsOfFundsVo;
import com.deloitte.platform.api.fssc.ito.vo.IncomeTurnedOverVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.ito.entity.DetailsOfFunds;
import com.deloitte.services.fssc.business.ito.entity.IncomeTurnedOver;
import com.deloitte.services.fssc.business.ito.mapper.DetailsOfFundsMapper;
import com.deloitte.services.fssc.business.ito.mapper.IncomeTurnedOverMapper;
import com.deloitte.services.fssc.business.ito.service.IIncomeTurnedOverService;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-15
 * @Description :  IncomeTurnedOver服务实现类
 * @Modified :
 */
@Service
@Transactional
public class IncomeTurnedOverServiceImpl extends ServiceImpl<IncomeTurnedOverMapper, IncomeTurnedOver> implements IIncomeTurnedOverService {


    @Autowired
    private IncomeTurnedOverMapper incomeTurnedOverMapper;

    @Autowired
    private BpmTaskBiz bpmTaskBiz;

    @Autowired
    private DetailsOfFundsMapper detailsOfFundsMapper;

    @Override
    public IPage<IncomeTurnedOver> selectPage(IncomeTurnedOverQueryForm queryForm ) {
        QueryWrapper<IncomeTurnedOver> queryWrapper = new QueryWrapper <IncomeTurnedOver>();
        queryWrapper.eq(FsscCommonUtil.getCurrentUserId()!=null,"CREATE_BY", FsscCommonUtil.getCurrentUserId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()),"DOCUMENT_NUM",queryForm.getDocumentNum());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentStatus()),"DOCUMENT_STATUS",queryForm.getDocumentStatus());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getCreateUserName()),"CREATE_USER_NAME",queryForm.getCreateUserName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getUnitId()),"UNIT_ID",queryForm.getUnitId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDeptId()),"DEPT_ID",queryForm.getDeptId());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getIncomeTypeId()),"INCOME_TYPE_ID",queryForm.getIncomeTypeId());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getMoneyStart()),"TOTAL_SUM",queryForm.getMoneyStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getMoneyEnd()),"TOTAL_SUM",queryForm.getMoneyEnd());
        queryWrapper.ge(StringUtil.isNotEmpty(queryForm.getTimeStart()),"CREATE_TIME",queryForm.getTimeStart());
        queryWrapper.le(StringUtil.isNotEmpty(queryForm.getTimeEnd()),"CREATE_TIME",queryForm.getTimeEnd());
        queryWrapper.orderByDesc("CREATE_TIME");
        return incomeTurnedOverMapper.selectPage(new Page<IncomeTurnedOver>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public IncomeTurnedOverVo findInfoById(Long id) {

        //主表信息
        IncomeTurnedOver byId = incomeTurnedOverMapper.selectById(id);
        IncomeTurnedOverVo incomeTurnedOverVo =
                new BeanUtils<IncomeTurnedOverVo>().copyObj(byId, IncomeTurnedOverVo.class);

        //行信息
        QueryWrapper<DetailsOfFunds> wrapper=new QueryWrapper<>();
        wrapper.eq("DOCUMENT_ID",id).eq("DOCUMENT_TYPE",FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue());
        List<DetailsOfFunds> lines = detailsOfFundsMapper.selectList(wrapper);
        List<DetailsOfFundsVo>  detailsOfFundsVo=
                new BeanUtils<DetailsOfFundsVo>().copyObjs(lines, DetailsOfFundsVo.class);
        incomeTurnedOverVo.setDetailsOfFundsVos(detailsOfFundsVo);
        //对收入上缴单批历史信息
        String documentType= FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue();
        try {
            List<BpmProcessTaskVo> history = bpmTaskBiz.findHistory(id, documentType);
            incomeTurnedOverVo.setBpmProcessTaskVos(new BeanUtils<BpmProcessTaskVo>().copyObjs(history,BpmProcessTaskVo.class));
        }catch (FSSCException e){
            e.getMessage();
        }
        return incomeTurnedOverVo;
    }

    @Override
    public List<IncomeTurnedOver> selectList(IncomeTurnedOverQueryForm queryForm) {
        QueryWrapper<IncomeTurnedOver> queryWrapper = new QueryWrapper <IncomeTurnedOver>();
        //getQueryWrapper(queryWrapper,queryForm);
        return incomeTurnedOverMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<IncomeTurnedOver> getQueryWrapper(QueryWrapper<IncomeTurnedOver> queryWrapper,IncomeTurnedOverQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(IncomeTurnedOver.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(IncomeTurnedOver.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(IncomeTurnedOver.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(IncomeTurnedOver.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(IncomeTurnedOver.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(IncomeTurnedOver.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){
            queryWrapper.eq(IncomeTurnedOver.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentStatus())){
            queryWrapper.eq(IncomeTurnedOver.DOCUMENT_STATUS,queryForm.getDocumentStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitId())){
            queryWrapper.eq(IncomeTurnedOver.UNIT_ID,queryForm.getUnitId());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptId())){
            queryWrapper.eq(IncomeTurnedOver.DEPT_ID,queryForm.getDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getIncomeTypeId())){
            queryWrapper.eq(IncomeTurnedOver.INCOME_TYPE_ID,queryForm.getIncomeTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getIsAgreedPromis())){
            queryWrapper.eq(IncomeTurnedOver.IS_AGREED_PROMIS,queryForm.getIsAgreedPromis());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(IncomeTurnedOver.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitName())){
            queryWrapper.eq(IncomeTurnedOver.UNIT_NAME,queryForm.getUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(IncomeTurnedOver.DEPT_NAME,queryForm.getDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getIncomeTypeName())){
            queryWrapper.eq(IncomeTurnedOver.INCOME_TYPE_NAME,queryForm.getIncomeTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(IncomeTurnedOver.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(IncomeTurnedOver.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(IncomeTurnedOver.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(IncomeTurnedOver.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(IncomeTurnedOver.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getExt6())){
            queryWrapper.eq(IncomeTurnedOver.EXT6,queryForm.getExt6());
        }
        if(StringUtils.isNotBlank(queryForm.getExt7())){
            queryWrapper.eq(IncomeTurnedOver.EXT7,queryForm.getExt7());
        }
        if(StringUtils.isNotBlank(queryForm.getExt8())){
            queryWrapper.eq(IncomeTurnedOver.EXT8,queryForm.getExt8());
        }
        if(StringUtils.isNotBlank(queryForm.getExt9())){
            queryWrapper.eq(IncomeTurnedOver.EXT9,queryForm.getExt9());
        }
        if(StringUtils.isNotBlank(queryForm.getExt10())){
            queryWrapper.eq(IncomeTurnedOver.EXT10,queryForm.getExt10());
        }
        if(StringUtils.isNotBlank(queryForm.getExt11())){
            queryWrapper.eq(IncomeTurnedOver.EXT11,queryForm.getExt11());
        }
        if(StringUtils.isNotBlank(queryForm.getExt12())){
            queryWrapper.eq(IncomeTurnedOver.EXT12,queryForm.getExt12());
        }
        if(StringUtils.isNotBlank(queryForm.getExt13())){
            queryWrapper.eq(IncomeTurnedOver.EXT13,queryForm.getExt13());
        }
        if(StringUtils.isNotBlank(queryForm.getExt14())){
            queryWrapper.eq(IncomeTurnedOver.EXT14,queryForm.getExt14());
        }
        if(StringUtils.isNotBlank(queryForm.getExt15())){
            queryWrapper.eq(IncomeTurnedOver.EXT15,queryForm.getExt15());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitCode())){
            queryWrapper.eq(IncomeTurnedOver.UNIT_CODE,queryForm.getUnitCode());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptCode())){
            queryWrapper.eq(IncomeTurnedOver.DEPT_CODE,queryForm.getDeptCode());
        }
        if(StringUtils.isNotBlank(queryForm.getIncomeTypeCode())){
            queryWrapper.eq(IncomeTurnedOver.INCOME_TYPE_CODE,queryForm.getIncomeTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalSum())){
            queryWrapper.eq(IncomeTurnedOver.TOTAL_SUM,queryForm.getTotalSum());
        }
        if(StringUtils.isNotBlank(queryForm.getCurrencyCode())){
            queryWrapper.eq(IncomeTurnedOver.CURRENCY_CODE,queryForm.getCurrencyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getPaymentCode())){
            queryWrapper.eq(IncomeTurnedOver.PAYMENT_CODE,queryForm.getPaymentCode());
        }
        return queryWrapper;
    }
     */
}

