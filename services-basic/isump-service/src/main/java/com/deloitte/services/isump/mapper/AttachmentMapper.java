package com.deloitte.services.isump.mapper;

import com.deloitte.services.isump.entity.Attachment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jianglong
 * @since 2019-04-04
 */
public interface AttachmentMapper extends BaseMapper<Attachment> {

    int delByMasterId(@Param("id") String id);

}
