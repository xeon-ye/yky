package com.deloitte.platform.api.hr.common.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-15
 * @Description :   ProcessNumber查询from对象
 * @Modified :
 */
@ApiModel("ProcessNumber查询表单")
@Data
public class ProcessNumberQueryForm extends BaseQueryForm<ProcessNumberQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "流程单号代表项目")
    private String name;

    @ApiModelProperty(value = "流程单号前缀")
    private String processNumberCode;

    @ApiModelProperty(value = "流程单号中间日期")
    private String processNumberDate;

    @ApiModelProperty(value = "流程编号尾数")
    private String processNumberLast;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "流程单号单位编号")
    private String processNumberCompany;

    @ApiModelProperty(value = "当前用户的accountID")
    private String empAccount;

}
