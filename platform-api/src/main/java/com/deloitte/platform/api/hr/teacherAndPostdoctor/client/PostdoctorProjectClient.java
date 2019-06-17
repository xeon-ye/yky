package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorProjectExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorProjectQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorProjectForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.netflix.ribbon.proxy.annotation.Http;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.List;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  PostdoctorProject控制器接口
 * @Modified :
 */
public interface PostdoctorProjectClient {

    public static final String path="/hr/postdoctor-project";


    /**
     *  POST---新增
     * @param postdoctorProjectForm
     * @return
     */
    @PostMapping(value = path+"/add")
    Result add(@Valid @ModelAttribute PostdoctorProjectForm postdoctorProjectForm);

    /**
    *  Delete---删除
    * @param  ids
    * @return
    */
    @PostMapping(value = path+"/delete")
    Result delete(@RequestParam String[] ids);

    /**
     *  Patch----部分更新
     * @param  id, postdoctorProjectForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PostdoctorProjectForm postdoctorProjectForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PostdoctorProjectVo> get(@PathVariable(value="id") long id);


    /**
     *   根据条件查询导出博士后资助项目信息列表
     *
     * @param   postdoctorProjectExportForm
     * @return
     */
    @GetMapping(value = path+"/exportProjectList")
    void exportProjectList(@ModelAttribute PostdoctorProjectExportForm postdoctorProjectExportForm, HttpServletRequest request, HttpServletResponse response);


    /**
     *  分页查询博士后资助项目信息列表
     *
     * @param  postdoctorProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/getProjectList")
    Result<IPage<PostdoctorProjectVo>> getProjectList(@Valid @RequestBody PostdoctorProjectQueryForm postdoctorProjectQueryForm,@RequestParam Integer type);

    /**
     * 条件搜索下拉框选项初始化
     * @return
     */
    @GetMapping(value = path+"/getInitTermSearch")
    Result<HashMap<String,Object>> getInitTermSearch();
}
