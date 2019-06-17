package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :   ZpcpFirstLevelSubject查询from对象
 * @Modified :
 */
@ApiModel("ZpcpFirstLevelSubject查询表单")
@Data
public class ZpcpFirstLevelSubjectQueryForm extends BaseQueryForm<ZpcpFirstLevelSubjectQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "学科编号")
    private String subjecyCode;

    @ApiModelProperty(value = "学科名称")
    private String subjectName;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;
}
