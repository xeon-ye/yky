package com.deloitte.services.fssc.business.carryforward.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.carryforward.param.IncomeOfCarryForwardQueryForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.services.fssc.business.carryforward.entity.IncomeOfCarryForward;
import com.deloitte.services.fssc.business.carryforward.mapper.IncomeOfCarryForwardMapper;
import com.deloitte.services.fssc.business.carryforward.service.IIncomeOfCarryForwardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang.StringUtils;



import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-05-06
 * @Description :  IncomeOfCarryForward服务实现类
 * @Modified :
 */
@Service
@Transactional
public class IncomeOfCarryForwardServiceImpl extends ServiceImpl<IncomeOfCarryForwardMapper, IncomeOfCarryForward> implements IIncomeOfCarryForwardService {


    @Autowired
    private IncomeOfCarryForwardMapper incomeOfCarryForwardMapper;

    @Autowired
    private FsscCommonServices commonServices;

    @Override
    public IPage<IncomeOfCarryForward> selectPage(IncomeOfCarryForwardQueryForm queryForm ) {
        QueryWrapper<IncomeOfCarryForward> queryWrapper = new QueryWrapper <IncomeOfCarryForward>();
        getQueryWrapper(queryWrapper,queryForm);
        return incomeOfCarryForwardMapper.selectPage(new Page<IncomeOfCarryForward>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<IncomeOfCarryForward> selectList(IncomeOfCarryForwardQueryForm queryForm) {
        QueryWrapper<IncomeOfCarryForward> queryWrapper = new QueryWrapper <IncomeOfCarryForward>();
        //getQueryWrapper(queryWrapper,queryForm);
        return incomeOfCarryForwardMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<IncomeOfCarryForward> getQueryWrapper(QueryWrapper<IncomeOfCarryForward> queryWrapper,IncomeOfCarryForwardQueryForm queryForm){
        //条件拼接
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitId(Long.parseLong(deptVo.getDeptId()));
        if(queryForm.getMainTypeId()!=null){
            queryWrapper.eq(IncomeOfCarryForward.MAIN_TYPE_ID,queryForm.getMainTypeId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){
            queryWrapper.like(IncomeOfCarryForward.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(IncomeOfCarryForward.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCode())){
            queryWrapper.eq(IncomeOfCarryForward.PROJECT_CODE,queryForm.getProjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.eq(IncomeOfCarryForward.PROJECT_NAME,queryForm.getProjectName());
        }
        if(queryForm.getUnitId()!=null){
            queryWrapper.eq(IncomeOfCarryForward.UNIT_ID,queryForm.getUnitId());
        }
        if(queryForm.getDeptId()!=null){
            queryWrapper.eq(IncomeOfCarryForward.DEPT_ID,queryForm.getDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getUnitName())){
            queryWrapper.eq(IncomeOfCarryForward.UNIT_NAME,queryForm.getUnitName());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(IncomeOfCarryForward.DEPT_NAME,queryForm.getDeptName());
        }
        if(queryForm.getEnterDate()!=null){
            queryWrapper.eq(IncomeOfCarryForward.ENTER_DATE,queryForm.getEnterDate());
        }
        if(queryForm.getMoney()!=null){
            queryWrapper.eq(IncomeOfCarryForward.MONEY,queryForm.getMoney());
        }
        if(StringUtils.isNotBlank(queryForm.getStatus())){
            queryWrapper.eq(IncomeOfCarryForward.STATUS,queryForm.getStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(IncomeOfCarryForward.REMARK,queryForm.getRemark());
        }
        if(queryForm.getMoneyStart()!=null){
            queryWrapper.ge(IncomeOfCarryForward.MONEY,queryForm.getMoneyStart());
        }
        if(queryForm.getMoneyEnd()!=null){
            queryWrapper.le(IncomeOfCarryForward.MONEY,queryForm.getMoneyEnd());
        }
        if(queryForm.getEnterDateStart()!=null){
            queryWrapper.ge(IncomeOfCarryForward.ENTER_DATE,queryForm.getEnterDateStart());
        }
        if(queryForm.getEnterDateEnd()!=null){
            queryWrapper.le(IncomeOfCarryForward.ENTER_DATE,queryForm.getEnterDateEnd());
        }
        return queryWrapper;
    }

    public  Integer hasCarryId (Long id){
        return incomeOfCarryForwardMapper.hasCarryId(id);
    }

   public  List<IncomeOfCarryForward> selectListByManual(IncomeOfCarryForwardQueryForm form){
      return incomeOfCarryForwardMapper.selectListByManual(form);
   }

}

