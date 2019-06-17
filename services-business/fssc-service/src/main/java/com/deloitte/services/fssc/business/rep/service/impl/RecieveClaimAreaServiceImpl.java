package com.deloitte.services.fssc.business.rep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.rep.param.RecieveClaimAreaQueryForm;
import com.deloitte.services.fssc.business.rep.entity.RecieveClaimArea;
import com.deloitte.services.fssc.business.rep.mapper.RecieveClaimAreaMapper;
import com.deloitte.services.fssc.business.rep.service.IRecieveClaimAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description :  RecieveClaimArea服务实现类
 * @Modified :
 */
@Service
@Transactional
public class RecieveClaimAreaServiceImpl extends ServiceImpl<RecieveClaimAreaMapper, RecieveClaimArea> implements IRecieveClaimAreaService {


    @Autowired
    private RecieveClaimAreaMapper recieveClaimAreaMapper;

    @Override
    public IPage<RecieveClaimArea> selectPage(RecieveClaimAreaQueryForm queryForm ) {
        QueryWrapper<RecieveClaimArea> queryWrapper = new QueryWrapper <RecieveClaimArea>();
        //getQueryWrapper(queryWrapper,queryForm);
        return recieveClaimAreaMapper.selectPage(new Page<RecieveClaimArea>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<RecieveClaimArea> selectList(RecieveClaimAreaQueryForm queryForm) {
        QueryWrapper<RecieveClaimArea> queryWrapper = new QueryWrapper <RecieveClaimArea>();
        //getQueryWrapper(queryWrapper,queryForm);
        return recieveClaimAreaMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<RecieveClaimArea> getQueryWrapper(QueryWrapper<RecieveClaimArea> queryWrapper,RecieveClaimAreaQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(RecieveClaimArea.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(RecieveClaimArea.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(RecieveClaimArea.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(RecieveClaimArea.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(RecieveClaimArea.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(RecieveClaimArea.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(RecieveClaimArea.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(RecieveClaimArea.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getClaimDeptCode())){
            queryWrapper.eq(RecieveClaimArea.CLAIM_DEPT_CODE,queryForm.getClaimDeptCode());
        }
        if(StringUtils.isNotBlank(queryForm.getClaimDeptName())){
            queryWrapper.eq(RecieveClaimArea.CLAIM_DEPT_NAME,queryForm.getClaimDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getClaimDeptId())){
            queryWrapper.eq(RecieveClaimArea.CLAIM_DEPT_ID,queryForm.getClaimDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getClaimEmpId())){
            queryWrapper.eq(RecieveClaimArea.CLAIM_EMP_ID,queryForm.getClaimEmpId());
        }
        if(StringUtils.isNotBlank(queryForm.getClaimEmpName())){
            queryWrapper.eq(RecieveClaimArea.CLAIM_EMP_NAME,queryForm.getClaimEmpName());
        }
        if(StringUtils.isNotBlank(queryForm.getClaimEmpNo())){
            queryWrapper.eq(RecieveClaimArea.CLAIM_EMP_NO,queryForm.getClaimEmpNo());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(RecieveClaimArea.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(RecieveClaimArea.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(RecieveClaimArea.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(RecieveClaimArea.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(RecieveClaimArea.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(RecieveClaimArea.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(RecieveClaimArea.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(RecieveClaimArea.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(RecieveClaimArea.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(RecieveClaimArea.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(RecieveClaimArea.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(RecieveClaimArea.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(RecieveClaimArea.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(RecieveClaimArea.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(RecieveClaimArea.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(RecieveClaimArea.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getLineNumber())){
            queryWrapper.eq(RecieveClaimArea.LINE_NUMBER,queryForm.getLineNumber());
        }
        return queryWrapper;
    }
     */
}

