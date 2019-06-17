package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.api.hr.common.util.DateFarmatUtil;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : 修改Postdoctor出站退站表单对象
 * @Modified :
 */
@ApiModel("修改Postdoctor出站退站表单")
@Data
public class PostdoctorPushForm {
    private static final long serialVersionUID = 1L;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "院校办理出站时间（格式:2019-04-01）")
    private LocalDate schoolPushTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "博管会出站时间（格式:2019-04-01）")
    private LocalDate committeePushTime;

    @ApiModelProperty(value = "出站接收单位")
    private String pushUnit;

    @ApiModelProperty(value = "是否颁发博士后证书（1是，2否）")
    private Integer isGraduation;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "退站时间（格式:2019-04-01）")
    private LocalDate backTime;

    @ApiModelProperty(value = "退站原因")
    private String backRemark;

    @ApiModelProperty(value = "退站档案存处")
    private String backArchivesDeposit;

    @ApiModelProperty(value = "户口是否已迁出（1是，2否）")
    private Integer postdoctorIsTransfer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "户口迁出时间（格式:2019-04-01）")
    private LocalDate postdoctorTransferTime;

    @ApiModelProperty(value = "迁出地")
    private String postdoctorTransferAddress;

    @ApiModelProperty(value = "备注论文")
    private String pushRemark;

    @ApiModelProperty(value = "出站附件URL")
    private String attachmentPushUrl;

    @ApiModelProperty(value = "出站附件名称")
    private String pushFileName;

    @ApiModelProperty(value = "退站附件URL")
    private String attachmentBackUrl;

    @ApiModelProperty(value = "退站附件名称")
    private String backFileName;

}
