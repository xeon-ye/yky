package com.deloitte.services.rulesystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.services.notice.entity.OaNoticeOther;
import com.deloitte.services.rulesystem.entity.OaRuleSystem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 规则制度 Mapper 接口
 * </p>
 *
 * @author fuqiao
 * @since 2019-04-15
 */
public interface OaRuleSystemMapper extends BaseMapper<OaRuleSystem> {

    List<OaRuleSystem> getHomeList(@Param(value = "num")int num, @Param(value = "deptCode") String deptCode);

    IPage<OaRuleSystem> getOrgPerList(IPage page, @Param(value = "orgCode") String orgCode,
                                       @Param(value = "deptCode") String deptCode,
                                       @Param(value = "curOrgCode") String curOrgCode,
                                       @Param(value = "userId") String userId,
                                       @Param(value = "sortCode") String sortCode);

}
