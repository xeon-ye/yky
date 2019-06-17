package com.deloitte.services.project.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ValueQueryForm;
import com.deloitte.platform.api.project.vo.ValueTypeVo;
import com.deloitte.platform.api.project.vo.ValueVo;
import com.deloitte.services.project.entity.Value;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : Value服务类接口
 * @Modified :
 */
public interface IValueService extends IService<Value> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Value>
     */
    IPage<Value> selectPage(ValueQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Value>
     */
    List<Value> selectList(ValueQueryForm queryForm);

    /**
     * 获取value type
     * @param parId
     * @return
     */
    List<ValueTypeVo> getValueTypeByPraIdToList(Long parId);

    /**
     * 获取valueType
     * @param valueDesc
     * @return
     */
    List<ValueTypeVo> getValueTypeList(String valueDesc);

    /**
     * 获取valueType
     * @param valueDesc
     * @return
     */
    List<ValueTypeVo> getValueTypeListByDes(String valueDesc);

    List<Value> getValueSubTypeList(String code);



    /**
     * 获取支出大类JSON DATA PACKAGES
     * @return
     */
    String getPayCatagoryJsonDatas();

    /**
     * 获取多个参数对应的数据 并封装JSON键值对形式参数
     * @param valueDesc
     * @return
     */
    JSONArray getValueTypeDataJsonByDes(String valueDesc);
}
