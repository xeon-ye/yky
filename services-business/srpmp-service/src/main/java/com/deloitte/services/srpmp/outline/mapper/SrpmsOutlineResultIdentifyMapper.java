package com.deloitte.services.srpmp.outline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineResultIdentifyQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineResultIdentifyQueryParam;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineResultIdentify;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题录 04成果鉴定信息表 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
public interface SrpmsOutlineResultIdentifyMapper extends BaseMapper<SrpmsOutlineResultIdentify> {

    /**
     * 根据题录基础ID查询数据
     *
     * @param baseId
     * @return
     */
    @Select("select * "
            + "from　SRPMS_OUTLINE_RESULT_IDENTIFY  " +
            "  WHERE BASE_ID = #{baseId} ")
    List<Map> selectListByBaseId(long baseId);

    /**
     * 查询题录-成果鉴定数据dao层接口
     *
     * @param queryForm
     * @return
     */
    List<SrpmsOutlineResultIdentifyQueryParam> listQuery(SrpmsOutlineResultIdentifyQueryForm queryForm);

    /**
     * 根据条件查询题录-成果鉴定
     *
     * @param map
     * @return
     */
    int getQueryCount(Map<String, Object> map);
}
