package com.deloitte.platform.basic.bpmservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.bpmservice.param.BpmPositionQueryForm;
import com.deloitte.platform.basic.bpmservice.entity.BpmPosition;
import com.deloitte.platform.basic.bpmservice.mapper.BpmPositionMapper;
import com.deloitte.platform.basic.bpmservice.service.IBpmPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description :  BpmPosition服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BpmPositionServiceImpl extends ServiceImpl<BpmPositionMapper, BpmPosition> implements IBpmPositionService {


    @Autowired
    private BpmPositionMapper bpmPositionMapper;

    @Override
    public IPage<BpmPosition> selectPage(BpmPositionQueryForm queryForm ) {
        QueryWrapper<BpmPosition> queryWrapper = new QueryWrapper <BpmPosition>();
        //getQueryWrapper(queryWrapper,queryForm);
        return bpmPositionMapper.selectPage(new Page<BpmPosition>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BpmPosition> selectList(BpmPositionQueryForm queryForm) {
        QueryWrapper<BpmPosition> queryWrapper = new QueryWrapper <BpmPosition>();
        //getQueryWrapper(queryWrapper,queryForm);
        return bpmPositionMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<BpmPosition> getQueryWrapper(QueryWrapper<BpmPosition> queryWrapper,BaseQueryForm<BpmPositionQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(bpmPosition.getProcessDefineKey())){
            queryWrapper.like(BpmPosition.PROCESS_DEFINE_KEY,bpmPosition.getProcessDefineKey());
        }
        if(StringUtils.isNotBlank(bpmPosition.getProcessName())){
            queryWrapper.like(BpmPosition.PROCESS_NAME,bpmPosition.getProcessName());
        }
        if(StringUtils.isNotBlank(bpmPosition.getNodeId())){
            queryWrapper.like(BpmPosition.NODE_ID,bpmPosition.getNodeId());
        }
        if(StringUtils.isNotBlank(bpmPosition.getNodeName())){
            queryWrapper.like(BpmPosition.NODE_NAME,bpmPosition.getNodeName());
        }
        if(StringUtils.isNotBlank(bpmPosition.getNodeSequence())){
            queryWrapper.like(BpmPosition.NODE_SEQUENCE,bpmPosition.getNodeSequence());
        }
        if(StringUtils.isNotBlank(bpmPosition.getCreateTime())){
            queryWrapper.like(BpmPosition.CREATE_TIME,bpmPosition.getCreateTime());
        }
        if(StringUtils.isNotBlank(bpmPosition.getUpdateTime())){
            queryWrapper.like(BpmPosition.UPDATE_TIME,bpmPosition.getUpdateTime());
        }
        if(StringUtils.isNotBlank(bpmPosition.getExt1())){
            queryWrapper.like(BpmPosition.EXT1,bpmPosition.getExt1());
        }
        if(StringUtils.isNotBlank(bpmPosition.getExt2())){
            queryWrapper.like(BpmPosition.EXT2,bpmPosition.getExt2());
        }
        if(StringUtils.isNotBlank(bpmPosition.getExt3())){
            queryWrapper.like(BpmPosition.EXT3,bpmPosition.getExt3());
        }
        if(StringUtils.isNotBlank(bpmPosition.getExt4())){
            queryWrapper.like(BpmPosition.EXT4,bpmPosition.getExt4());
        }
        if(StringUtils.isNotBlank(bpmPosition.getExt5())){
            queryWrapper.like(BpmPosition.EXT5,bpmPosition.getExt5());
        }
        if(StringUtils.isNotBlank(bpmPosition.getName())){
            queryWrapper.like(BpmPosition.NAME,bpmPosition.getName());
        }
        if(StringUtils.isNotBlank(bpmPosition.getState())){
            queryWrapper.like(BpmPosition.STATE,bpmPosition.getState());
        }
        return queryWrapper;
    }
     */
}

