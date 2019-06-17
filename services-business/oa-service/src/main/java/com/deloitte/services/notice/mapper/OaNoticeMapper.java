package com.deloitte.services.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.services.notice.entity.OaNotice;
import com.deloitte.services.notice.entity.OaNoticeOther;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 通知公告表 Mapper 接口
 * </p>
 *
 * @author fuqiao
 * @since 2019-04-15
 */
public interface OaNoticeMapper extends BaseMapper<OaNotice> {

    List<OaNotice> getHomeList(@Param(value = "num")int num,
                               @Param(value = "noticeTypeCode")String noticeTypeCode,
                               @Param(value = "deptCode")String deptCode);

    String getOrderNum(@Param(value = "head")String head);

    IPage<OaNotice> getOrgPerList(IPage page, @Param(value = "orgCode") String orgCode,
                                       @Param(value = "deptCode") String deptCode,
                                       @Param(value = "curOrgCode") String curOrgCode,
                                       @Param(value = "userId") String userId,
                                       @Param(value = "typeCode") String typeCode,
                                       @Param(value = "sortCode") String sortCode);

}
