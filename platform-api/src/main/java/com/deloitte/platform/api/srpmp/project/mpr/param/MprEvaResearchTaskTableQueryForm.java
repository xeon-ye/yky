package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-25
 * @Description :   MprEvaResearchTaskTable查询from对象
 * @Modified :
 */
@ApiModel("MprEvaResearchTaskTable查询表单")
@Data
public class MprEvaResearchTaskTableQueryForm extends BaseQueryForm<MprEvaResearchTaskTableQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目编号_科研")
    private String projectNoResearch;

    @ApiModelProperty(value = "项目负责人")
    private String projectManager;

    @ApiModelProperty(value = "项目来源")
    private String projectSource;

    @ApiModelProperty(value = "实际获得经费（万元）")
    private String actualFund;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "类型（承担/参与）")
    private String type;

    @ApiModelProperty(value = "开始时间")
    private String beginTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
