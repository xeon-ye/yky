package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-20
 * @Description :   HrarchivesinfoApply查询from对象
 * @Modified :
 */
@ApiModel("HrarchivesinfoApply查询表单")
@Data
public class HrarchivesinfoApplyQueryForm extends BaseQueryForm<HrarchivesinfoApplyQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "对象ID")
    private String empId;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String updateTime;

    @ApiModelProperty(value = "0 查档人员 1档案对象")
    private String type;


}
