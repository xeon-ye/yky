package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorApplyQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorHarvestExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.PostdoctorHarvestQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  PostdoctorApply控制器接口
 * @Modified :
 */
public interface PostdoctorApplyClient {

    public static final String path="/hr/postdoctor-apply";


    /**
     *  POST---新增
     * @param postdoctorApplyForm
     * @return
     */
    @PostMapping(value = path+"/add")
    Result add(@Valid @ModelAttribute PostdoctorApplyForm postdoctorApplyForm);


    /**
     * 获取登录用户信息
     *
     * @return
     */
    @GetMapping(value = path+"/getUserInfo")
    Result<PostdoctorApplyBaseVo> getUserInfo();

    /**
     *  POST----复杂查询
     *
     * @return
     */
    @PostMapping(value = path+"/getApplyList")
    Result<IPage<PostdoctorApplyVo>> getApplyList(@Valid @RequestBody PostdoctorApplyQueryForm postdoctorApplyQueryForm);

    /**
     *  博士后在站成果自助申请
     *
     * @param
     * @return
     */
    @PostMapping(value = path+"/addHarvestApply")
    Result  addHarvestApply(PostdoctorHForm postdoctorHForm);

    /**
     *  根据ID获取在站成果自助申请
     *
     * @param id
     * @return
     */
    @GetMapping(value = path+"/getApplyById/{id}")
    Result getApplyById(@PathVariable("id") long id);

    /**
     *  分页查询在站成果管理审核列表
     *
     * @param postdoctorHarvestQueryForm 条件查询
     * @return
     */
    @PostMapping(value = path+"/getHarvestCheckList")
    Result<IPage<PostdoctorApplyCheckVo>> getHarvestCheckList(@Valid @RequestBody PostdoctorHarvestQueryForm postdoctorHarvestQueryForm);

    /**
     * 分页导出在站成果管理审核列表
     *
     * @param postdoctorHarvestExportForm 条件导出
     */
    @GetMapping(value = path+"/exportHarvestCheckList")
    void exportHarvestCheckList(@ModelAttribute PostdoctorHarvestExportForm postdoctorHarvestExportForm, HttpServletRequest request, HttpServletResponse response);

    /**
     *  博士后在站成果批量审核
     * @param aidList     在站成果申请信息ID数组
     * @param checkType  审核类型 (1合作导师审核通过，2合作导师审核不通过，3所院审核通过，4所院审核不通过，5院校审核通过，6院校审核不通过)
     * @return
     */
    @PostMapping(value = path+"/checkApply")
    Result checkApply(String aidList, @RequestParam Integer checkType);

    /**
     *  在站成果word导出
     *
     * @param appId  需要导出的申请ID
     */
    @GetMapping(value = path+"/exportHarvestInfo")
    void exportHarvestInfo(@RequestParam String appId, HttpServletRequest request, HttpServletResponse response);

    /**
     *  博士后申请信息流程审核
     *
     * @param postdoctorCheckForm
     * @return
     */
    @PostMapping(value = path+"/checkApplyById")
    Result checkApplyById(@Valid @RequestBody PostdoctorCheckForm postdoctorCheckForm);
}
