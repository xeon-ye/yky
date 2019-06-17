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
 * @Description : Insandequip新增修改form对象
 * @Modified :
 */
@ApiModel("新增Insandequip表单")
@Data
public class InsandequipForm extends BaseForm {
    private static final long serialVersionUID = 1L;


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

    @ApiModelProperty(value = "仪器设备ID")
    private String insandequipId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "分类统计Code")
    private String classifiedStatisticsCode;

    @ApiModelProperty(value = "分类统计名称")
    private String classifiedStatisticsName;

    @ApiModelProperty(value = "总数量")
    private Long totalNumber;

    @ApiModelProperty(value = "在用数量")
    private Long useNumber;

    @ApiModelProperty(value = "总量原值")
    private Long originalValue;

    @ApiModelProperty(value = "在用原值")
    private Long useOriginalValue;

}
