package com.deloitte.services.dss.scientific.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.scientific.vo.ResultColumnVo;
import com.deloitte.platform.api.dss.scientific.vo.ResultVo;
import com.deloitte.services.dss.scientific.mapper.ResultMapper;
import com.deloitte.services.dss.scientific.service.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : hmz
 * @Date : Create in 2019-03-01
 */
@Service
@Transactional
public class IResultServiceImpl extends ServiceImpl implements IResultService {
    @Autowired
    public ResultMapper resultMapper;
    @Override
    public List<ResultVo> queryResult(){
        return resultMapper.queryResult();
    }
    @Override
    public List<ResultColumnVo> queryResultColumn(){
        return resultMapper.queryResultColumn();
    }
}
