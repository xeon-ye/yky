package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   AttamentInfo查询from对象
 * @Modified :
 */
@ApiModel("AttamentInfo查询表单")
@Data
public class AttamentInfoQueryForm extends BaseQueryForm<AttamentInfoQueryParam>  {


    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "附件类型编号")
    private String attamentTypeCode;

    @ApiModelProperty(value = "附件类型")
    private String attamentType;

    @ApiModelProperty(value = "文件名称（重命名）")
    private String attamentRenanem;

    @ApiModelProperty(value = "文件名称（原名称）")
    private String attamentFullName;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

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
