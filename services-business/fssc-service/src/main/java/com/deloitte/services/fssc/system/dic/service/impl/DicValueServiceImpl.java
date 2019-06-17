package com.deloitte.services.fssc.system.dic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.dic.param.DicValueQueryForm;
import com.deloitte.services.fssc.system.dic.entity.DicValue;
import com.deloitte.services.fssc.system.dic.mapper.DicValueMapper;
import com.deloitte.services.fssc.system.dic.service.IDicValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description :  DicValue服务实现类
 * @Modified :
 */
@Service
@Transactional
public class DicValueServiceImpl extends ServiceImpl<DicValueMapper, DicValue> implements IDicValueService {


    @Autowired
    private DicValueMapper dicValueMapper;

    /*@Override
    public IPage<DicValue> selectPage(DicValueQueryForm queryForm ) {
        QueryWrapper<DicValue> queryWrapper = new QueryWrapper <DicValue>();
        //getQueryWrapper(queryWrapper,queryForm);
        return dicValueMapper.selectPage(new Page<DicValue>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }
*/
    @Override
    public IPage<DicValue> selectPage(DicValueQueryForm queryForm) {
        Page<DicValue> page = new Page(queryForm.getCurrentPage(), queryForm.getPageSize());
        List<DicValue> unitInfoVos =
                dicValueMapper.selectByPageConditions(page, queryForm);
        page.setRecords(unitInfoVos);
        return page;
    }
    @Override
    public List<DicValue> selectList(DicValueQueryForm queryForm) {
        QueryWrapper<DicValue> queryWrapper = new QueryWrapper <DicValue>();
        //getQueryWrapper(queryWrapper,queryForm);
        return dicValueMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<DicValue> getQueryWrapper(QueryWrapper<DicValue> queryWrapper,BaseQueryForm<DicValueQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(dicValue.getDicParentId())){
            queryWrapper.like(DicValue.DIC_PARENT_ID,dicValue.getDicParentId());
        }
        if(StringUtils.isNotBlank(dicValue.getDicCode())){
            queryWrapper.like(DicValue.DIC_CODE,dicValue.getDicCode());
        }
        if(StringUtils.isNotBlank(dicValue.getDicName())){
            queryWrapper.like(DicValue.DIC_NAME,dicValue.getDicName());
        }
        if(StringUtils.isNotBlank(dicValue.getDicValue())){
            queryWrapper.like(DicValue.DIC_VALUE,dicValue.getDicValue());
        }
        if(StringUtils.isNotBlank(dicValue.getDicDesciption())){
            queryWrapper.like(DicValue.DIC_DESCIPTION,dicValue.getDicDesciption());
        }
        if(StringUtils.isNotBlank(dicValue.getDicOrder())){
            queryWrapper.like(DicValue.DIC_ORDER,dicValue.getDicOrder());
        }
        if(StringUtils.isNotBlank(dicValue.getIsValid())){
            queryWrapper.like(DicValue.IS_VALID,dicValue.getIsValid());
        }
        if(StringUtils.isNotBlank(dicValue.getExt1())){
            queryWrapper.like(DicValue.EXT_1,dicValue.getExt1());
        }
        if(StringUtils.isNotBlank(dicValue.getExt2())){
            queryWrapper.like(DicValue.EXT_2,dicValue.getExt2());
        }
        if(StringUtils.isNotBlank(dicValue.getExt3())){
            queryWrapper.like(DicValue.EXT_3,dicValue.getExt3());
        }
        if(StringUtils.isNotBlank(dicValue.getExt4())){
            queryWrapper.like(DicValue.EXT_4,dicValue.getExt4());
        }
        if(StringUtils.isNotBlank(dicValue.getExt5())){
            queryWrapper.like(DicValue.EXT_5,dicValue.getExt5());
        }
        if(StringUtils.isNotBlank(dicValue.getCreateTime())){
            queryWrapper.like(DicValue.CREATE_TIME,dicValue.getCreateTime());
        }
        if(StringUtils.isNotBlank(dicValue.getUpdateTime())){
            queryWrapper.like(DicValue.UPDATE_TIME,dicValue.getUpdateTime());
        }
        if(StringUtils.isNotBlank(dicValue.getCreateBy())){
            queryWrapper.like(DicValue.CREATE_BY,dicValue.getCreateBy());
        }
        if(StringUtils.isNotBlank(dicValue.getUpdateBy())){
            queryWrapper.like(DicValue.UPDATE_BY,dicValue.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

