package com.deloitte.services.srpmp.outline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlinePatentQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlinePatentQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePatentReportVo;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlinePatent;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题录 05专利信息表 Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
public interface SrpmsOutlinePatentMapper extends BaseMapper<SrpmsOutlinePatent> {

    /**
     * 根据题录基础ID查询数据
     *
     * @param baseId
     * @return
     */
    @Select("select * "
            + "from　SRPMS_OUTLINE_PATENT  " +
            "  WHERE BASE_ID = #{baseId} ")
    List<Map> selectListByBaseId(long baseId);

    /**
     * 查询题录-专利数据dao层接口
     *
     * @param queryForm
     * @return
     */
    List<SrpmsOutlinePatentQueryParam> listQuery(SrpmsOutlinePatentQueryForm queryForm);

    /**
     * 根据条件查询题录-专利
     *
     * @param map
     * @return
     */
    int getQueryCount(Map<String, Object> map);

    List<SrpmsOutlinePatentReportVo> getReportCount(Map<String, Object> map);
}
