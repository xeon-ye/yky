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
 * @Description :   MprEvaTechRewardTable查询from对象
 * @Modified :
 */
@ApiModel("MprEvaTechRewardTable查询表单")
@Data
public class MprEvaTechRewardTableQueryForm extends BaseQueryForm<MprEvaTechRewardTableQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "成果名称")
    private String achieveName;

    @ApiModelProperty(value = "获奖人")
    private String obtainPerson;

    @ApiModelProperty(value = "获奖类别")
    private String awardCategory;

    @ApiModelProperty(value = "获奖等级")
    private String awardLevel;

    @ApiModelProperty(value = "获奖年度")
    private String awardYear;

    @ApiModelProperty(value = "奖项名称")
    private String awardName;

    @ApiModelProperty(value = "证书编号")
    private String certNum;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}
