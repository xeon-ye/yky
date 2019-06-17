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
 * @Description :   MprEvaHighLevelTable查询from对象
 * @Modified :
 */
@ApiModel("MprEvaHighLevelTable查询表单")
@Data
public class MprEvaHighLevelTableQueryForm extends BaseQueryForm<MprEvaHighLevelTableQueryParam>  {


    @ApiModelProperty(value = " 主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "人才类型")
    private String talentType;

    @ApiModelProperty(value = "批准编号")
    private String approvalNumber;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "当选时间")
    private String electedDate;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}
