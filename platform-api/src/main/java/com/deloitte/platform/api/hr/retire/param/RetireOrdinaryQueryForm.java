package com.deloitte.platform.api.hr.retire.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-11
 * @Description :   RetireOrdinary查询from对象
 * @Modified :
 */
@ApiModel("RetireOrdinary查询表单")
@Data
public class RetireOrdinaryQueryForm extends BaseQueryForm<RetireOrdinaryQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "员工明细ID组合")
    private String empIds;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;
}
