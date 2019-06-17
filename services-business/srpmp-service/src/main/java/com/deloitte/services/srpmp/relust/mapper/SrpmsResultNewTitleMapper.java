package com.deloitte.services.srpmp.relust.mapper;

import com.deloitte.services.srpmp.relust.entity.SrpmsResultNewTitle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 横纵向-题录新获项目 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
public interface SrpmsResultNewTitleMapper extends BaseMapper<SrpmsResultNewTitle> {

    int getQueryCount(Map<String, Object> map);
}
