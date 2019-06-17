/**
 * 
 */
package com.deloitte.platform.api.bpmservice.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 *  流程审批者实体
 * @author yoli
 *
 */
@ApiModel("下一环节审批需要的参数")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NextNodeParamVo extends BaseVo
{

	/**
	 * 账号ID
	 */
	@NotBlank(message="用户账号不能为空")
	@ApiModelProperty(value = "下一环节审批人账号")
	private String acountId;

	/**
	 * 用户名
	 */
	@NotBlank(message="用户名不能为空")
	@ApiModelProperty(value = "下一环节审批人姓名")
	private String acountName;

	@ApiModelProperty(value = "代理人id")
	private String agentId;

	@ApiModelProperty(value = "代理人名称")
	private String agentName;

	/**
	 * 岗位ID
	 */
	@NotBlank(message="岗位ID不能为空")
	@ApiModelProperty(value = "下一环节审批人岗位")
	private String stationId;

	@ApiModelProperty(value = "下一环节审批人组织")
	private String orgId;

	@ApiModelProperty(value = "下一环节审批人描述")
	private String approverDescription;

	@ApiModelProperty(value = "下一环节审批对象URL")
	private String objectUrl;

	@ApiModelProperty(value = "下一环节审批对象APP URL")
	private String objectAppUrl;


}
