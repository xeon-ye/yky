package com.deloitte.platform.api.project.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description : ExeChange新增修改form对象
 * @Modified :
 */
@ApiModel("新增ExeChange表单")
@Data
public class ExeChangeForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "变更id")
    private Long changeId;

    @ApiModelProperty(value = "项目执行id")
    private Long executionId;

    @ApiModelProperty(value = "变更原因")
    private String changeAdv;

    @ApiModelProperty(value = "变更状态code")
    private Long changeStatusCode;

    @ApiModelProperty(value = "变更状态name")
    private String changeStatusName;

    @ApiModelProperty(value = "历史记录时间")
    private LocalDateTime hisTime;

    @ApiModelProperty(value = "历史记录变更人")
    private String hisBy;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    private String ext3;

    @ApiModelProperty(value = "维度id")
    private Long orgId;

    @ApiModelProperty(value = "path")
    private String orgPath;

}
