package com.deloitte.services.srpmp.outline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlinePaperQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlinePaperQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePaperReportVo;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlinePaper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题录 08论文 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
public interface SrpmsOutlinePaperMapper extends BaseMapper<SrpmsOutlinePaper> {

    /**
     * 根据题录基础ID查询数据
     *
     * @param baseId
     * @return
     */
    @Select("select * "
            + "from　SRPMS_OUTLINE_PAPER  " +
            "  WHERE BASE_ID = #{baseId} ")
    List<Map> selectListByBaseId(long baseId);

    /**
     * 查询题录-论文数据dao层接口
     *
     * @param queryForm
     * @return
     */
    List<SrpmsOutlinePaperQueryParam> listQuery(SrpmsOutlinePaperQueryForm queryForm);

    /**
     * 根据条件查询题录-论文
     *
     * @param map
     * @return
     */
    int getQueryCount(Map<String, Object> map);

    List<SrpmsOutlinePaperReportVo> getReportCount(Map<String, Object> map);

}
