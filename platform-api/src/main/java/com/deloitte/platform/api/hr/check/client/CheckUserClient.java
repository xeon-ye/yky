package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckUserInfoQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckUserQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckUserAddForm;
import com.deloitte.platform.api.hr.check.vo.CheckUserForm;
import com.deloitte.platform.api.hr.check.vo.CheckUserInfoVo;
import com.deloitte.platform.api.hr.check.vo.CheckUserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckUser控制器接口
 * @Modified :
 */
public interface CheckUserClient {

    public static final String path="/hr/check-user";


    /**
     *  POST---新增
     * @param checkUserForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckUserForm checkUserForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkUserForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckUserForm checkUserForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckUserVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckUserVo>> list(@Valid @RequestBody CheckUserQueryForm checkUserQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckUserVo>> search(@Valid @RequestBody CheckUserQueryForm checkUserQueryForm);


    /**
     *  POST----列表查询
     * @param   checkUserInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/getCheckUserInfo")
    Result<List<CheckUserInfoVo>> getCheckUserInfo(@Valid @RequestBody CheckUserInfoQueryForm checkUserInfoQueryForm);

    /**
     *  POST----批量保存
     * @param   checkUserAddForm
     * @return
     */
    @PostMapping(value = path+"/beatchSaveOrUpdate")
    Result beatchSaveOrUpdate(@Valid @RequestBody CheckUserAddForm checkUserAddForm);

    /**
     *  POST----批量删除
     * @param   checkUserAddForm
     * @return
     */
    @PostMapping(value = path+"/beatchDelete")
    Result  beatchDelete(@Valid @RequestBody CheckUserAddForm checkUserAddForm);

    /**
     * 导出考核人员列表
     * @param queryForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportCheckuserInfo")
    void exportCheckuserInfo(@Valid @ModelAttribute CheckUserInfoQueryForm queryForm, HttpServletRequest request, HttpServletResponse response);

    /**
     * 导入
     * @param file
     * @param checkImportForm
     * @return
     */
    @PostMapping(value = path+"/importCheckUser")
    Result importCheckUser(@RequestPart(value = "file") MultipartFile file,@RequestBody CheckUserAddForm checkImportForm);


    /**
     * 导出考核人员导入模板
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportCheckUserTempate")
    void exportCheckUserTempate(HttpServletRequest request, HttpServletResponse response);
}
