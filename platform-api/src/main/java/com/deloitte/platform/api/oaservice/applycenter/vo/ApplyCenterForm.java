package com.deloitte.platform.api.oaservice.applycenter.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description : ApplyCenter新增修改form对象
 * @Modified :
 */
@ApiModel("新增ApplyCenter表单")
@Data
public class ApplyCenterForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "自增长id")
    private Long applyId;

    @ApiModelProperty(value = "图片URL，常用流程中该字段为空")
    private String picUrl;

    @ApiModelProperty(value = "应用名称")
    private String applyName;

    @ApiModelProperty(value = "应用排序")
    private String applySort;

    @ApiModelProperty(value = "是否可见，1表示可见，0表示不可见")
    private String isVisiable;

    @ApiModelProperty(value = "发布时间，默认系统时间")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "更新时间，默认系统时间")
    private LocalDateTime applyUpdatetime;

    @ApiModelProperty(value = "类型，应用中心或常用流程")
    private String typeName;

    @ApiModelProperty(value = "删除状态，默认0，表示未删除")
    private String delFlag;

    @ApiModelProperty(value = "跳转到其他系统的url")
    private String applyUrl;

    @ApiModelProperty(value = "对应applyName，字典表中查询")
    private String applyCode;

    @ApiModelProperty(value = "系统标识")
    private String systemCode;
}
