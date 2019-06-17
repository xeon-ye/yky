package com.deloitte.platform.api.hr.vacation.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-15
 * @Description : VacationTrainCount新增修改form对象
 * @Modified :
 */
@ApiModel("新增VacationTrainCount表单")
@Data
public class VacationTrainCountForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "员工基础信息表ID")
    private String empId;

    @ApiModelProperty(value = "事假")
    private String matterLeave;

    @ApiModelProperty(value = "病假")
    private String illnessLeave;

    @ApiModelProperty(value = "婚假")
    private String marriageLeave;

    @ApiModelProperty(value = "产假")
    private String maternityLeave;

    @ApiModelProperty(value = "丧假")
    private String funeralLeave;

    @ApiModelProperty(value = "探亲假")
    private String visitfamilyLeave;

    @ApiModelProperty(value = "其他假")
    private String otherLeave;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

}
