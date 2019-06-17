package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description :   TeacherempQuerys查询from对象
 * @Modified :
 */
@ApiModel("TeacherempQuerys查询表单")
@Data
public class TeacherempQuerysQueryForm extends BaseQueryForm<TeacherempQuerysQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

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
    private String createTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateTime;

    @ApiModelProperty(value = "单位")
    private String company;
}
