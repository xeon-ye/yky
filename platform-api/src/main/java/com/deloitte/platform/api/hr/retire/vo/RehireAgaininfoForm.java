package com.deloitte.platform.api.hr.retire.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-12
 * @Description : RehireAgaininfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增RehireAgaininfo表单")
@Data
public class RehireAgaininfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String empName;

    @ApiModelProperty(value = "性别")
    private String empSex;

    @ApiModelProperty(value = "年龄")
    private Integer empAge;

    @ApiModelProperty(value = "部门")
    private String empDep;

    @ApiModelProperty(value = "原有职务")
    private String empOriginal;

    @ApiModelProperty(value = "技术职务")
    private String empTechnology;

    @ApiModelProperty(value = "拟定退休日期")
    private String rehireDate;

    @ApiModelProperty(value = "拟返聘部门")
    private String returnDep;

    @ApiModelProperty(value = "拟返聘岗位")
    private String returnPosition;

    @ApiModelProperty(value = "返聘开始时间")
    private LocalDateTime returnStartdate;

    @ApiModelProperty(value = "返聘结束时间")
    private LocalDateTime returnEnddate;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "返聘申请表ID")
    private String againId;

}
