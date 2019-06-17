package com.deloitte.platform.api.fssc.mq.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-29
 * @Description :   SyncMsg查询from对象
 * @Modified :
 */
@ApiModel("SyncMsg查询表单")
@Data
public class SyncMsgQueryForm extends BaseQueryForm<SyncMsgQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "关联ID")
    private Long relationId;

    @ApiModelProperty(value = "消息类型")
    private String msgType;

    @ApiModelProperty(value = "消息主体")
    private String msgBody;

    @ApiModelProperty(value = "消息状态")
    private String msgStatus;

    @ApiModelProperty(value = "失败错误描述")
    private String msgErrorInfo;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}
