package com.deloitte.platform.api.hr.check.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckRightQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckRightQueryInfoForm;
import com.deloitte.platform.api.hr.check.vo.CheckRightForm;
import com.deloitte.platform.api.hr.check.vo.CheckRightInfoVo;
import com.deloitte.platform.api.hr.check.vo.CheckRightVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckRight控制器接口
 * @Modified :
 */
public interface CheckRightClient {

    public static final String path="/hr/check-right";


    /**
     *  POST---新增
     * @param checkRightForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckRightForm checkRightForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkRightForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckRightForm checkRightForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckRightVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkRightQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckRightVo>> list(@Valid @RequestBody CheckRightQueryForm checkRightQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkRightQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckRightVo>> search(@Valid @RequestBody CheckRightQueryForm checkRightQueryForm);

    /**
     * 导出权限详细excel列表
     * @param checkRightQueryInfoForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/list/exportCheckRightQueryInfoForm")
    void exportCheckRightQueryInfoForm(@Valid @ModelAttribute CheckRightQueryInfoForm checkRightQueryInfoForm, HttpServletRequest request, HttpServletResponse response);


    /**
     * 获取权限信息
     * @param checkRightQueryInfoForm
     * @return
     */
    @PostMapping(value = path+"/list/getCheckRightInfo")
    Result<List<CheckRightInfoVo>>   getCheckRightInfo(@Valid @RequestBody CheckRightQueryInfoForm checkRightQueryInfoForm);


    /**
     *  Patch----部分更新
     * @param   ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);


    /**
     * 获取考核组织code集合
     * @param queryForm
     * @return
     */
    @PostMapping(value = path+"/getAllOrgCodeByRight")
    Result<List<String>> getAllOrgCodeByRight(@Valid @RequestBody CheckRightQueryForm queryForm);

}
