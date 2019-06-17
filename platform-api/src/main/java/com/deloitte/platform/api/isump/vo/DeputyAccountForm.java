package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.api.utils.JsonLongDeserializer;
import com.deloitte.platform.api.utils.JsonLongSerialize;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : DeputyAccount新增修改form对象
 * @Modified :
 */
@ApiModel("新增DeputyAccount表单")
@Data
public class DeputyAccountForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "用户ID")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long userId;

    @ApiModelProperty(value = "组织架构ID")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long orgId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "个人账号排序")
    private Long sort;

    @ApiModelProperty(value = "组织内排序")
    private Long orgSort;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "启用时间")
    private LocalDateTime openTime;

    @ApiModelProperty(value = "停用时间")
    private LocalDateTime closeTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    private String reserve;

    @ApiModelProperty(value = "备用字段2")
    private String version;

    @ApiModelProperty(value = "直接上级")
    private String parentEmpNo;

}
