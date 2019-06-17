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
 * @Date : Create in 2019-04-13
 * @Description :   EliminateleaveInfo查询from对象
 * @Modified :
 */
@ApiModel("EliminateleaveInfo查询表单")
@Data
public class EliminateleaveInfoQueryForm extends BaseQueryForm<EliminateleaveInfoQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "休假具体日期")
    private Long vacationdays;

    @ApiModelProperty(value = "休假类型")
    private String vacationtype;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "销假申请标识")
    private String eliId;
}
