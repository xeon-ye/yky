package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :   ZpcpTalentProject查询from对象
 * @Modified :
 */
@ApiModel("ZpcpTalentProject查询表单")
@Data
public class ZpcpTalentProjectQueryForm extends BaseQueryForm<ZpcpTalentProjectQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    /*@ApiModelProperty(value = "人才项目名称")
    private String talentName;

    @ApiModelProperty(value = "入选时间")
    private LocalDateTime selectedTime;

    @ApiModelProperty(value = "备注")
    private String remark;*/

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "查询类型(准聘长聘申报人查询.1,审核人查看详情.0)")
    private String selectType;

    /*@ApiModelProperty(value = "组织code")
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
