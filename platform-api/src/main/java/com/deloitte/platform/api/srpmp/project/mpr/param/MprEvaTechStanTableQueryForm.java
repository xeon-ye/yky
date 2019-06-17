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
 * @Description :   MprEvaTechStanTable查询from对象
 * @Modified :
 */
@ApiModel("MprEvaTechStanTable查询表单")
@Data
public class MprEvaTechStanTableQueryForm extends BaseQueryForm<MprEvaTechStanTableQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "获得技术标准名称")
    private String obtTechStanName;

    @ApiModelProperty(value = "标准类型")
    private String stanType;

    @ApiModelProperty(value = "标准号")
    private String stanNum;

    @ApiModelProperty(value = "完成人")
    private String completePerson;

    @ApiModelProperty(value = "发布时间")
    private String postDate;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}
