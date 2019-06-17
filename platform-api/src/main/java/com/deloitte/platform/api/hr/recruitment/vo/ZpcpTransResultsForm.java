package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description : ZpcpTransResults新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpTransResults表单")
@Data
public class ZpcpTransResultsForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "科技成果转化名称")
    private String transName;

    @ApiModelProperty(value = "转化时间")
    private LocalDate time;

    @ApiModelProperty(value = "转化形式")
    private String form;

    @ApiModelProperty(value = "累积金额(万元)")
    private Double cumulativeAmount;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "所获得分数")
    private String score;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
