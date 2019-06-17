package com.deloitte.platform.api.hr.check.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :   CheckUser查询from对象
 * @Modified :
 */
@ApiModel("CheckUser查询表单")
@Data
public class CheckUserQueryForm extends BaseQueryForm<CheckUserQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;


    @ApiModelProperty(value = "考核工作Id")
    private String checkWorkId;

    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

    @ApiModelProperty(value = "教职工id")
    private String userId;

    @ApiModelProperty(value = "是否参与")
    private String isPartake;

    @ApiModelProperty(value = "不参加原因")
    private String notPartakeReason;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
