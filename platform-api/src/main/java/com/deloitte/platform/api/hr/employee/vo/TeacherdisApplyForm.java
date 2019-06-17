package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-05-18
 * @Description : TeacherdisApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增TeacherdisApply表单")
@Data
public class TeacherdisApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "备注")
    private String remake;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "流程编号")
    private String processNum;

    @ApiModelProperty(value = "员工基础信息表ID")
    private String empId;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "优惠号")
    private String disNum;

    @ApiModelProperty(value = "售房单位")
    private String sellingUnit;

    @ApiModelProperty(value = "申请次数")
    private String applyNum;

    @ApiModelProperty(value = "售房地址")
    private String sellingAddress;

    @ApiModelProperty("附件")
    private List<HrAttachmentForm> attachments;

    private String id;

}
