package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-25
 * @Description :   Execution查询from对象
 * @Modified :
 */
@ApiModel("Execution查询表单")
@Data
public class ExecutionQueryForm extends BaseQueryForm<ExecutionQueryParam>  {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "项目执行id")
    private String exeId;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    private String applicationId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    private String ext3;

    @ApiModelProperty(value = "拓展4")
    private String ext4;

    @ApiModelProperty(value = "维度id")
    private Long orgId;

    @ApiModelProperty(value = "维度")
    private String orgPath;
}
