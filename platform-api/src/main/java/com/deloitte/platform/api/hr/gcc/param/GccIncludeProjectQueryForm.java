package com.deloitte.platform.api.hr.gcc.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccIncludeProject查询from对象
 * @Modified :
 */
@ApiModel("GccIncludeProject查询表单")
@Data
public class GccIncludeProjectQueryForm extends BaseQueryForm<GccIncludeProjectQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "项目编号")
    private Long projectId;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "入选时间")
    private LocalDateTime selectedTime;
}
