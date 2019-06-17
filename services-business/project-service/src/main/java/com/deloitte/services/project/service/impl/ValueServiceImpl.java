package com.deloitte.services.project.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.project.param.ValueQueryForm;
import com.deloitte.platform.api.project.vo.ValueTypeVo;
import com.deloitte.platform.api.project.vo.ValueVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.common.util.AssertUtils;
import com.deloitte.services.project.entity.Value;
import com.deloitte.services.project.mapper.ValueMapper;
import com.deloitte.services.project.service.IValueService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Value服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ValueServiceImpl extends ServiceImpl<ValueMapper, Value> implements IValueService {


    @Autowired
    private ValueMapper valueMapper;

    @Override
    public IPage<Value> selectPage(ValueQueryForm queryForm ) {
        QueryWrapper<Value> queryWrapper = new QueryWrapper <Value>();
        //getQueryWrapper(queryWrapper,queryForm);
        return valueMapper.selectPage(new Page<Value>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Value> selectList(ValueQueryForm queryForm) {
        QueryWrapper<Value> queryWrapper = new QueryWrapper <Value>();
        //getQueryWrapper(queryWrapper,queryForm);
        return valueMapper.selectList(queryWrapper);
    }

    @Override
    public List<ValueTypeVo> getValueTypeByPraIdToList(Long parId) {
        AssertUtils.asserts(Objects.isNull(parId), ProjectErrorType.ID_CANT_BE_NULL);
        QueryWrapper<Value> valueQueryWrapper = new QueryWrapper<>();
        valueQueryWrapper.eq(Value.PAR_ID, parId);
        List<Value> valueList = this.list(valueQueryWrapper);
        AssertUtils.asserts(CollectionUtils.isEmpty(valueList), ProjectErrorType.DATA_IS_NULL);
        List<ValueTypeVo> valueTypeVoList = new BeanUtils<ValueTypeVo>().copyObjs(valueList, ValueTypeVo.class);
        return valueTypeVoList;
    }

    @Override
    public List<ValueTypeVo> getValueTypeList(String valueDesc) {
        AssertUtils.asserts(Objects.isNull(valueDesc), ProjectErrorType.ID_CANT_BE_NULL);
        QueryWrapper<Value> valueQueryWrapper = new QueryWrapper<>();
        valueQueryWrapper.eq(Value.VALUE_DES, valueDesc);
        List<Value> valueList = this.list(valueQueryWrapper);
        AssertUtils.asserts(CollectionUtils.isEmpty(valueList), ProjectErrorType.SQL_EXCEPTION);
        List<ValueTypeVo> typeVoList = new BeanUtils<ValueTypeVo>().copyObjs(valueList, ValueTypeVo.class);
        return typeVoList;
    }

    @Override
    public List<ValueTypeVo> getValueTypeListByDes(String valueDesc) {
        AssertUtils.asserts(Objects.isNull(valueDesc), ProjectErrorType.ID_CANT_BE_NULL);
        QueryWrapper<Value> valueQueryWrapper = new QueryWrapper<>();
        valueQueryWrapper.inSql(Value.VALUE_DES, valueDesc);
        valueQueryWrapper.orderByAsc(Value.ID);
        List<Value> valueList = this.list(valueQueryWrapper);
        AssertUtils.asserts(CollectionUtils.isEmpty(valueList), ProjectErrorType.SQL_EXCEPTION);
        List<ValueTypeVo> typeVoList = new BeanUtils<ValueTypeVo>().copyObjs(valueList, ValueTypeVo.class);
        return typeVoList;
    }

    @Override
    public List<Value> getValueSubTypeList(String code) {
        QueryWrapper<Value> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(Value.VALUE_DES, "pro_cat");
        QueryWrapper.eq(Value.PAR_ID,code);
        List<Value> values = valueMapper.selectList(QueryWrapper);
        return values;
    }

    @Override
    public JSONArray getValueTypeDataJsonByDes(String valueDesc) {
        AssertUtils.asserts(Objects.isNull(valueDesc), ProjectErrorType.ID_CANT_BE_NULL);
        JSONArray parentArray = new JSONArray();
        String[] typeStrs = valueDesc.split(",");
        for (String typeStr : typeStrs) {
            QueryWrapper<Value> valueQueryWrapper = new QueryWrapper<>();
            valueQueryWrapper.eq(Value.VALUE_DES, typeStr);
            List<Value> valueList = this.list(valueQueryWrapper);
            if (CollectionUtils.isNotEmpty(valueList)) {
                JSONObject parentObject = new JSONObject();
                JSONArray childrenArray = new JSONArray();
                for (Value value : valueList) {
                    if (value.getValueDes().equals(typeStr)) {
                        JSONObject childObject = new JSONObject();
                        childObject.put("valueCode", value.getValueCode());
                        childObject.put("valueName", value.getValueName());
                        childrenArray.add(childObject);
                    }
                }
                parentObject.put(typeStr, childrenArray);
                parentArray.add(parentObject);
            }
        }
        return parentArray;
    }

    @Override
    public String getPayCatagoryJsonDatas() {
        QueryWrapper<Value> valueQueryWrapper = new QueryWrapper<>();
        valueQueryWrapper.eq(Value.VALUE_DES, "pay_subject");
        List<Value> parentValueList = this.list(valueQueryWrapper);

        QueryWrapper<Value> childrenValueQueryWrapper = new QueryWrapper<>();
        childrenValueQueryWrapper.eq(Value.VALUE_DES, "pay_catagory");
        List<Value> childrenValueList = this.list(childrenValueQueryWrapper);

        AssertUtils.asserts(CollectionUtils.isEmpty(parentValueList), ProjectErrorType.SQL_EXCEPTION);
        AssertUtils.asserts(CollectionUtils.isEmpty(childrenValueList), ProjectErrorType.SQL_EXCEPTION);

        JSONArray parentArray = new JSONArray();

        for (Value parent : parentValueList) {
            JSONObject parentJson = new JSONObject();
            JSONArray childrenArray = new JSONArray();
            for (Value children : childrenValueList) {
                if (String.valueOf(children.getParId()).equals(parent.getValueCode())) {
                    JSONObject childrenJson = new JSONObject();
                    childrenJson.put("expenseProjectCode", children.getValueCode());
                    childrenJson.put("expenseProject", children.getValueName());
                    childrenArray.add(childrenJson);
                }
            }
            parentJson.put("expenseProjectCode", parent.getValueCode());
            parentJson.put("expenseProject", parent.getValueName());
            parentJson.put("children", childrenArray);

            parentArray.add(parentJson);
        }
        return parentArray.toJSONString();
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Value> getQueryWrapper(QueryWrapper<Value> queryWrapper,ValueQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getValueId())){
            queryWrapper.eq(Value.VALUE_ID,queryForm.getValueId());
        }
        if(StringUtils.isNotBlank(queryForm.getValueCode())){
            queryWrapper.eq(Value.VALUE_CODE,queryForm.getValueCode());
        }
        if(StringUtils.isNotBlank(queryForm.getValueName())){
            queryWrapper.eq(Value.VALUE_NAME,queryForm.getValueName());
        }
        if(StringUtils.isNotBlank(queryForm.getValueStatus())){
            queryWrapper.eq(Value.VALUE_STATUS,queryForm.getValueStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getSuccessTime())){
            queryWrapper.eq(Value.SUCCESS_TIME,queryForm.getSuccessTime());
        }
        if(StringUtils.isNotBlank(queryForm.getParId())){
            queryWrapper.eq(Value.PAR_ID,queryForm.getParId());
        }
        if(StringUtils.isNotBlank(queryForm.getParName())){
            queryWrapper.eq(Value.PAR_NAME,queryForm.getParName());
        }
        if(StringUtils.isNotBlank(queryForm.getFailureTime())){
            queryWrapper.eq(Value.FAILURE_TIME,queryForm.getFailureTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Value.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Value.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Value.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Value.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Value.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Value.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Value.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Value.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Value.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Value.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Value.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

