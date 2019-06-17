package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorApplyInfoQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorApplyInfoForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorApplyInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  PostdoctorApplyInfo控制器接口
 * @Modified :
 */
public interface PostdoctorApplyInfoClient {

    public static final String path="/hr/postdoctor-apply-info";


    /**
     *  POST---新增
     * @param postdoctorApplyInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PostdoctorApplyInfoForm postdoctorApplyInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, postdoctorApplyInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PostdoctorApplyInfoForm postdoctorApplyInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PostdoctorApplyInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   postdoctorApplyInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PostdoctorApplyInfoVo>> list(@Valid @RequestBody PostdoctorApplyInfoQueryForm postdoctorApplyInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  postdoctorApplyInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PostdoctorApplyInfoVo>> search(@Valid @RequestBody PostdoctorApplyInfoQueryForm postdoctorApplyInfoQueryForm);
}
