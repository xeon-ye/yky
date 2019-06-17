package com.deloitte.platform.api.hr.retireRehiring.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-15
 * @Description :   RetireRemindRecord查询from对象
 * @Modified :
 */
@ApiModel("RetireRemindRecord查询表单")
@Data
public class RemindRecordQueryForm extends BaseQueryForm<RemindRecordQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别 1 男 2女 3不详")
    private String gender;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "岗位编码")
    private String positionCode;

    @ApiModelProperty(value = "岗位名称")
    private String positionName;

    @ApiModelProperty(value = "部门编码")
    private String organizationCode;

    @ApiModelProperty(value = "部门名称")
    private String organizationName;

    @ApiModelProperty(value = "拟定退休日期")
    private LocalDate retireDate;

    @ApiModelProperty(value = "拟定返聘截止日期")
    private LocalDate rebateData;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String createBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "提醒表id")
    private Long remindId;
}
