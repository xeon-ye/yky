package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-19
 * @Description : ZpcpCheckNumber新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpCheckNumber表单")
@Data
public class ZpcpCheckNumberForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "通知的id")
    private Long noticeId;

    @ApiModelProperty(value = "审核小组名称")
    private Long checkGroupId;

    @ApiModelProperty(value = "应到人数")
    private Long shouldNumber;

    @ApiModelProperty(value = "实际到人数")
    private String trueNumber;

    @ApiModelProperty(value = "${field.comment}")
    private String organizationCode;

    @ApiModelProperty(value = "审核类型(1.聘任工作组,2,学术委员会,3.教授聘任委员会,4教职聘任委员会)")
    private String checkType;

}
