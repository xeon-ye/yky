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
 * @Date : Create in 2019-04-08
 * @Description :   EmployCount查询from对象
 * @Modified :
 */
@ApiModel("EmployCount查询表单")
@Data
public class EmployCountQueryForm extends BaseQueryForm<EmployCountQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "账户表ID")
    private Long accountId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号码")
    private String idcard;

    @ApiModelProperty(value = "应聘部门")
    private String appDep;

    @ApiModelProperty(value = "应聘岗位")
    private String appPosition;

    @ApiModelProperty(value = "拟定入职时间")
    private LocalDateTime entryTime;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "流程状态ID")
    private String flowId;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "填表年份")
    private String year;
}
