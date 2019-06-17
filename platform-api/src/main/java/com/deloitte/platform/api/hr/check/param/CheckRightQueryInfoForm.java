package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :   CheckRight查询from对象
 * @Modified :
 */
@ApiModel("CheckRight查询表单")
@Data
public class CheckRightQueryInfoForm extends BaseQueryForm<CheckRightQueryParam>  {


//    @ApiModelProperty(value = "主键")
//    private Long id;

//    @ApiModelProperty(value = "教职工id")
//    private String userId;

    @ApiModelProperty(value = "教职工姓名")
    private String userName;

    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

    @ApiModelProperty(value = "考核工作id")
    private String checkWorkId;

//    @ApiModelProperty(value = "管理部门id")
//    private String departId;

//    @ApiModelProperty(value = "备注")
//    private String remark;
//
//
//    @ApiModelProperty(value = "创建时间")
//    private LocalDateTime createTime;
//
//    @ApiModelProperty(value = "创建人")
//    private String createBy;
//
//    @ApiModelProperty(value = "更新时间")
//    private LocalDateTime updateTime;
//
//    @ApiModelProperty(value = "更新人")
//    private String updateBy;
}
