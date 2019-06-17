package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpDeclareQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :  HrZpcpDeclare控制器接口
 * @Modified :
 */
public interface HrZpcpDeclareClient {

    public static final String path = "/hr/recruitment/hr-zpcp-declare";


    /**
     * POST---新增
     *
     * @param hrZpcpDeclareForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrZpcpDeclareForm hrZpcpDeclareForm);

    /**
     * Delete---删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = path + "/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     * Patch----部分更新
     *
     * @param id, hrZpcpDeclareForm
     * @return
     */
    @PatchMapping(value = path + "/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrZpcpDeclareForm hrZpcpDeclareForm);

    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<HrZpcpDeclareVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     *
     * @param hrZpcpDeclareQueryForm
     * @return
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<HrZpcpDeclareVo>> list(@Valid @RequestBody HrZpcpDeclareQueryForm hrZpcpDeclareQueryForm);


    /**
     * POST----复杂查询
     *
     * @param hrZpcpDeclareQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<IPage<HrZpcpDeclareVo>> search(@Valid @RequestBody HrZpcpDeclareQueryForm hrZpcpDeclareQueryForm);

    /**
     * POST----员工自助时根据条件查询通知情况以及申报状态
     *
     * @param hrZpcpDeclareQueryForm
     * @return
     */
    @PostMapping(value = path + "/getDeclareNoticeList/conditions")
    Result<IPage<ZpcpDeclareNoticeVo>> getDeclareNoticeList(@Valid @RequestBody HrZpcpDeclareQueryForm hrZpcpDeclareQueryForm);

    /**
     * POST----聘任审核时根据条件查询申报列表
     *
     * @param hrZpcpDeclareQueryForm
     * @return
     */
    @PostMapping(value = path + "/getDeclarList/conditions")
    Result<IPage<HrZpcpDeclareVo>> getDeclarList(@Valid @RequestBody HrZpcpDeclareQueryForm hrZpcpDeclareQueryForm);

    /**
     * GET----聘任审核时根据条件导出列表
     *
     * @param queryForm
     * @return
     */
    @GetMapping(value = path + "/exportDeclarList")
    void exportDeclareList(@Valid @ModelAttribute HrZpcpDeclareQueryForm queryForm, HttpServletRequest request, HttpServletResponse response);

    /**
     *  根据ID导出申请人详情
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/exportDeclareDetail/{id}")
    Result exportDeclareDetail(@PathVariable(value="id") Long id, HttpServletRequest request, HttpServletResponse response);



    /**
     * POST----查看聘用详情
     *
     * @param noticeId
     * @return
     */
    @GetMapping(value = path + "/getDeclarDetailList/{noticeId}")
    Result<List<HrZpcpDeclareVo>> getDeclarDetailList(@Valid @PathVariable(value="noticeId") Long noticeId);


    /**
     * GET----员工申报获取申报人基本信息
     */
    @GetMapping(value = path + "/getDeclareDetail/{noticeId}/{empCode}")
    Result<DeclareBaseVo> getDeclareDetail(@Valid @PathVariable(value = "noticeId") Long noticeId,@Valid @PathVariable(value = "empCode")String empCode);

    /**
     * 审核的时候获取申报人基本信息
     * @param noticeId
     * @return
     */
    @ApiOperation(value = "聘任审核的时候通过通知id获取-获取申报人基本信息", notes = "员工自助-获取申报人基本信息")
    @GetMapping(value = path + "/getDeclareDetailWhenCheck/{noticeId}/{empCode}")
    Result<DeclareBaseVo> getDeclareDetailWhenCheck(@PathVariable(value = "noticeId", required = true) Long noticeId, @PathVariable(value = "empCode", required = true) String empCode);

    @GetMapping(value = path + "/getIdType")
    @ApiOperation(value = "获取证件类型名称", notes = "获取证件类型名称")
    Result getIdType();

    @GetMapping(value = path + "/getDegree")
    @ApiOperation(value = "获取学位", notes = "获取学位")
    Result getDegree();

    @GetMapping(value = path + "/getEducation")
    @ApiOperation(value = "获取学历", notes = "获取学历")
    Result getEducation();

    @ApiOperation(value = "获取专业技术职务两级树形菜单", notes = "获取专业技术职务两级树形菜单")
    @GetMapping(value = path + "/getSpectech")
    Result<List<SpecTechVo>> getSpectech();


    /**
     * GET----根据通知ID获取
     *
     * @param noticeId
     * @return
     */
    @GetMapping(value = path + "/getDeclareByNoticeId/{noticeId}/{empCode}")
    Result<HrZpcpDeclareVo> getDeclareByNoticeId(@PathVariable(value = "noticeId") long noticeId,@PathVariable(value = "empCode") String empCode);


    /**
     * GET----根据通知ID获取
     *
     * @param noticeId
     * @return
     */
    @GetMapping(value = path + "getImpactDescriptionByNoticeId/{noticeId}/{empCode}")
    Result<HrZpcpDeclareVo> getImpactDescriptionByNoticeId(@PathVariable(value = "noticeId") long noticeId,@PathVariable(value = "empCode") String empCode);


}



