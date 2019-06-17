package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : SysCommonLanguage新增修改form对象
 * @Modified :
 */
@ApiModel("新增SysCommonLanguage表单")
@Data
public class SysCommonLanguageForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "使用场景编码")
    private String applicationCode;

    @ApiModelProperty(value = "使用场景")
    private String application;

    @ApiModelProperty(value = "常用语内容")
    private String context;

    @ApiModelProperty(value = "人员id")
    private String belongPerson;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

    @ApiModelProperty(value = "备用字段")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    private Long spareField5;

}
