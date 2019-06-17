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
 * @Date : Create in 2019-05-25
 * @Description :   Expert查询from对象
 * @Modified :
 */
@ApiModel("Expert查询表单")
@Data
public class ExpertQueryForm extends BaseQueryForm<ExpertQueryParam>  {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "专家列表ID")
    private String expertId;

    @ApiModelProperty(value = "员工编号")
    private String staffNo;

    @ApiModelProperty(value = "专家名称")
    private String expertName;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "职称")
    private String expertPost;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "学科")
    private String subject;

    @ApiModelProperty(value = "擅长领域")
    private String goodAtField;

    @ApiModelProperty(value = "创建")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

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

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "单位id")
    private String companyId;
}
