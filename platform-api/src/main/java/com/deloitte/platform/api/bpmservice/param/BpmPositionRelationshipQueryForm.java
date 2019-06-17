package com.deloitte.platform.api.bpmservice.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description :   BpmPositionRelationship查询from对象
 * @Modified :
 */
@ApiModel("BpmPositionRelationship查询表单")
@Data
public class BpmPositionRelationshipQueryForm extends BaseQueryForm<BpmPositionRelationshipQueryParam>  {


    @ApiModelProperty(value = "关系表ID")
    private Long id;

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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
