package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.StandardTemplateQueryForm;
import com.deloitte.platform.api.contract.param.TemplatePersonMapQueryForm;
import com.deloitte.platform.api.contract.vo.TemplatePersonMapForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.entity.StandardTemplate;
import com.deloitte.services.contract.entity.TemplatePersonMap;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : TemplatePersonMap服务类接口
 * @Modified :
 */
public interface ITemplatePersonMapService extends IService<TemplatePersonMap> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<TemplatePersonMap>
     */
    IPage<TemplatePersonMap> selectPage(TemplatePersonMapQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<TemplatePersonMap>
     */
    List<TemplatePersonMap> selectList(TemplatePersonMapQueryForm queryForm);

    /**
     * 分页查询
     * @param standardTemplateQueryForm
     * @return
     */
    List<StandardTemplate> getTemplatePersonList(StandardTemplateQueryForm standardTemplateQueryForm);

    /**
     * 获取条件查询的最大数量
     * @param standardTemplateQueryForm
     * @return
     */
    BigDecimal getTemplatePersonMxaSize(StandardTemplateQueryForm standardTemplateQueryForm);

    /**
     * 添加我的常用标准文本
     * @param templatePersonMap
     * @return
     */
    Result addMyStandard(TemplatePersonMapForm templatePersonMap);
}
