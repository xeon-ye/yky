package com.deloitte.services.srpmp.relust.mapper;

import com.deloitte.services.srpmp.relust.entity.SrpmsResultPatent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 成果管理-专利信息表 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
public interface SrpmsResultPatentMapper extends BaseMapper<SrpmsResultPatent> {

    int getQueryCount(Map<String, Object> map);
}
