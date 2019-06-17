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
 * @Description : RehireReturninfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增RehireReturninfo表单")
@Data
public class RehireReturninfoForm extends BaseForm {
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

    @ApiModelProperty(value = "技术职务")
    private String technology;

    @ApiModelProperty(value = "行政职务")
    private String administrativePost;

    @ApiModelProperty(value = "返聘到期日")
    private LocalDateTime retrunEnddate;

    @ApiModelProperty(value = "拟返聘部门")
    private String retrunDep;

    @ApiModelProperty(value = "拟返聘岗位")
    private String retrunPosition;

    @ApiModelProperty(value = "拟返聘开始时间")
    private LocalDateTime retrunStartdate;

    @ApiModelProperty(value = "拟返聘结束时间")
    private LocalDateTime retrunEnddates;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "在聘申请表ID")
    private Long returnId;
}
