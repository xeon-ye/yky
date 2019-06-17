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
 * @Description :   MprEvaCoopResultTable查询from对象
 * @Modified :
 */
@ApiModel("MprEvaCoopResultTable查询表单")
@Data
public class MprEvaCoopResultTableQueryForm extends BaseQueryForm<MprEvaCoopResultTableQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "成果名称")
    private String resultName;

    @ApiModelProperty(value = "成果类型")
    private String resultType;

    @ApiModelProperty(value = "协作单位")
    private String coopUnit;

    @ApiModelProperty(value = "协作人")
    private String coopPerson;

    @ApiModelProperty(value = "完成人员所在的任务")
    private String completePersonTask;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}
