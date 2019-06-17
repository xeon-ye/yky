package com.deloitte.platform.api.oaservice.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description : OaAssistantMapping新增修改form对象
 * @Modified :
 */
@ApiModel("新增OaAssistantMapping表单")
@NoArgsConstructor
@Data
public class OaAssistantMappingForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "秘书ID")
    @NotEmpty
    private String userId;

    @ApiModelProperty(value = "秘书名称")
    private String userName;

    @ApiModelProperty(value = "领导ID")
    @NotEmpty
    private String leaderId;

    @ApiModelProperty(value = "领导名称")
    private String leaderName;

    @ApiModelProperty(value = "领导所属部门ID")
    private String leaderDeptId;

    @ApiModelProperty(value = "领导所属部门名称")
    private String leaderDeptName;

    @ApiModelProperty(value = "是否有效")
    private String enableFlag;

    @ApiModelProperty(value = "备用字段")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    private String ext4;

    @ApiModelProperty(value = "备用字段")
    private String ext5;

}
