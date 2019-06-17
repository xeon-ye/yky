package com.deloitte.platform.api.srpmp.project.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-29
 * @Description :  SrpmsProjectExtClient
 * @Modified :
 */
public interface SrpmsProjectExtClient {

    public static final String path="/srpmp/project/feign";

    /**
     * 分页查询，内部系统使用
     * @param  srpmsProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/project/page/searchNoUser")
    Result<GdcPage<SrpmsProjectVo>> searchNoUser(@RequestBody SrpmsProjectQueryForm srpmsProjectQueryForm);

    /**
     * 查询项目信息，内部系统使用
     * @param  projectIds（逗号分隔）
     * @return
     */
    @PostMapping(value = path+"/project/list/listByProjectIds")
    Result<List<SrpmsProjectVo>> listByProjectIds(@RequestBody String projectIds);

}
