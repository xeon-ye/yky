package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-12
 * @Description : ZpcpEmployerInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpEmployerInfo表单")
@Data
public class ZpcpEmployerInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "教职工编号")
    private Long userId;

    @ApiModelProperty(value = "教职工姓名")
    private String name;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "岗位")
    private String post;

    @ApiModelProperty(value = "聘用状态")
    private String employmentStatus;

    @ApiModelProperty(value = "聘用时间起")
    private LocalDateTime employmentStarttime;

    @ApiModelProperty(value = "聘用时间止")
    private LocalDateTime employmentEndtime;

    @ApiModelProperty(value = "薪酬制度")
    private String salarySystem;

    @ApiModelProperty(value = "基本年薪")
    private Long totalSalay;

    @ApiModelProperty(value = "聘用类型编码")
    private Long employCode;

    @ApiModelProperty(value = "聘用类型名称")
    private String employName;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织编码")
    private String organizationCode;

    @ApiModelProperty(value = "任职年限")
    private String appointmentTime;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "有效状态(0无效,1有效)")
    private String status;

}
