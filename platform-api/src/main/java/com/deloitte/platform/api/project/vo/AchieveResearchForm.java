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
 * @Description : AchieveResearch新增修改form对象
 * @Modified :
 */
@ApiModel("新增AchieveResearch表单")
@Data
public class AchieveResearchForm extends BaseForm {
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

    @ApiModelProperty(value = "奖项专利论文Code")
    private String awardPaperCode;

    @ApiModelProperty(value = "奖项专利论文名称")
    private String awardPaperName;

    @ApiModelProperty(value = "前五年期间")
    private Long duringFiveYear;

    @ApiModelProperty(value = "上一年")
    private Long lastYear;

}
