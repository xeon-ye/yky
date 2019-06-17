package com.deloitte.services.fssc.engine.automatic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvLogicHeadLineDicQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvLogicHeadLineDic;
import com.deloitte.services.fssc.engine.automatic.service.IAvLogicHeadLineDicService;
import com.deloitte.services.fssc.engine.automatic.mapper.AvLogicHeadLineDicMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-04-26
 * @Description :  AvLogicHeadLineDic服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AvLogicHeadLineDicServiceImpl extends ServiceImpl<AvLogicHeadLineDicMapper, AvLogicHeadLineDic> implements IAvLogicHeadLineDicService {


    @Autowired
    private AvLogicHeadLineDicMapper avLogicHeadLineDicMapper;

    @Override
    public IPage<AvLogicHeadLineDic> selectPage(AvLogicHeadLineDicQueryForm queryForm ) {
        QueryWrapper<AvLogicHeadLineDic> queryWrapper = new QueryWrapper <AvLogicHeadLineDic>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avLogicHeadLineDicMapper.selectPage(new Page<AvLogicHeadLineDic>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AvLogicHeadLineDic> selectList(AvLogicHeadLineDicQueryForm queryForm) {
        QueryWrapper<AvLogicHeadLineDic> queryWrapper = new QueryWrapper <AvLogicHeadLineDic>();
        //getQueryWrapper(queryWrapper,queryForm);
        return avLogicHeadLineDicMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<AvLogicHeadLineDic> getQueryWrapper(QueryWrapper<AvLogicHeadLineDic> queryWrapper,AvLogicHeadLineDicQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getDocumentModule())){
            queryWrapper.eq(AvLogicHeadLineDic.DOCUMENT_MODULE,queryForm.getDocumentModule());
        }
        if(StringUtils.isNotBlank(queryForm.getHeadTable())){
            queryWrapper.eq(AvLogicHeadLineDic.HEAD_TABLE,queryForm.getHeadTable());
        }
        if(StringUtils.isNotBlank(queryForm.getLineTable())){
            queryWrapper.eq(AvLogicHeadLineDic.LINE_TABLE,queryForm.getLineTable());
        }
        if(StringUtils.isNotBlank(queryForm.getLineType())){
            queryWrapper.eq(AvLogicHeadLineDic.LINE_TYPE,queryForm.getLineType());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx1())){
            queryWrapper.eq(AvLogicHeadLineDic.ETX_1,queryForm.getEtx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx2())){
            queryWrapper.eq(AvLogicHeadLineDic.ETX_2,queryForm.getEtx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx3())){
            queryWrapper.eq(AvLogicHeadLineDic.ETX_3,queryForm.getEtx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx4())){
            queryWrapper.eq(AvLogicHeadLineDic.ETX_4,queryForm.getEtx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx5())){
            queryWrapper.eq(AvLogicHeadLineDic.ETX_5,queryForm.getEtx5());
        }
        return queryWrapper;
    }
     */
}

