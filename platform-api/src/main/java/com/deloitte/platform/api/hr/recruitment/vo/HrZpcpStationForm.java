package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description : HrZpcpStation新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrZpcpStation表单")
@Data
public class HrZpcpStationForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "岗位编号")
    private String stationCode;

    @ApiModelProperty(value = "聘用类型编号")
    private Long employTypeId;

    @ApiModelProperty(value = "岗位名称")
    private String stationName;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "岗位人数")
    private Double stationNumber;

    @ApiModelProperty(value = "备注")
    private String remaks;

    @ApiModelProperty(value = "是否有效：0表示无效，1表示 有效")
    private String status;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

}
