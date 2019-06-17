package com.deloitte.platform.api.srpmp.relust.vo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description : SrpmsResultTrans新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsResultTrans表单")
@Data
public class SrpmsResultTransForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "成果转化id")
    private Long id;

    @ApiModelProperty(value = "成果id")
    private Long resultId;

    @ApiModelProperty(value = "成果名称")
    private String resultName;

    @ApiModelProperty(value = "成果转化名称")
    private String resultTransName;	

    @ApiModelProperty(value = "转化方式")
    private String transWay;

    @ApiModelProperty(value = "合同号")
    private String contractNum;

    @ApiModelProperty(value = "合同金额")
    private Long contractAmount;

    @ApiModelProperty(value = "合同签订日")
    private String contractSigningDay;

    @ApiModelProperty(value = "转化费来源")
    private String transFeeSource;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "附件")
    private List<SrpmsProjectAttachmentVo> attachmentFile;

}
