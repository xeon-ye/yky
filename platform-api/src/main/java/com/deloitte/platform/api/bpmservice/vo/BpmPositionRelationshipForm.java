package com.deloitte.platform.api.bpmservice.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description : BpmPositionRelationship新增修改form对象
 * @Modified :
 */
@ApiModel("新增BpmPositionRelationship表单")
@Data
public class BpmPositionRelationshipForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "BMP虚拟岗位ID")
    private Long bpmPositionId;

    @ApiModelProperty(value = "实体岗位ID")
    private Long physicalPositionId;

    @ApiModelProperty(value = "负责组织ID")
    private Long chargeOrgId;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "生效时间")
    private LocalDateTime effectTime;

    @ApiModelProperty(value = "失效时间")
    private LocalDateTime loseEfficacyTime;

}
