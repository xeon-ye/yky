package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckAchNotifyQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckAchUserListVoQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckAchUserQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchNeedDealtNotifyVo;
import com.deloitte.platform.api.hr.check.vo.CheckAchNotifyForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchNotifyVo;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserListVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :  CheckAchNotify控制器接口
 * @Modified :
 */
public interface CheckAchNotifyClient {

    public static final String path="/hr/check-ach-notify";


    /**
     *  POST---新增
     * @param checkAchNotifyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckAchNotifyForm checkAchNotifyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkAchNotifyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckAchNotifyForm checkAchNotifyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckAchNotifyVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkAchNotifyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckAchNotifyVo>> list(@Valid @RequestBody CheckAchNotifyQueryForm checkAchNotifyQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkAchNotifyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckAchNotifyVo>> search(@Valid @RequestBody CheckAchNotifyQueryForm checkAchNotifyQueryForm);

    /**
     *  POST---发布通知
     * @param checkAchNotifyForm
     * @return
     */
    @PostMapping(value = path+"/publishNotify")
    Result publishNotify(@Valid @ModelAttribute CheckAchNotifyForm checkAchNotifyForm);

    /**
     * 查询代办通知页
     * @param checkAchUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/getNeedDealtNotify")
    Result<List<CheckAchNeedDealtNotifyVo>> getNeedDealtNotify(CheckAchUserQueryForm checkAchUserQueryForm);

    /**
     *  POST----复杂查询 查询被评价人列表
     * @param  checkAchUserListVoQueryForm
     * @return
     */
    @PostMapping(value = path+"/getAchUserList")
    Result<IPage<CheckAchUserListVo>> getAchUserList(@Valid @RequestBody CheckAchUserListVoQueryForm checkAchUserListVoQueryForm);

    /**
     * 导出数据
     * @param checkAchUserListVoQueryForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportAchUserList")
    void exportAchUserList(@Valid @ModelAttribute CheckAchUserListVoQueryForm checkAchUserListVoQueryForm , HttpServletRequest request, HttpServletResponse response);

    /**
     *
     * @param checkAchUserListVoQueryForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportAchImportTempate")
    void exportAchImportTempate(@Valid @ModelAttribute  CheckAchUserListVoQueryForm checkAchUserListVoQueryForm, HttpServletRequest request, HttpServletResponse response);

    /**
     * 导入
     * @param file
     * @param checkAchUserListVoQueryForm
     * @return
     */
    @PostMapping(value = path+"/importCheckAch")
    Result importCheckAch(@RequestPart(value = "file") MultipartFile file, @RequestBody CheckAchUserListVoQueryForm checkAchUserListVoQueryForm);
}
