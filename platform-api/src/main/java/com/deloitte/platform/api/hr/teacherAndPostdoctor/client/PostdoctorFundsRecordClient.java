package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorFundsRecordQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorFundsRecordForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.PostdoctorFundsRecordVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  PostdoctorFundsRecord控制器接口
 * @Modified :
 */
public interface PostdoctorFundsRecordClient {

    public static final String path="/hr/postdoctor-funds-record";


    /**
     *  POST---新增
     * @param postdoctorFundsRecordForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PostdoctorFundsRecordForm postdoctorFundsRecordForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, postdoctorFundsRecordForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PostdoctorFundsRecordForm postdoctorFundsRecordForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PostdoctorFundsRecordVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   postdoctorFundsRecordQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PostdoctorFundsRecordVo>> list(@Valid @RequestBody PostdoctorFundsRecordQueryForm postdoctorFundsRecordQueryForm);


    /**
     *  POST----复杂查询
     * @param  postdoctorFundsRecordQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PostdoctorFundsRecordVo>> search(@Valid @RequestBody PostdoctorFundsRecordQueryForm postdoctorFundsRecordQueryForm);
}
