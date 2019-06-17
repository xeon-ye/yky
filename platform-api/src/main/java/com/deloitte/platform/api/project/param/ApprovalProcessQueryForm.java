package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-22
 * @Description :   ApprovalProcess查询from对象
 * @Modified :
 */
@ApiModel("ApprovalProcess查询表单")
@Data
public class ApprovalProcessQueryForm extends BaseQueryForm<ApprovalProcessQueryParam>  {


    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "机构")
    private String orgCode;

    @ApiModelProperty(value = "流程类型(1：合同审批，2：合同打印签署，3：合同办结，4：合同作废，5：经办人移交，6：履行人移交)")
    private String processType;

    @ApiModelProperty(value = "流程key")
    private String processDefineKey;

    @ApiModelProperty(value = "流程名称")
    private String processDefineName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

    @ApiModelProperty(value = "备用字段")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    private LocalDateTime ext4;

    @ApiModelProperty(value = "备用字段")
    private Long ext5;
}
