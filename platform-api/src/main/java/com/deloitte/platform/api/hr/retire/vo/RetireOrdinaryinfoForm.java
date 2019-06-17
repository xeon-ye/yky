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
 * @Description : RetireOrdinaryinfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增RetireOrdinaryinfo表单")
@Data
public class RetireOrdinaryinfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "普通退休表ID")
    private Long ordinaryId;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String empName;

    @ApiModelProperty(value = "性别")
    private String empSex;

    @ApiModelProperty(value = "年龄")
    private String empAge;

    @ApiModelProperty(value = "部门")
    private String empDep;

    @ApiModelProperty(value = "岗位")
    private String empPosition;

    @ApiModelProperty(value = "拟定退休日期")
    private LocalDateTime retireDate;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "男员工退休申请主表ID")
    private Long RetireOrdinaryId;
}
