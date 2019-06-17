package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :   ZpcpTeachPerformance查询from对象
 * @Modified :
 */
@ApiModel("ZpcpTeachPerformance查询表单")
@Data
public class ZpcpTeachPerformanceQueryForm extends BaseQueryForm<ZpcpTeachPerformanceQueryParam>  {


    /*@ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "教学业绩名称")
    private String teachName;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "级别")
    private String grade;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

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

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "selectType")
    private String selectType;

    @ApiModelProperty(value = "员工编号")
    private String empCode;
}
