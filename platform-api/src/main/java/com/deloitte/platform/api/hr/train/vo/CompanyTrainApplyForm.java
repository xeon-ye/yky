package com.deloitte.platform.api.hr.train.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-02
 * @Description : CompanyTrainApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增CompanyTrainApply表单")
@Data
public class CompanyTrainApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机构培训表主键ID")
    private String id;

    @ApiModelProperty(value = "培训申请单位/部门名称")
    private String companyDep;

    @ApiModelProperty(value = "培训班名称")
    private String trainClassName;

    @ApiModelProperty(value = "培训内容")
    private String trainContent;

    @ApiModelProperty(value = "培训对象")
    private String trainObject;

    @ApiModelProperty(value = "期数")
    private String stage;

    @ApiModelProperty(value = "人数/期")
    private String numbers;

    @ApiModelProperty(value = "举办地点")
    private String address;

    @ApiModelProperty(value = "天数/期")
    private String days;

    @ApiModelProperty(value = "主办部门")
    private String sponDep;

    @ApiModelProperty(value = "协力部门")
    private String assistDep;

    @ApiModelProperty(value = "经费")
    private Long funds;

    @ApiModelProperty(value = "列支渠道")
    private String channel;

    @ApiModelProperty(value = "是否公开（1公开, 2不公开）")
    private String isopen;

    @ApiModelProperty(value = "备注")
    private String remake;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCoode;

    @ApiModelProperty(value = "申请单位名称")
    private String trainDeptname;

    @ApiModelProperty(value = "1 单位 2 部门")
    private String trainType;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentForm> attachments;

    @ApiModelProperty(value = "状态（0 保存，1 提交）")
    private String states;

    @ApiModelProperty(value = "发起申请员的员工---工表ID")
    private String empId;

}
