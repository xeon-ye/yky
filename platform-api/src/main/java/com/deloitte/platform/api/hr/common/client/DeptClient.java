package com.deloitte.platform.api.hr.common.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.common.param.HrDeptQueryForm;
import com.deloitte.platform.api.hr.common.vo.HrDeptForm;
import com.deloitte.platform.api.hr.common.vo.HrDeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-01
 * @Description :  Dept控制器接口
 * @Modified :
 */
public interface DeptClient {

    public static final String path="/hr/dept";


    /**
     *  POST---新增
     * @param hrDeptForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrDeptForm hrDeptForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     * get
     * @param id
     * @return
     */
    @GetMapping(value = path+"/deptEnabled/{id}")
    Result deptEnabled(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrDeptForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrDeptForm hrDeptForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrDeptVo> get(@PathVariable(value = "id") long id);

    @GetMapping(value = path+"/getByCode/{code}")
    Result<HrDeptVo> getByCode(@PathVariable(value = "code") String code);

    /**
     *  POST----列表查询
     * @param   
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrDeptVo>> list(@Valid @RequestBody HrDeptQueryForm hrDeptQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrDeptQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrDeptVo>> search(@Valid @RequestBody HrDeptQueryForm hrDeptQueryForm);

    /**
     * POST----复杂查询
     * @param hrDeptQueryForm
     * @return
     */
    @PostMapping(value = path+"/page2/conditions")
    Result<GdcPage<HrDeptVo>> search2(@Valid @RequestBody HrDeptQueryForm hrDeptQueryForm);

    /**
     * 根据编码查询code和name的映射
     * @param codeList
     * @return
     */
    @PostMapping(value = path+"/searchNameByCodes")
    Result<HashMap<String,String>> searchNameByCodes(@RequestBody List<String> codeList);

    @GetMapping(value = path+"/getByName/{name}")
    Result<HrDeptVo> getDeptByName(@PathVariable(value = "name") String name);
}
