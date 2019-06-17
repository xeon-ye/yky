package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpBudgetQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpBudgetForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpBudgetVo;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpEmployerInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-09
 * @Description :  ZpcpBudget控制器接口
 * @Modified :
 */
public interface ZpcpBudgetClient {

    public static final String path = "/hr/zpcp-budget";


    /**
     * POST---新增
     *
     * @param zpcpBudgetForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpBudgetForm zpcpBudgetForm);

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
     * @param id, zpcpBudgetForm
     * @return
     */
    @PatchMapping(value = path + "/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpBudgetForm zpcpBudgetForm);

    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<ZpcpBudgetVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     *
     * @param zpcpBudgetQueryForm
     * @return
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<ZpcpBudgetVo>> list(@Valid @RequestBody ZpcpBudgetQueryForm zpcpBudgetQueryForm);


    /**
     * POST----复杂查询
     *
     * @param zpcpBudgetQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<IPage<ZpcpBudgetVo>> search(@Valid @RequestBody ZpcpBudgetQueryForm zpcpBudgetQueryForm);

    /**
     * 条件导出数据列表
     *
     * @param queryForm 条件
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path + "/list/exportBudgetList")
    void exportBudgetList(@Valid @ModelAttribute ZpcpBudgetQueryForm queryForm, HttpServletRequest request, HttpServletResponse response);

    @PostMapping(value = path + "/getBudgetEmployer")
    Result<List<ZpcpEmployerInfoVo>> getBudgetEmployer(@Valid @RequestBody @ApiParam(name = "zpcpBudgetQueryForm", value = "查询参数", required = true) ZpcpBudgetQueryForm zpcpBudgetQueryForm);
}
