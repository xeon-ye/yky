package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.StandardTemplateQueryForm;
import com.deloitte.services.contract.entity.StandardTemplate;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : StandardTemplate服务类接口
 * @Modified :
 */
public interface IStandardTemplateService extends IService<StandardTemplate> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<StandardTemplate>
     */
    IPage<StandardTemplate> selectPage(StandardTemplateQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<StandardTemplate>
     */
    List<StandardTemplate> selectList(StandardTemplateQueryForm queryForm);

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<StandardTemplate> getStandardTemplateList(Map<String, String> map);

    /**
     * 根据标准文本属性查询标准文本信息
     * @param map
     * @return
     */
    List<StandardTemplate> getStandardTemplateAllList(Map<String, String> map);

    /**
     * 获取最大数量信息
     * @param map
     * @return
     */
    int getStandardTemplateMxaSize(Map<String, String> map);
}
