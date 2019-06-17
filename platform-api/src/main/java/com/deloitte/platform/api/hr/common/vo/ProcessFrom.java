package com.deloitte.platform.api.hr.common.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("新增Process通知表单")
@Data
public class ProcessFrom extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "发送的人")
    private Long id;

    @ApiModelProperty(value = "对象的id")
    private Long objectId;

    @ApiModelProperty(value = "对象的类型")
    private  String objectType;

    @ApiModelProperty(value = "单位编号")
    private String comNum;

    @ApiModelProperty(value = "流程编号-首字母大写")
    private String numCode;

    @ApiModelProperty(value = "通知跳转链接")
    private String url;

    @ApiModelProperty(value = "提交部门")
    private String station;

    @ApiModelProperty(value = "提交部门")
    private String processDefineKey;
}
