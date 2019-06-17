package com.deloitte.services.srpmp.outline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineAcaExcQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineAcaExcQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineAcaExcReportVo;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineAcaExc;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题录 09学术交流 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
public interface SrpmsOutlineAcaExcMapper extends BaseMapper<SrpmsOutlineAcaExc> {
    /**
     * 根据题录基础ID查询数据
     *
     * @param baseId
     * @return
     */
    @Select("select * "
            + "from　SRPMS_OUTLINE_ACA_EXC  " +
            "  WHERE BASE_ID = #{baseId} ")
    List<Map> selectListByBaseId(long baseId);

    /**
     * 查询题录-学术交流数据dao层接口
     *
     * @param queryForm
     * @return
     */
    List<SrpmsOutlineAcaExcQueryParam> listQuery(SrpmsOutlineAcaExcQueryForm queryForm);

    /**
     * 根据条件查询题录-学术交流
     *
     * @param map
     * @return
     */
    int getQueryCount(Map<String, Object> map);

    List<SrpmsOutlineAcaExcReportVo> getReportCount(Map<String, Object> map);
}
