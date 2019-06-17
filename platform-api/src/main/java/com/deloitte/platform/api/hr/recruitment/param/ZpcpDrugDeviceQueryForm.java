package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :   ZpcpDrugDevice查询from对象
 * @Modified :
 */
@ApiModel("ZpcpDrugDevice查询表单")
@Data
public class ZpcpDrugDeviceQueryForm extends BaseQueryForm<ZpcpDrugDeviceQueryParam>  {


    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "查询类型(准聘长聘申报人查询.1,审核人查看详情.0)")
    private String selectType;

    @ApiModelProperty(value = "员工编号")
    private String empCode;


    /*@ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "原创新药和医疗器械")
    private String drugDevice;

    @ApiModelProperty(value = "类别")
    private String type;

    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    @ApiModelProperty(value = "审批阶段")
    private String approvalStage;

    @ApiModelProperty(value = "完成情况排名")
    private String completionRanking;

    @ApiModelProperty(value = "所获分数")
    private String score;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

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
