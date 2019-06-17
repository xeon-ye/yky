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
 * @Description :   MprEvaAcadConfTable查询from对象
 * @Modified :
 */
@ApiModel("MprEvaAcadConfTable查询表单")
@Data
public class MprEvaAcadConfTableQueryForm extends BaseQueryForm<MprEvaAcadConfTableQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "会议名称")
    private String conferenceName;

    @ApiModelProperty(value = "会议类型")
    private String conferenceType;

    @ApiModelProperty(value = "国外代表人数")
    private String foreReprNum;

    @ApiModelProperty(value = "国内代表人数")
    private String domeReprNum;

    @ApiModelProperty(value = "会议地点")
    private String conferencePlace;

    @ApiModelProperty(value = "举办时间")
    private String holdingTime;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}
