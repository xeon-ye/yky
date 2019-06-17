package com.deloitte.platform.api.hr.vacation.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-17
 * @Description :   VacationtypeExplain查询from对象
 * @Modified :
 */
@ApiModel("VacationtypeExplain查询表单")
@Data
public class VacationtypeExplainQueryForm extends BaseQueryForm<VacationtypeExplainQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "假期类型")
    private String vacationType;

    @ApiModelProperty(value = "类型说明提示")
    private String vacationExplain;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;
}
