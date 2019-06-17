package com.deloitte.platform.api.oaservice.notice.vo;


import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskFormApprove;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * oa notice 流程请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OaNoticeBpmProcessTaskForm extends BpmProcessTaskFormApprove {

    private Map<String, String> taskParams;

    private List<NextNodeParamVo> nextNodeParamVos;

}
