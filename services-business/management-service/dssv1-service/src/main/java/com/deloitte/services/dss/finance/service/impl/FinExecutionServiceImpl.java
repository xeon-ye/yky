package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.finance.vo.FinExecutionVo;
import com.deloitte.services.dss.finance.mapper.FinExecutionMapper;
import com.deloitte.services.dss.finance.service.IFinExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description :  FinExecution服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinExecutionServiceImpl extends ServiceImpl implements IFinExecutionService {

    @Autowired
    FinExecutionMapper finExecutionMapper;

    @Override
    public FinExecutionVo queryTotalExe(Map map) {
        FinExecutionVo finExecutionVos = finExecutionMapper.queryTotalExe(map);
        return finExecutionVos;
    }
}
