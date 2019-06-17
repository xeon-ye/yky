package com.deloitte.platform.api.srpmp.project.task;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.task.param.SrpmsProjectTaskInnUnitQueryForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskInnUnitForm;
import com.deloitte.platform.api.srpmp.project.task.vo.SrpmsProjectTaskInnUnitVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-05-22
 * @Description :  SrpmsProjectTaskInnUnit控制器接口
 * @Modified :
 */
public interface SrpmsProjectTaskInnUnitClient {

    public static final String path="/srpmp/project/task/inn/unit";


    /**
     *  POST---新增
     * @param srpmsProjectTaskInnUnitForm
     * @return
     */
    @PostMapping(value = path + "/save")
    Result save(@Valid @ModelAttribute SrpmsProjectTaskInnUnitForm srpmsProjectTaskInnUnitForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SrpmsProjectTaskInnUnitVo> get(@PathVariable(value = "id") long id);

    /**
     * 提交创新单元任务书
     * @param innUnitForm
     * @param userVo
     * @param deptVo
     * @return
     */
    @PostMapping(value = path + "/submit")
    Result submit(@Valid @ModelAttribute SrpmsProjectTaskInnUnitForm innUnitForm, UserVo userVo, DeptVo deptVo);

}
