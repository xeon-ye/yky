package com.deloitte.services.srpmp.outline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineAwardQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineAwardQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineAwardReportVo;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineAward;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题录 02奖励信息表 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
public interface SrpmsOutlineAwardMapper extends BaseMapper<SrpmsOutlineAward> {
    /**
     * 根据题录基础ID查询数据
     *
     * @param baseId
     * @return
     */
    @Select("select * "
            + "from　SRPMS_OUTLINE_AWARD  " +
            "  WHERE BASE_ID = #{baseId} ")
    List<Map> selectListByBaseId(long baseId);

    /**
     * 查询题录-奖励数据dao层接口
     *
     * @param queryForm
     * @return
     */
    List<SrpmsOutlineAwardQueryParam> listQuery(SrpmsOutlineAwardQueryForm queryForm);

    /**
     * 根据条件查询题录-奖励
     *
     * @param map
     * @return
     */
    int getQueryCount(Map<String, Object> map);

    List<SrpmsOutlineAwardReportVo> getReportCount(Map<String, Object> map);
}
