package com.deloitte.services.srpmp.relust.mapper;

import com.deloitte.services.srpmp.relust.entity.SrpmsResultMedical;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 成果管理-新药器械 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
public interface SrpmsResultMedicalMapper extends BaseMapper<SrpmsResultMedical> {

    int getQueryCount(Map<String, Object> map);
}
