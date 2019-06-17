package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-22
 * @Description : HrarchivesinfoApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrarchivesinfoApply表单")
@Data
public class HrarchivesinfoApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "对象ID")
    private String empId;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "0 查档人员 1档案对象")
    private String type;

    @ApiModelProperty(value = "人事档案申请表ID")
    private String hrAid;

    @ApiModelProperty(value = "复印数")
    private String copynum;

    @ApiModelProperty(value = "归还时间")
    private LocalDateTime returnTime;

    @ApiModelProperty(value = "${field.comment}")
    private String companyname;

    @ApiModelProperty(value = "${field.comment}")
    private String deptname;

    @ApiModelProperty(value = "${field.comment}")
    private String postname;

    @ApiModelProperty(value = "${field.comment}")
    private String polit;

    private String name;

}
