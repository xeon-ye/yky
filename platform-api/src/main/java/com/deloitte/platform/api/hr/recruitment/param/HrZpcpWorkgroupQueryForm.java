package com.deloitte.platform.api.hr.recruitment.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpWorkgroup查询from对象
 * @Modified :
 */
@ApiModel("HrZpcpWorkgroup查询表单")
@Data
public class HrZpcpWorkgroupQueryForm extends BaseQueryForm<HrZpcpWorkgroupQueryParam>  {


    /*@ApiModelProperty(value = "主键")
    private Long id;*/

    @ApiModelProperty(value = "聘任年份")
    private String employYear;

    @ApiModelProperty(value = "小组名称")
    private String groupName;

    @ApiModelProperty(value = "单位名称")
    private String deptName;
    /*@ApiModelProperty(value = "所属单位")
    private String unit;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "备注")
    private String remarks;*/

    @ApiModelProperty(value = "1，任聘工作组、2，学术委员会、3，教授委员会、4，教职委员会")
    private String groupType;

    @ApiModelProperty(value = "是否有效：0表示无效，1表示 有效")
    private String status;

    private String name;

    /*@ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "授权经办人")
    private String authorizedOperator;*/
}
