package com.deloitte.services.srpmp.outline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineMedicalQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineMedicalQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineMedicalReportVo;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineMedical;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题录 06新药器械 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
public interface SrpmsOutlineMedicalMapper extends BaseMapper<SrpmsOutlineMedical> {

    /**
     * 根据题录基础ID查询数据
     *
     * @param baseId
     * @return
     */
    @Select("select * "
            + "from　SRPMS_OUTLINE_MEDICAL  " +
            "  WHERE BASE_ID = #{baseId} ")
    List<Map> selectListByBaseId(long baseId);

    /**
     * 查询题录-新药器械数据dao层接口
     *
     * @param queryForm
     * @return
     */
    List<SrpmsOutlineMedicalQueryParam> listQuery(SrpmsOutlineMedicalQueryForm queryForm);

    /**
     * 根据条件查询题录-新药器械
     *
     * @param map
     * @return
     */
    int getQueryCount(Map<String, Object> map);

    List<SrpmsOutlineMedicalReportVo> getReportCount(Map<String, Object> map);

}
