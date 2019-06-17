package com.deloitte.services.srpmp.outline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineSatBookQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineSatBookQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineSatBookReportVo;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineSatBook;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 科技著作接口
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
public interface SrpmsOutlineSatBookMapper extends BaseMapper<SrpmsOutlineSatBook> {

    /**
     * 根据题录基础ID查询数据
     *
     * @param baseId
     * @return
     */
    @Select("select * "
            + "from　SRPMS_OUTLINE_SAT_BOOK  " +
            "  WHERE BASE_ID = #{baseId} ")
    List<Map> selectListByBaseId(long baseId);

    /**
     * 查询题录-科技著作数据dao层接口
     *
     * @param queryForm
     * @return
     */
    List<SrpmsOutlineSatBookQueryParam> listQuery(SrpmsOutlineSatBookQueryForm queryForm);

    /**
     * 根据条件查询题录-科技著作
     *
     * @param map
     * @return
     */
    int getQueryCount(Map<String, Object> map);

    List<SrpmsOutlineSatBookReportVo> getReportCount(Map<String, Object> map);
}
