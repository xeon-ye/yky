package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.BasicConfiguraQueryForm;
import com.deloitte.platform.api.contract.vo.BasicConfiguraForm;
import com.deloitte.platform.api.contract.vo.BasicConfiguraVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.entity.BasicConfigura;
import com.deloitte.services.contract.mapper.BasicConfiguraMapper;
import com.deloitte.services.contract.service.IBasicConfiguraService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : yangyq
 * @Date : Create in 2019-04-23
 * @Description :  BasicConfigura服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BasicConfiguraServiceImpl extends ServiceImpl<BasicConfiguraMapper, BasicConfigura> implements IBasicConfiguraService {


    @Autowired
    private BasicConfiguraMapper basicConfiguraMapper;

    @Override
    public IPage<BasicConfigura> selectPage(BasicConfiguraQueryForm queryForm ) {
        QueryWrapper<BasicConfigura> queryWrapper = new QueryWrapper <BasicConfigura>();
        //getQueryWrapper(queryWrapper,queryForm);
        return basicConfiguraMapper.selectPage(new Page<BasicConfigura>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BasicConfigura> selectList(BasicConfiguraQueryForm queryForm) {
        QueryWrapper<BasicConfigura> queryWrapper = new QueryWrapper <BasicConfigura>();
        queryWrapper.eq(BasicConfigura.CONTRACT_CODE,queryForm.getContractCode());
//        getQueryWrapper(queryWrapper,queryForm);
        return basicConfiguraMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<BasicConfigura> getQueryWrapper(QueryWrapper<BasicConfigura> queryWrapper,BasicConfiguraQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getId())){
            queryWrapper.eq(BasicConfigura.ID,queryForm.getId());
        }
        if(StringUtils.isNotBlank(queryForm.getContractCode())){
            queryWrapper.eq(BasicConfigura.CONTRACT_CODE,queryForm.getContractCode());
        }
        if(StringUtils.isNotBlank(queryForm.getBasicFlag())){
            queryWrapper.eq(BasicConfigura.BASIC_FLAG,queryForm.getBasicFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getContractFlag())){
            queryWrapper.eq(BasicConfigura.CONTRACT_FLAG,queryForm.getContractFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getExecutFlag())){
            queryWrapper.eq(BasicConfigura.EXECUT_FLAG,queryForm.getExecutFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getFinanceFlag())){
            queryWrapper.eq(BasicConfigura.FINANCE_FLAG,queryForm.getFinanceFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getOrderFlag())){
            queryWrapper.eq(BasicConfigura.ORDER_FLAG,queryForm.getOrderFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectFlag())){
            queryWrapper.eq(BasicConfigura.PROJECT_FLAG,queryForm.getProjectFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getMonitorFlag())){
            queryWrapper.eq(BasicConfigura.MONITOR_FLAG,queryForm.getMonitorFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getTicketFlag())){
            queryWrapper.eq(BasicConfigura.TICKET_FLAG,queryForm.getTicketFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(BasicConfigura.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(BasicConfigura.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(BasicConfigura.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(BasicConfigura.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */

    /**
     * 保存合同信息配置
     * @param basicConfiguraForm
     * @return
     */
    public Result<BasicConfiguraVo> saveBasicConfigura(BasicConfiguraForm basicConfiguraForm){
        List<BasicConfiguraForm> listBasicConfiguraForm = basicConfiguraForm.getBasicConfiguraFormList();
        List<BasicConfigura> listBasicConfigura = new BeanUtils<BasicConfigura>().copyObjs(listBasicConfiguraForm, BasicConfigura.class);
        BasicConfigura basicConfigura = null;
        if (listBasicConfigura != null && listBasicConfigura.size() > 0){
            for (int i=0; i < listBasicConfigura.size(); i++){
                basicConfigura = listBasicConfigura.get(i);
                if (basicConfigura.getId() != null){
                    basicConfiguraMapper.updateById(basicConfigura);
                }else{
                    basicConfiguraMapper.insert(basicConfigura);
                }
            }
        }
        return Result.success();
    }
}
