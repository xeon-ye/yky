package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : yangyq
 * @Date : Create in 2019-04-24
 * @Description : TemplatePersonMap新增修改form对象
 * @Modified :
 */
@ApiModel("新增TemplatePersonMap表单")
@Data
public class TemplatePersonMapForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "标准文本编号")
    private String templateCode;

    @ApiModelProperty(value = "人员id")
    private String personCode;

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
