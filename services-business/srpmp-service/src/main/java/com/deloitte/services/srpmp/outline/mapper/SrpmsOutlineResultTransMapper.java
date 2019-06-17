package com.deloitte.services.srpmp.outline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineResultTransQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineResultTransQueryParam;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineResultTrans;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题录 03成果转化信息表 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
public interface SrpmsOutlineResultTransMapper extends BaseMapper<SrpmsOutlineResultTrans> {

    /**
     * 根据题录基础ID查询数据
     *
     * @param baseId
     * @return
     */
    @Select("select * "
            + "from　SRPMS_OUTLINE_RESULT_TRANS  " +
            "  WHERE BASE_ID = #{baseId} ")
    List<Map> selectListByBaseId(long baseId);

    /**
     * 查询题录-成果转化数据dao层接口
     *
     * @param map
     * @return
     */
    List<SrpmsOutlineResultTransQueryParam> listQuery(SrpmsOutlineResultTransQueryForm queryForm);

    /**
     * 根据条件查询题录-成果转化
     *
     * @param map
     * @return
     */
    int getQueryCount(Map<String, Object> map);
}
