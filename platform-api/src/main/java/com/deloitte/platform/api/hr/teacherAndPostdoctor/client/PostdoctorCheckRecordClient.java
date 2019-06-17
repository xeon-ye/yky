package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorCheckRecordQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorCheckRecordForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorCheckRecordVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  PostdoctorCheckRecord控制器接口
 * @Modified :
 */
public interface PostdoctorCheckRecordClient {

    public static final String path="/hr/postdoctor-check-record";


    /**
     *  POST---新增
     * @param postdoctorCheckRecordForm
     * @return
     */
    @PostMapping(value = path+"/add")
    Result add(@Valid @ModelAttribute PostdoctorCheckRecordForm postdoctorCheckRecordForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, postdoctorCheckRecordForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PostdoctorCheckRecordForm postdoctorCheckRecordForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PostdoctorCheckRecordVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----复杂查询
     * @param  postdoctorQueryForm
     * @return
     */
    @PostMapping(value = path+"/getCheckRecordList")
    Result<IPage<PostdoctorCheckRecordVo>> getCheckRecordList(@Valid @RequestBody PostdoctorQueryForm postdoctorQueryForm);

    /**
     * 根据条件查询导出考勤记录
     *
     * @param postdoctorExportForm 搜索条件
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportCheckRecordList")
    void exportCheckRecordList(@ModelAttribute PostdoctorExportForm postdoctorExportForm, HttpServletRequest request, HttpServletResponse response);
}
