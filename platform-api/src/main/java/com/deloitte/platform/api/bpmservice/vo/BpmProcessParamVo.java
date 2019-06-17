package com.deloitte.platform.api.bpmservice.vo;

import com.deloitte.platform.api.oaservice.applycenter.vo.BpmProcessVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.deloitte.platform.common.core.util.GdcPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : jackliu
 * @Date : Create in 2019-04-02
 * @Description : BpmProcessTask返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BpmProcessParamVo extends BaseVo {

    @ApiModelProperty(value = "BpmProcessTask返回的VO对象")
    private GdcPage<BpmProcessVo> bpmProcessTaskVo;

    @ApiModelProperty(value = "已办事宜总数")
    private long doneTotal;

    @ApiModelProperty(value = "办结事宜总数")
    private long endTotal;

    @ApiModelProperty(value = "抄送事宜总数")
    private long sendTotal;

    @ApiModelProperty(value = "我的请求总数")
    private long myReqTotal;
}
