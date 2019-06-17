package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckAchEvaluateNotifyQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckAchEvaluateUserListQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckAchEvaluateUserQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateInfoNotifyVo;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateNotifyForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateNotifyVo;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateUserListVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :  CheckAchEvaluateNotify控制器接口
 * @Modified :
 */
public interface CheckAchEvaluateNotifyClient {

    public static final String path="/hr/check-ach-evaluate-notify";


    /**
     *  POST---新增
     * @param checkAchEvaluateNotifyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckAchEvaluateNotifyForm checkAchEvaluateNotifyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkAchEvaluateNotifyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckAchEvaluateNotifyForm checkAchEvaluateNotifyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckAchEvaluateNotifyVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkAchEvaluateNotifyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckAchEvaluateNotifyVo>> list(@Valid @RequestBody CheckAchEvaluateNotifyQueryForm checkAchEvaluateNotifyQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkAchEvaluateNotifyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckAchEvaluateNotifyVo>> search(@Valid @RequestBody CheckAchEvaluateNotifyQueryForm checkAchEvaluateNotifyQueryForm);

    /**
     *  POST----列表查询
     * @param   checkAchEvaluateUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/getEvaluateNotifyInfo")
    Result<List<CheckAchEvaluateInfoNotifyVo>> getEvaluateNotifyInfo(@Valid @RequestBody CheckAchEvaluateUserQueryForm checkAchEvaluateUserQueryForm);

    /**
     *  POST---发布测评
     * @param checkAchEvaluateNotifyForm
     * @return
     */
    @PostMapping(value = path+"/publishEvaluateNotify")
    Result publishEvaluateNotify(@Valid @ModelAttribute CheckAchEvaluateNotifyForm checkAchEvaluateNotifyForm);

    /**
     *  POST----复杂查询
     * @param  checkAchEvaluateUserListQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/getEvaluateUserList")
    Result<IPage<CheckAchEvaluateUserListVo>> getEvaluateUserList(@Valid @RequestBody CheckAchEvaluateUserListQueryForm checkAchEvaluateUserListQueryForm);

    /**
     * 导出数据
     * @param checkAchEvaluateUserListQueryForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportEvaluateUserList")
    void exportEvaluateUserList( @Valid @ModelAttribute CheckAchEvaluateUserListQueryForm checkAchEvaluateUserListQueryForm, HttpServletRequest request, HttpServletResponse response);


}
