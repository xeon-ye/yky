package com.deloitte.platform.api.hr.vacation.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-13
 * @Description : EliminateleaveInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增EliminateleaveInfo表单")
@Data
public class EliminateleaveInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "休假具体日期")
    private String vacationdays;

    @ApiModelProperty(value = "休假类型")
    private String vacationtype;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "销假申请标识")
    private String eliId;

    @ApiModelProperty(value = "销假选择标记")
    private String choiceFlag;

}
