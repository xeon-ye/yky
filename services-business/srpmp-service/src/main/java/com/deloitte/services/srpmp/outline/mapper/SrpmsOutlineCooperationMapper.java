package com.deloitte.services.srpmp.outline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineCooperationQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineCooperationQueryParam;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineCooperation;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题录 07国合项目 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
public interface SrpmsOutlineCooperationMapper extends BaseMapper<SrpmsOutlineCooperation> {

    /**
     * 根据题录基础ID查询数据
     *
     * @param baseId
     * @return
     */
    @Select("select * "
            + "from　SRPMS_OUTLINE_COOPERATION  " +
            "  WHERE BASE_ID = #{baseId} ")
    List<Map> selectListByBaseId(long baseId);

    /**
     * 查询题录-国合项目数据dao层接口
     *
     * @param queryForm
     * @return
     */
    List<SrpmsOutlineCooperationQueryParam> listQuery(SrpmsOutlineCooperationQueryForm queryForm);

    /**
     * 根据条件查询题录-国合项目
     *
     * @param map
     * @return
     */
    int getQueryCount(Map<String, Object> map);
}
