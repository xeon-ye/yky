package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.vo.DeptForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.DictVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import java.util.HashMap;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-01
 * @Description :  Dept控制器接口
 * @Modified :
 */
public interface DeptClient {

    public static final String path="/isump/dept";


    /**
     *  POST---新增
     * @param deptForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute DeptForm deptForm);

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
     * @param  id, deptForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody DeptForm deptForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<DeptVo> get(@PathVariable(value = "id") long id);

    @GetMapping(value = path+"/getByCode/{code}")
    Result<DeptVo> getByCode(@PathVariable(value = "code") String code);

    /**
     *  POST----列表查询
     * @param   
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<DeptVo>> list(@Valid @RequestBody DeptQueryForm deptQueryForm);


    /**
     *  POST----复杂查询
     * @param  deptQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<DeptVo>> search(@Valid @RequestBody DeptQueryForm deptQueryForm);

    /**
     * POST----复杂查询
     * @param deptQueryForm
     * @return
     */
    @PostMapping(value = path+"/page2/conditions")
    Result<GdcPage<DeptVo>> search2(@Valid @RequestBody DeptQueryForm deptQueryForm);

    /**
     * 根据编码查询code和name的映射
     * @param codeList
     * @return
     */
    @PostMapping(value = path+"/searchNameByCodes")
    Result<HashMap<String,String>> searchNameByCodes(@RequestBody List<String> codeList);

    /**
     * 获取单位,返回code和name的映射
     * @param deptQueryForm
     * @return
     */
    @PostMapping(value = path+"/searchCodeAndName")
    Result<HashMap<String,String>> searchCodeAndName(@Valid @RequestBody DeptQueryForm deptQueryForm);

    @GetMapping(value = path+"/getByName/{name}")
    Result<DeptVo> getDeptByName(@PathVariable(value = "name")  String name);
}
