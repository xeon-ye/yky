package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainGainQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.TeacherTrainGainForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.TeacherTrainGainVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description :  TeacherTrainGain控制器接口
 * @Modified :
 */
public interface TeacherTrainGainClient {

    public static final String path="/hr/teacher-train-gain";


    /**
     *  POST---新增
     * @param teacherTrainGainForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute TeacherTrainGainForm teacherTrainGainForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, teacherTrainGainForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody TeacherTrainGainForm teacherTrainGainForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<TeacherTrainGainVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   teacherTrainGainQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<TeacherTrainGainVo>> list(@Valid @RequestBody TeacherTrainGainQueryForm teacherTrainGainQueryForm);


    /**
     *  POST----复杂查询
     * @param  teacherTrainGainQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<TeacherTrainGainVo>> search(@Valid @RequestBody TeacherTrainGainQueryForm teacherTrainGainQueryForm);
}
