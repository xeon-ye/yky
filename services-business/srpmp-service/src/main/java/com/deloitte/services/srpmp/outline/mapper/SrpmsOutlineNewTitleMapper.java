package com.deloitte.services.srpmp.outline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineNewTitleQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineNewTitleQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineNewTitleOutlayProportionVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineNewTitleReportCountVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineNewTitleReportOutlayVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineNewTitleStateCountVo;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineNewTitle;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pengchao
 * @since 2019-02-14
 */
public interface SrpmsOutlineNewTitleMapper extends BaseMapper<SrpmsOutlineNewTitle> ,SrpmsOutlineBasicMapper {

    /**
     * 根据题录基础ID查询数据
     *
     * @param baseId
     * @return
     */
    @Select("select * "
            + "from　SRPMS_OUTLINE_NEW_TITLE  " +
            "  WHERE BASE_ID = #{baseId} ")
    List<Map> selectListByBaseId(long baseId);

    /**
     * 查询题录-新获项目数据dao层接口
     *
     * @param queryForm
     * @return
     */
    List<SrpmsOutlineNewTitleQueryParam> listQuery(SrpmsOutlineNewTitleQueryForm queryForm);

    /**
     * 根据条件查询题录-新获项目
     *
     * @param map
     * @return
     */
    int getQueryCount(Map<String, Object> map);

    /**
     * 新获项目导出报表数量统计
     *
     * @param map
     * @return
     */
    List<SrpmsOutlineNewTitleReportCountVo> getReportCount(Map<String, Object> map);

    /**
     * 新获项目导出报表总经费统计
     *
     * @param map
     * @return
     */
    List<SrpmsOutlineNewTitleReportOutlayVo> getReportTotalOutlay(Map<String, Object> map);

    /**
     * 新获项目导出报表到位经费统计
     *
     * @param map
     * @return
     */
    List<SrpmsOutlineNewTitleReportOutlayVo> getReportReceiveOutlay(Map<String, Object> map);

    /**
     * 科技项目状态统计
     *
     * @param map
     * @return
     */
    List<SrpmsOutlineNewTitleStateCountVo> getReportStateCount(Map<String, Object> map);

    /**
     * 各单位项目经费占比
     * @param map
     * @return
     */
    List<SrpmsOutlineNewTitleOutlayProportionVo> getReportProportionByOrg(Map<String, Object> map);

    /**
     * 各单位项目经费求和
     * @param map
     * @return
     */
    List<SrpmsOutlineNewTitleOutlayProportionVo> getReportProportionSumByOrg(Map<String, Object> map);

    /**
     * 各项目经费占比
     * @param map
     * @return
     */
    List<SrpmsOutlineNewTitleOutlayProportionVo> getReportProportionByProject(Map<String, Object> map);

}
