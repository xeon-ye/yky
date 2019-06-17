package com.deloitte.platform.api.hr.recruitment.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :   ZpcpTransResults查询from对象
 * @Modified :
 */
@ApiModel("ZpcpTransResults查询表单")
@Data
public class ZpcpTransResultsQueryForm extends BaseQueryForm<ZpcpTransResultsQueryParam>  {


    /*@ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "科技成果转化名称")
    private String transName;

    @ApiModelProperty(value = "转化时间")
    private LocalDateTime time;

    @ApiModelProperty(value = "转化形式")
    private String form;

    @ApiModelProperty(value = "累积金额(万元)")
    private Double cumulativeAmount;*/

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "selectType")
    private String selectType;

    @ApiModelProperty(value = "员工编号")
    private String empCode;
/*
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "所获得分数")
    private String score;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;*/


}
