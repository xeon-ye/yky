package com.deloitte.platform.api.fssc.mq.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-29
 * @Description : SyncMsg新增修改form对象
 * @Modified :
 */
@ApiModel("新增SyncMsg表单")
@Data
public class SyncMsgForm extends BaseForm {
    private static final long serialVersionUID = 1L;


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

}
