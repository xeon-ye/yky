package com.deloitte.platform.api.hr.check.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.*;
import com.deloitte.platform.api.hr.check.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckResult控制器接口
 * @Modified :
 */
public interface CheckResultClient {

    public static final String path="/hr/check-result";


    /**
     *  POST---新增
     * @param checkResultForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckResultForm checkResultForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkResultForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckResultForm checkResultForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckResultVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkResultQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckResultVo>> list(@Valid @RequestBody CheckResultQueryForm checkResultQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkResultQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckResultVo>> search(@Valid @RequestBody CheckResultQueryForm checkResultQueryForm);


    /**
     *  POST----复杂查询 查询结果结算的考核关系列表
     * @param  checkRelationComputeQueryForm
     * @return
     */
    @PostMapping(value = path+"/getCheckRelationComputeList")
    Result<IPage<CheckRelationComputeVo>> getCheckRelationComputeList(@Valid @RequestBody CheckRelationComputeQueryForm checkRelationComputeQueryForm);

    /**
     *  POST----计算
     * @param  checkResultComputeFormList
     * @return
     */
    @PostMapping(value = path+"/computingResult")
    Result computingResult(@RequestBody  List<CheckResultComputeForm>  checkResultComputeFormList);

    /**
     * 结果等级计算
     * @param checkComputeCheckLevelFromList
     * @return
     */
    @PostMapping(value = path+"/computeCheckLevel")
    Result  computeCheckLevel(@RequestBody List<CheckComputeCheckLevelFrom> checkComputeCheckLevelFromList);

    /**
     * 结果名次计算
     * @param checkComputeCheckRankFrom
     * @return
     */
    @PostMapping(value = path+"/computeCheckRank")
    Result computeCheckRank(@Valid @RequestBody CheckComputeCheckRankFrom checkComputeCheckRankFrom);

    /**
     * 结果统计数据
     * @param checkResultInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/getCheckResultStatistics")
    Result<List<CheckResultStatisticsVo>> getCheckResultStatistics(@Valid @RequestBody  CheckResultInfoQueryForm checkResultInfoQueryForm);

    /**
     * 发布考核结果
     * @param checkCommonModifyForm
     * @return
     */
    @PostMapping(value = path+"/publishResult")
    Result publishResult(@Valid @RequestBody CheckCommonModifyForm checkCommonModifyForm);

    /**
     * 获取结果详情
     * @param queryForm
     * @return
     */
    @PostMapping(value = path+"/getCheckResultInfoVo")
    Result<IPage<CheckResultInfoVo>> getCheckResultInfoVo(@Valid @RequestBody CheckResultInfoQueryForm queryForm);

    /**
     *
     * @param checkResultUpdateForm
     * @return
     */
    @PostMapping(value = path+"/updatePartInfo")
    Result  updatePartInfo(@Valid @RequestBody  CheckResultUpdateForm checkResultUpdateForm);



    /**
     * 分数导出
     * @param checkResultInfoQueryForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportResultScore")
    void exportResultScore(@Valid @ModelAttribute CheckResultInfoQueryForm checkResultInfoQueryForm , HttpServletRequest request, HttpServletResponse response);

    /**
     * 等级导出
     * @param checkResultInfoQueryForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportResultLevel")
    void exportResultLevel(@Valid @ModelAttribute CheckResultInfoQueryForm checkResultInfoQueryForm , HttpServletRequest request, HttpServletResponse response);


    /**
     * 沟通人员导出
     * @param checkResultInfoQueryForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportCheckChat")
    void exportCheckChat(@Valid @ModelAttribute CheckResultInfoQueryForm checkResultInfoQueryForm , HttpServletRequest request, HttpServletResponse response);

    /**
     * 沟通统计导出
     * @param checkResultInfoQueryForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportCheckResultStatistics")
    void exportCheckResultStatistics(@Valid @ModelAttribute CheckResultInfoQueryForm checkResultInfoQueryForm, HttpServletRequest request, HttpServletResponse response);


    /**
     *
     *  导出历史
     * @param checkResultInfoQueryForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportCheckResultHistory")
    void exportCheckResultHistory(@Valid @ModelAttribute CheckResultInfoQueryForm checkResultInfoQueryForm, HttpServletRequest request, HttpServletResponse response);

}
