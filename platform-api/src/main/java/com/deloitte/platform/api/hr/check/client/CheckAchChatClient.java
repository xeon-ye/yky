package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckAchChatQueryForm;
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
 * @Description :  CheckAchChat控制器接口
 * @Modified :
 */
public interface CheckAchChatClient {

    public static final String path="/hr/check-Ach-chat";


    /**
     *  POST---新增
     * @param checkAchChatForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckAchChatForm checkAchChatForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkAchChatForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckAchChatForm checkAchChatForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckAchChatVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkAchChatQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckAchChatVo>> list(@Valid @RequestBody CheckAchChatQueryForm checkAchChatQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkAchChatQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckAchChatVo>> search(@Valid @RequestBody CheckAchChatQueryForm checkAchChatQueryForm);

    /**
     * 发布绩效沟通通知
     * @param checkAchChatNotifyForm
     * @return
     */
    @PostMapping(value = path+"/page/publishChatNotify")
    Result publishChatNotify(@Valid @RequestBody CheckAchChatNotifyForm checkAchChatNotifyForm);


    /**
     * 获取绩效通知详情列表
     * @param checkAchChatQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/getCheckResultChatInfo")
    Result<IPage<CheckResultChatInfoVo>> getCheckResultChatInfo(@Valid @RequestBody  CheckAchChatQueryForm checkAchChatQueryForm);

    /**
     * 绩效沟通填写或者申诉
     * @param checkAchChatAppealForm
     * @return
     */
    @PostMapping(value = path+"/saveCheckChatAppeal")
    Result saveCheckChatAppeal(@Valid @RequestBody  CheckAchChatAppealForm checkAchChatAppealForm);

    /**
     * 导出年度考核表
     * @param checkAchChatQueryForm
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path+"/exportYearCheckTable")
    void   exportYearCheckTable(@ModelAttribute CheckAchChatQueryForm checkAchChatQueryForm, HttpServletRequest request, HttpServletResponse response);

    /**
     * 发布绩效沟通通知
     * @param checkAchChatUpadteForm
     * @return
     */
    @PostMapping(value = path+"/updateCheckChat")
    Result updateCheckChat(@Valid @RequestBody CheckAchChatUpadteForm checkAchChatUpadteForm);
}
