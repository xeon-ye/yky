package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-04
 * @Description : PolicyNotice新增修改form对象
 * @Modified :
 */
@ApiModel("新增PolicyNotice表单")
@Data
public class PolicyNoticeForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "政策名称")
    private String noticeName;

    @ApiModelProperty(value = "政策内容")
    private String noticeIndex;

    @ApiModelProperty(value = "政策类型 1 人事政策")
    private String noticeType;

    @ApiModelProperty(value = "1 仅机关 2含二级学院")
    private String depScope;

    @ApiModelProperty(value = "1 仅部门负责人 2含教职工")
    private String posScope;

    @ApiModelProperty(value = "删除标识")
    private String isdelete;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "提交状态 1提交 0未提交")
    private String state;

    @ApiModelProperty(value = "附件名称")
    private String fileName;

    @ApiModelProperty(value = "附件存储地址")
    private String fileUrl;

    @ApiModelProperty(value = "发布月份")
    private String month;

    @ApiModelProperty(value = "发布年份")
    private String year;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentForm> attachments;

    private String empId;
}
