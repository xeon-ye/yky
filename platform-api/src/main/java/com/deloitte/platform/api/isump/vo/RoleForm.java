package com.deloitte.platform.api.isump.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : Role新增修改form对象
 * @Modified :
 */
@ApiModel("新增Role表单")
@Data
public class RoleForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型(分组，功能，数据)")
    private String type;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "上级ID")
    private Long parentId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    private String reserve;

    @ApiModelProperty(value = "备用字段2")
    private String version;

    @ApiModelProperty(value = "服务类型")
    private String service;

    @ApiModelProperty(value = "角色code")
    private String code;

}
