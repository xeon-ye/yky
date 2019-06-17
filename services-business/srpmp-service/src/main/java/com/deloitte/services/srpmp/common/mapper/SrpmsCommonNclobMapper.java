package com.deloitte.services.srpmp.common.mapper;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.srpmp.common.entity.SrpmsCommonNclob;

/**
 * <p>
 * 科研CLOB通用表 Mapper 接口
 * </p>
 *
 * @author pengchao
 * @since 2019-04-30
 */
public interface SrpmsCommonNclobMapper extends BaseMapper<SrpmsCommonNclob> {

	@Select("select ID " +
            "from　SRPMS_COMMON_NCLOB  " +
            " WHERE PROJECT_ID = #{projectId}  " +
            " AND TYPE = #{type} ")
	Long selectByProjectIdAndType(SrpmsCommonNclob vo);
}
