package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-09
 * @Description : CommonReason新增修改form对象
 * @Modified :
 */
@ApiModel("新增CommonReason表单")
@Data
public class CommonReasonForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "原因")
    private String reason;

    @ApiModelProperty(value = "原因类型")
    private String reasonType;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

}
