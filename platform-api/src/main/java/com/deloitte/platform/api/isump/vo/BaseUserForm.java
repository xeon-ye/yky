package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : BaseUser新增修改form对象
 * @Modified :
 */
@ApiModel("新增BaseUser表单")
@Data
public class BaseUserForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "身份证")
    private String identityCard;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "护照号")
    private String passportNo;

    @ApiModelProperty(value = "工作单位")
    private String company;

    @ApiModelProperty(value = "岗位职务")
    private String position;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    private String reserve;

    @ApiModelProperty(value = "备用字段2")
    private String version;

}
