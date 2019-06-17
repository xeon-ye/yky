package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :   MprEvaTreaPopTable查询from对象
 * @Modified :
 */
@ApiModel("MprEvaTreaPopTable查询表单")
@Data
public class MprEvaTreaPopTableQueryForm extends BaseQueryForm<MprEvaTreaPopTableQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "科普基地名称")
    private String popularBaseName;

    @ApiModelProperty(value = "科普内容")
    private String popularScienceContent;

    @ApiModelProperty(value = "成立年份")
    private String yearEstablished;

    @ApiModelProperty(value = "接待人次（千人）")
    private String receptionNumber;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}
