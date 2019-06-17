package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccThesis新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccThesis表单")
@Data
public class GccThesisForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "作者编号")
    private Long userId;

    @ApiModelProperty(value = "作者姓名")
    private String userName;

    @ApiModelProperty(value = "题目")
    private String thesisTitle;

    @ApiModelProperty(value = "发表时间")
    private LocalDateTime publishTime;

    @ApiModelProperty(value = "发表刊物")
    private String publishJournals;

    @ApiModelProperty(value = "是否SCI收录")
    private String inSci;

    @ApiModelProperty(value = "期刊影响因子")
    private String journalFactor;

    @ApiModelProperty(value = "他引次数")
    private Long leadTimes;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForm;

}
