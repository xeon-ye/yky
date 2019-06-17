package com.deloitte.platform.api.oaservice.notice.client;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskFormApprove;
import com.deloitte.platform.api.oaservice.notice.param.OaNoticeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeBpmProcessTaskForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeVo;
import com.deloitte.platform.api.oaservice.param.OaWorkflowParamForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description :  OaNotice控制器接口
 * @Modified :
 */
public interface OaNoticeClient {

    public static final String path="/oaservice/oa-notice";


    /**
     *  POST---新增
     * @param oaNoticeForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaNoticeForm oaNoticeForm);

    /**
     * 获取下一个流程审批节点数据
     *
     * @param processVariables
     * @return
     */
    @PostMapping(value = path+"/nextprocessnodes/get")
    Result getNextNodeParamVos(@Valid @ModelAttribute Map<String, Object> processVariables);

    /**
     * 提交审批申请
     * @param oaNoticeForm
     * @return
     */
    @PostMapping(value = path+"/submit/start")
    Result submitStart(@Valid @ModelAttribute OaNoticeForm oaNoticeForm, String token);

    /**
     * 审批
     * 同意审批
     * @param bpmProcessTaskForm
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute OaNoticeBpmProcessTaskForm bpmProcessTaskForm);

    /**
     * 重新提交审批
     * @param bpmProcessTaskForm
     * @return
     */
    @PostMapping(value = path+"/resubmit")
    Result reSubmit(@Valid @RequestBody OaNoticeBpmProcessTaskForm bpmProcessTaskForm);

    /**
     * 驳回审批
     * @param bpmProcessTaskForm
     * @return
     */
    @PostMapping(value = path+"/submit/reply")
    Result reply(@Valid @ModelAttribute OaNoticeBpmProcessTaskForm bpmProcessTaskForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaNoticeForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaNoticeForm oaNoticeForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaNoticeVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaNoticeQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaNoticeVo>> list(@Valid @RequestBody OaNoticeQueryForm oaNoticeQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaNoticeQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaNoticeVo>> search(@Valid @RequestBody OaNoticeQueryForm oaNoticeQueryForm, String token);

    /**
     *  GET---首页查询
     * @param num
     * @return
     */
    @GetMapping(value = path+"/list/home")
    Result<List<OaNoticeVo>> homeList(@RequestParam(value = "num", defaultValue = "3") Integer num,
                                      @RequestParam(value = "noticeTypeCode") String noticeTypeCode,
                                      String token);

    /**
     * 根据权限进行列表查询
     * @param oaNoticeQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/permission")
    Result<IPage<OaNoticeVo>> listWithPermission(@Valid @RequestBody OaNoticeQueryForm oaNoticeQueryForm, @RequestHeader(value = "token")String token);

}
