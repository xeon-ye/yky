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
 * @Date : Create in 2019-05-25
 * @Description : ServiceNum新增修改form对象
 * @Modified :
 */
@ApiModel("新增ServiceNum表单")
@Data
public class ServiceNumForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    private String applicationId;

    @ApiModelProperty(value = "申报业务号")
    private String applyNum;

    @ApiModelProperty(value = "申报书取消业务号")
    private String applyCancelNum;

    @ApiModelProperty(value = "评审业务号")
    private String reviewNum;

    @ApiModelProperty(value = "批复业务号")
    private String replyNum;

    @ApiModelProperty(value = "项目变更业务号")
    private String changeNum;

    @ApiModelProperty(value = "业务项目号")
    private String bussNum;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    private String ext3;

    @ApiModelProperty(value = "业务重复号")
    private String serviceOnly;

    @ApiModelProperty(value = "当前年度")
    private Long curYear;

    @ApiModelProperty(value = "序列号")
    private Integer num;

}
