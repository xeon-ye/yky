package com.deloitte.services.srpmp.relust.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.srpmp.relust.entity.SrpmsResultAcaExc;

import java.util.Map;

/**
 * <p>
 * 成果管理-学术交流 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
public interface SrpmsResultAcaExcMapper extends BaseMapper<SrpmsResultAcaExc> {

    int getQueryCount(Map<String, Object> map);
}
