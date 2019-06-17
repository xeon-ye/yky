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
 * @Description :   BpmPosition查询from对象
 * @Modified :
 */
@ApiModel("BpmPosition查询表单")
@Data
public class BpmPositionQueryForm extends BaseQueryForm<BpmPositionQueryParam>  {


    @ApiModelProperty(value = "主键Id")
    private Long id;

    @ApiModelProperty(value = "对应的流程定义ID")
    private String processDefineKey;

    @ApiModelProperty(value = "对应的流程名称")
    private String processName;

    @ApiModelProperty(value = "审批流程中的节点ID")
    private String nodeId;

    @ApiModelProperty(value = "审批流程中的节点名称")
    private String nodeName;

    @ApiModelProperty(value = "审批流程中的节点序号")
    private Integer nodeSequence;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String ext1;

    @ApiModelProperty(value = "${field.comment}")
    private String ext2;

    @ApiModelProperty(value = "${field.comment}")
    private String ext3;

    @ApiModelProperty(value = "${field.comment}")
    private String ext4;

    @ApiModelProperty(value = "${field.comment}")
    private String ext5;

    @ApiModelProperty(value = "虚拟组织名称")
    private String name;

    @ApiModelProperty(value = "虚拟组织状态")
    private String state;
}
