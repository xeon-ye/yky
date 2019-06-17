package com.deloitte.platform.api.project.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description : Equipment新增修改form对象
 * @Modified :
 */
@ApiModel("新增Equipment表单")
@Data
public class EquipmentForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "设备购置ID")
    private String equId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "明细名称")
    private String projectAbs;

    @ApiModelProperty(value = "规格型号")
    private String model;

    @ApiModelProperty(value = "产地")
    private String equAddress;

    @ApiModelProperty(value = "用途")
    private String equUse;

    @ApiModelProperty(value = "数量")
    private String equNum;

    @ApiModelProperty(value = "实施周期")
    private String equCycle;

    @ApiModelProperty(value = "中央财政经费")
    private String fundingCenter;

    @ApiModelProperty(value = "主管部门经费")
    private String fundingDirector;

    @ApiModelProperty(value = "其他经费")
    private String fundingOther;

    @ApiModelProperty(value = "经费合计")
    private String fundingTotal;

    @ApiModelProperty(value = "拓展字段")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    private String orgPath;

}
