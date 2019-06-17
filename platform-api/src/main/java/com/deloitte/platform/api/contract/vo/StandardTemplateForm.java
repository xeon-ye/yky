package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : yangyq
 * @Date : Create in 2019-04-25
 * @Description : StandardTemplate新增修改form对象
 * @Modified :
 */
@ApiModel("新增StandardTemplate表单")
@Data
public class StandardTemplateForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "标准文本编号")
    private String templateCode;

    @ApiModelProperty(value = "标准文本名称")
    private String templateName;

    @ApiModelProperty(value = "属性编号")
    private String attributeCode;

    @ApiModelProperty(value = "属性值")
    private String attribute;

    @ApiModelProperty(value = "组织范围编号")
    private String orgCode;

    @ApiModelProperty(value = "组织范围值")
    private String org;

    @ApiModelProperty(value = "合同类型编号")
    private String contractTypeCode;

    @ApiModelProperty(value = "合同类型")
    private String contractType;

    @ApiModelProperty(value = "审批状态")
    private String statue;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

    @ApiModelProperty(value = "备用字段")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    private Long spareField5;

    @ApiModelProperty(value = "文件编号")
    private String attamentId;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String fileUrl;

    @ApiModelProperty(value = "备注说明")
    private String standardRemark;

    @ApiModelProperty(value = "流程定义key")
    private String processDefineKey;

    @ApiModelProperty(value = "创建人名称")
    private String createByName;
}
