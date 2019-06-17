package com.deloitte.platform.api.hr.employee.client;

import com.deloitte.platform.api.hr.employee.param.TeacherempQuerysQueryForm;
import com.deloitte.platform.api.hr.employee.vo.TeacherempQuerysForm;
import com.deloitte.platform.api.hr.employee.vo.TeacherempQuerysVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description :  TeacherempQuerys控制器接口
 * @Modified :
 */
public interface TeacherempQuerysClient {

    public static final String path="/hr/teacheremp-querys";


    /**
     *  POST---新增
     * @param teacherempQuerysForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute TeacherempQuerysForm teacherempQuerysForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, teacherempQuerysForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody TeacherempQuerysForm teacherempQuerysForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<TeacherempQuerysVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   teacherempQuerysQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<TeacherempQuerysVo>> list(@Valid @RequestBody TeacherempQuerysQueryForm teacherempQuerysQueryForm);


    /**
     *  POST----复杂查询
     * @param  teacherempQuerysQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<TeacherempQuerysVo>> search(@Valid @RequestBody TeacherempQuerysQueryForm teacherempQuerysQueryForm);
}
