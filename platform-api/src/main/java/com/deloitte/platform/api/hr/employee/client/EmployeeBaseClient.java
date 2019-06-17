package com.deloitte.platform.api.hr.employee.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.employee.param.EmployeeBaseQueryForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeeBaseForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeeBaseVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-03
 * @Description :  EmployeeBase控制器接口
 * @Modified :
 */
public interface EmployeeBaseClient {

    public static final String path = "/hr/employee-base";


    /**
     * POST---新增
     *
     * @param employeeBaseForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeBaseForm employeeBaseForm);

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
     * @param id, employeeBaseForm
     * @return
     */
    @PatchMapping(value = path + "/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeBaseForm employeeBaseForm);

    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<EmployeeBaseVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     *
     * @param employeeBaseQueryForm
     * @return
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<EmployeeBaseVo>> list(@Valid @RequestBody EmployeeBaseQueryForm employeeBaseQueryForm);


    /**
     * POST----复杂查询
     *
     * @param employeeBaseQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<IPage<EmployeeBaseVo>> search(@Valid @RequestBody EmployeeBaseQueryForm employeeBaseQueryForm);


    @PostMapping(value = path + "/list/selectEmployeeListBaseByLikes")
    Result<List<EmployeeBaseVo>> selectEmployeeListBaseByLikes(@Valid @RequestBody EmployeeBaseQueryForm employeeBaseQueryForm);

    /**
     * POST----准聘长聘用根据条件模糊查询用户列表
     *
     * @param employeeBaseQueryForm
     * @return
     */
    @PostMapping(value = path + "/list/selectEmployeeListBaseByLike")
    Result<IPage<EmployeeBaseVo>> selectEmployeeListBaseByLike(@Valid @RequestBody @ApiParam(name = "employeeBaseQueryForm", value = "EmployeeBase查询参数", required = true) EmployeeBaseQueryForm employeeBaseQueryForm);

    /**
     * 获取EMPCODE信息
     * @param code
     * @return
     */
    @PostMapping(value = path + "/getEmployeeByEmpCode")
    Result<EmployeeBaseVo> getEmployeeByEmpCode(@Valid @RequestBody @ApiParam(name = "code", value = "EmployeeBase查询参数", required = true) String code);

    /**
     * 根据用户id获取原始数据
     * @param id
     * @return
     */
    @GetMapping(value = path + "/selectByAccountId/{id}")
    Result<EmployeeBaseVo> selectByAccountId(@PathVariable(value = "id") String id);
}