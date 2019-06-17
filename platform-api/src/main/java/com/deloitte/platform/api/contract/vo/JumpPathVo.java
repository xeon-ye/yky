package com.deloitte.platform.api.contract.vo;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-03
 * @Description : BasicInfo返回的VO对象
 * @Modified :
 */
@Data
public class JumpPathVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private String id;

    @ApiModelProperty(value = "请求页面的类型   1=合同审批  2=合同签署  3=合同办结   4=合同作废  5=经办人移交   6=履行人移交   7=合同定稿   8=合同履行   9=合同打印   10=标准文本审批  11=标准文本失效")
    private String path;

    @ApiModelProperty(value = "是否跳转到审批页面   1=跳转到审批    0=默认页面")
    private String jumpApproval;

    @ApiModelProperty(value = "跳转到审批页面需要携带的参数   jumpApproval=0时 参数为空")
    private BpmProcessTaskVo bpmProcessTaskVo;

}
