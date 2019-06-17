package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-11
 * @Description : GccReportUnit新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccReportUnit表单")
@Data
public class GccReportUnitForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "上报项目")
    private Long projectId;

    @ApiModelProperty(value = "上报项目类别")
    private Long projectCategoryId;

    @ApiModelProperty(value = "上报人编号")
    private Long userId;

    @ApiModelProperty(value = "上报人单位")
    private String userUnit;

    @ApiModelProperty(value = "上报人部门")
    private String userDept;

    @ApiModelProperty(value = "院校通知")
    private String yxNotice;

    @ApiModelProperty(value = "提交部门")
    private String sumbitDept;

    @ApiModelProperty(value = "上报时间")
    private LocalDateTime reportedTime;

    @ApiModelProperty(value = "推荐报告")
    private Long enclosure1;

    @ApiModelProperty(value = "一览表")
    private Long enclosure;

    @ApiModelProperty(value = "推荐报告")
    private HrAttachmentForm attachmentForm1;

    @ApiModelProperty(value = "一览表")
    private HrAttachmentForm attachmentForm;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "提交院校名单列表ids")
    private  Long[] ids;
}
