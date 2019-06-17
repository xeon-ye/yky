package com.deloitte.services.contract.mapper;

import com.deloitte.platform.api.contract.param.StandardTemplateQueryForm;
import com.deloitte.services.contract.entity.StandardTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 合同标准文本 合同标准文本 Mapper 接口
 * </p>
 *
 * @author yangyq
 * @since 2019-04-25
 */
public interface StandardTemplateMapper extends BaseMapper<StandardTemplate> {

    List<StandardTemplate> getStandardTemplateList(Map<String, String> map);

    List<StandardTemplate> getTemplatePersonList(StandardTemplateQueryForm standardTemplateQueryForm);

    List<StandardTemplate> getStandardTemplateaAllList(Map<String, String> map);

    List<Map<String, Object>> getStandardTemplateMxaSize(Map<String, String> map);

    BigDecimal getTemplatePersonMxaSize(StandardTemplateQueryForm standardTemplateQueryForm);
}
