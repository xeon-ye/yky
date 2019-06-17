package com.deloitte.platform.api.hr.vacation.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-01
 * @Description : Eliminateleave新增修改form对象
 * @Modified :
 */
@ApiModel("新增Eliminateleave表单")
@Data
public class EliminateleaveForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "销假申请表ID")
    private String id;

    @ApiModelProperty(value = "请假申请表ID")
    private String vacationTrainId;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "销假说明")
    private String eliminateleaveIllu;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "销假详情")
    private List<EliminateleaveInfoForm> eliminateleaveInfoForms;

    @ApiModelProperty(value = "员工表ID")
    private String empId;

    @ApiModelProperty(value = "流程单号")
    @TableField("PROCESS_NUM")
    private String processNum;
}
