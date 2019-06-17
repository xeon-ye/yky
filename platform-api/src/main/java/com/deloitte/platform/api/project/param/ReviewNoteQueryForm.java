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
 * @Description :   ReviewNote查询from对象
 * @Modified :
 */
@ApiModel("ReviewNote查询表单")
@Data
public class ReviewNoteQueryForm extends BaseQueryForm<ReviewNoteQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "审批人")
    private String reviewMan;

    @ApiModelProperty(value = "审批时间")
    private LocalDateTime reviewDate;

    @ApiModelProperty(value = "审批意见")
    private String reviewOpi;

    @ApiModelProperty(value = "创建")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    private String ext3;
}
