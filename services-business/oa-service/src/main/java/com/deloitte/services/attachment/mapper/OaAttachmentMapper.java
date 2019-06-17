package com.deloitte.services.attachment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.attachment.entity.OaAttachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 附件表 Mapper 接口
 * </p>
 *
 * @author jianghaixun
 * @since 2019-04-15
 */
public interface OaAttachmentMapper extends BaseMapper<OaAttachment> {

    List<OaAttachment> getHomeList(@Param(value = "num")int num, @Param(value = "busicessName")String busicessName);

    boolean deleteByBusinessId(@Param(value = "businessId")String businessId);

}
