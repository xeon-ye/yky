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
 * @Description : Researchfunds新增修改form对象
 * @Modified :
 */
@ApiModel("新增Researchfunds表单")
@Data
public class ResearchfundsForm extends BaseForm {
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

    @ApiModelProperty(value = "科技经费ID")
    private String researchfundsId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "时期Code")
    private String periodCode;

    @ApiModelProperty(value = "时期名称")
    private String periodName;

    @ApiModelProperty(value = "前五年期间项目数量（项")
    private Long numProFiveYear;

    @ApiModelProperty(value = "前五年期间经费（万元")
    private Long fundFiveYear;

    @ApiModelProperty(value = "上一年经费数（项")
    private Long fundsLastYear;

    @ApiModelProperty(value = "上一年经费(万元)")
    private Long expenditure;

}
