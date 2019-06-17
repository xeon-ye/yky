package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckResult返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRelationComputeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "测评通知di")
    private String checkEvaluateNotifyId;

    @ApiModelProperty(value = "考核期间名称")
    private String checkTimeName;

    @ApiModelProperty(value = "正式测评名称")
    private Double evaluateName;

    @ApiModelProperty(value = "关系关系名称")
    private Long relationName;

    @ApiModelProperty(value = "发布日期")
    private String createTime;

}
