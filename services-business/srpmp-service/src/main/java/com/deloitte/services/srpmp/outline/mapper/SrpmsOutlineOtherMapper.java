package com.deloitte.services.srpmp.outline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineOtherQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineOtherQueryParam;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineOther;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题录 11其他 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
public interface SrpmsOutlineOtherMapper extends BaseMapper<SrpmsOutlineOther> {

    /**
     * 根据题录基础ID查询数据
     *
     * @param baseId
     * @return
     */
    @Select("select * "
            + "from　SRPMS_OUTLINE_OTHER  " +
            "  WHERE BASE_ID = #{baseId} ")
    List<Map> selectListByBaseId(long baseId);

    /**
     * 查询题录-其他数据dao层接口
     *
     * @param map
     * @return
     */
    List<SrpmsOutlineOtherQueryParam> listQuery(SrpmsOutlineOtherQueryForm queryForm);

    /**
     * 根据条件查询题录-其他
     *
     * @param map
     * @return
     */
    int getQueryCount(Map<String, Object> map);
}
