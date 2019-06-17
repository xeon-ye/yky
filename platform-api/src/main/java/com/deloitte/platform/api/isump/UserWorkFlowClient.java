package com.deloitte.platform.api.isump;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.param.bpm.ApprovalForm;
import com.deloitte.platform.api.isump.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  User控制器接口
 * @Modified :
 */
public interface UserWorkFlowClient {

    public static final String path="/isump/user/workflow";

    /**
     * 流程审批
     * @param approvalForm 审批对象
     * @return
     */
    @PostMapping(value = path+"/user/approvalCheck")
    Result approvalUserCheck(@RequestBody ApprovalForm approvalForm);

    /**
     * 流程审批
     * @param approvalForm 审批对象
     * @return
     */
    @PostMapping(value = path+"/org/approvalCheck")
    Result approvalOrgCheck(@RequestBody ApprovalForm approvalForm);

    /**
     * 终止流程
     * @param approvalForm 审批对象
     * @return
     */
    @PostMapping(value = path+"/stopProcess")
    Result stopProcess(@RequestBody ApprovalForm approvalForm);


}
