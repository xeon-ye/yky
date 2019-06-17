package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.CommonReasonQueryForm;
import com.deloitte.services.contract.entity.CommonReason;
import com.deloitte.services.contract.mapper.CommonReasonMapper;
import com.deloitte.services.contract.service.ICommonReasonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-09
 * @Description :  CommonReason服务实现类
 * @Modified :
 */
@Service
@Transactional
public class CommonReasonServiceImpl extends ServiceImpl<CommonReasonMapper, CommonReason> implements ICommonReasonService {


    @Autowired
    private CommonReasonMapper commonReasonMapper;

    @Override
    public IPage<CommonReason> selectPage(CommonReasonQueryForm queryForm ) {
        QueryWrapper<CommonReason> queryWrapper = new QueryWrapper <CommonReason>();
        getQueryWrapper(queryWrapper,queryForm);
        return commonReasonMapper.selectPage(new Page<CommonReason>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<CommonReason> selectList(CommonReasonQueryForm queryForm) {
        QueryWrapper<CommonReason> queryWrapper = new QueryWrapper <CommonReason>();
        //getQueryWrapper(queryWrapper,queryForm);
        return commonReasonMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<CommonReason> getQueryWrapper(QueryWrapper<CommonReason> queryWrapper,CommonReasonQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getReason())){
            queryWrapper.eq(CommonReason.REASON,queryForm.getReason());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(CommonReason.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(CommonReason.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getReasonType())){
            queryWrapper.eq(CommonReason.REASON_TYPE,queryForm.getReasonType());
        }
        return queryWrapper;
    }

}

