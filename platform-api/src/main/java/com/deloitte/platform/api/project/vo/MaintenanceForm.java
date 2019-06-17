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
 * @Date : Create in 2019-06-05
 * @Description : Maintenance新增修改form对象
 * @Modified :
 */
@ApiModel("新增Maintenance表单")
@Data
public class MaintenanceForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "维护项目id")
    private String maintenanceId;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    private String applicationId;

    @ApiModelProperty(value = "项目主要内容")
    private String mainNote;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "维护状态code")
    private String mainStatusCode;

    @ApiModelProperty(value = "维护状态name")
    private String mainStatusName;

    @ApiModelProperty(value = "关联父级项目id projectId")
    private String mainParentId;

}
