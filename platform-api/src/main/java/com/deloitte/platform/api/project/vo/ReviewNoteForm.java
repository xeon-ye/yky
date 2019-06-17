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
 * @Description : ReviewNote新增修改form对象
 * @Modified :
 */
@ApiModel("新增ReviewNote表单")
@Data
public class ReviewNoteForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "审批人")
    private String reviewMan;

    @ApiModelProperty(value = "审批时间")
    private LocalDateTime reviewDate;

    @ApiModelProperty(value = "审批意见")
    private String reviewOpi;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    private String ext3;

}
