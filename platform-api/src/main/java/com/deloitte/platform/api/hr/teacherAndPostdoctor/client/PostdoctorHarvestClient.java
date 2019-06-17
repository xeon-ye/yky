package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorHarvestQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorHarvestForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorHarvestVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  PostdoctorHarvest控制器接口
 * @Modified :
 */
public interface PostdoctorHarvestClient {

    public static final String path="/hr/postdoctor-harvest";


    /**
     *  POST---新增
     * @param postdoctorHarvestForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PostdoctorHarvestForm postdoctorHarvestForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, postdoctorHarvestForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PostdoctorHarvestForm postdoctorHarvestForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PostdoctorHarvestVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   postdoctorHarvestQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PostdoctorHarvestVo>> list(@Valid @RequestBody PostdoctorHarvestQueryForm postdoctorHarvestQueryForm);


    /**
     *  POST----复杂查询
     * @param  postdoctorHarvestQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PostdoctorHarvestVo>> search(@Valid @RequestBody PostdoctorHarvestQueryForm postdoctorHarvestQueryForm);
}
