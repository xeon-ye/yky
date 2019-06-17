package com.deloitte.services.srpmp.project.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 项目表 Mapper 接口
 * </p>
 *
 * @author lixin
 * @since 2019-02-19
 */
public interface SrpmsProjectMapper extends BaseMapper<SrpmsProject> {

    /**
     * 根据待审核单位ID查询审核项目dao层接口
     *
     * @return
     */
    List<SrpmsProject> getApproveList();

    List<SrpmsProject> selectMyPage(IPage<SrpmsProject> page, @Param("queryForm") SrpmsProjectQueryForm srpmsProjectQueryForm);

    /**
     * 根据单位性质、项目名称、项目类型查询
     *
     * @param projectQueryForm
     * @return
     */
    List<SrpmsProject> queryByDept(SrpmsProjectQueryForm projectQueryForm);
}
