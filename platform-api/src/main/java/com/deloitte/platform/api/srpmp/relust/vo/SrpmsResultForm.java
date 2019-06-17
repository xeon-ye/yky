package com.deloitte.platform.api.srpmp.relust.vo;
import java.sql.Clob;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description : SrpmsResult新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsResult表单")
@Data
public class SrpmsResultForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "成果id")
    private Long id;

    @ApiModelProperty(value = "成果入库编号")
    private String resultNum;

    @ApiModelProperty(value = "成果名称")
    private String resultName;

    @ApiModelProperty(value = "成果类型")
    private String resultType;

    @ApiModelProperty(value = "是否转化")
    private String transFlag;	

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "所属单位")
    private String deptName;

    @ApiModelProperty(value = "完成人")
    private String personName;

    @ApiModelProperty(value = "明细")
    private JSONObject detail;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "附件")
    private List<SrpmsProjectAttachmentVo> attachmentFile;

}
