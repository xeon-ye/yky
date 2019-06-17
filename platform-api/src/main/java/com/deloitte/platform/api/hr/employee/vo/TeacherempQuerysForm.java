package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description : TeacherempQuerys新增修改form对象
 * @Modified :
 */
@ApiModel("新增TeacherempQuerys表单")
@Data
public class TeacherempQuerysForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "售房单位")
    private String houseSelling;

    @ApiModelProperty(value = "售房地址")
    private String houseSellingAddres;

    @ApiModelProperty(value = "优惠号")
    private String discountNumber;

    @ApiModelProperty(value = "开证时间")
    private LocalDateTime openingTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime careteBy;

    @ApiModelProperty(value = "单位")
    private String company;

}
