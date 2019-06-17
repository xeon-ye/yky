package com.deloitte.platform.api.oaservice.fastway.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-17
 * @Description : FastWay新增修改form对象
 * @Modified :
 */
@ApiModel("新增FastWay表单")
@Data
public class FastWayForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "图片URL")
    private String picUrl;

    @ApiModelProperty(value = "应用名称")
    private String applyName;

    @ApiModelProperty(value = "应用排序")
    private String applySort;

    @ApiModelProperty(value = "发布时间，默认系统时间")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "更新时间，默认系统时间")
    private LocalDateTime applyUpdatetime;

    @ApiModelProperty(value = "跳转的url")
    private String applyUrl;

}
