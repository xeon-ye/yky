package com.deloitte.platform.api.hr.employ.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-09
 * @Description :   RecruitCount查询from对象
 * @Modified :
 */
@ApiModel("RecruitCount查询表单")
@Data
public class RecruitCountQueryForm extends BaseQueryForm<RecruitCountQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Double id;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "部门")
    private String dept;
}
