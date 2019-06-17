package com.deloitte.platform.api.srpmp.project.apply;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.param.SrpmsProjectApplyInnUnitQueryForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplyInnUnitVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnBcooSaveVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-05-22
 * @Description :  SrpmsProjectApplyInnUnit控制器接口
 * @Modified :
 */
public interface SrpmsProjectApplyInnUnitClient {

    public static final String path="/srpmp/project/apply/innovate/unit";


    /**
     *  POST---新增
     * @param srpmsProjectApplyInnUnitForm
     * @return
     */
    @PostMapping(value = path+"/save")
    Result save(@Valid @ModelAttribute SrpmsProjectApplyInnUnitForm srpmsProjectApplyInnUnitForm, DeptVo deptVo);

    /**
     * 提交
     * @param srpmsProjectApplyInnUnitForm
     * @param userVo
     * @param deptVo
     * @return
     */
    @PostMapping(value = path + "/submit")
    Result submit(@Valid @ModelAttribute SrpmsProjectApplyInnUnitForm srpmsProjectApplyInnUnitForm, UserVo userVo, DeptVo deptVo);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SrpmsProjectApplyInnUnitVo> get(@PathVariable(value = "id") long id);

}
