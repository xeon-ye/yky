package com.deloitte.services.srpmp.relust.mapper;

import com.deloitte.services.srpmp.relust.entity.SrpmsResultPaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 成果管理-论文 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
public interface SrpmsResultPaperMapper extends BaseMapper<SrpmsResultPaper> {

    int getQueryCount(Map<String, Object> map);
}
