package com.deloitte.services.srpmp.relust.mapper;

import com.deloitte.services.srpmp.relust.entity.SrpmsResultSatBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 成果管理-科技著作 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
public interface SrpmsResultSatBookMapper extends BaseMapper<SrpmsResultSatBook> {

    int getQueryCount(Map<String, Object> map);
}
