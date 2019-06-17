package com.deloitte.services.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.services.notice.entity.OaNoticeOther;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 校历 Mapper 接口
 * </p>
 *
 * @author jianghaixun
 * @since 2019-04-19
 */
public interface OaNoticeOtherMapper extends BaseMapper<OaNoticeOther> {

    List<OaNoticeOther> getHomeList(@Param(value = "num") int num,
                                    @Param(value = "typeCode") String typeCode,
                                    @Param(value = "deptCode") String deptCode);

    IPage<OaNoticeOther> getOrgPerList(IPage page, @Param(value = "orgCode") String orgCode,
                                       @Param(value = "deptCode") String deptCode,
                                       @Param(value = "curOrgCode") String curOrgCode,
                                      @Param(value = "userId") String userId,
                                       @Param(value = "typeCode") String typeCode);

}
