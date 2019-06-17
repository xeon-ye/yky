package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-11
 * @Description :   GccReportUnit查询from对象
 * @Modified :
 */
@ApiModel("GccReportUnit查询表单")
@Data
public class GccReportUnitQueryForm extends BaseQueryForm<GccReportUnitQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "上报项目")
    private Long projectId;

    @ApiModelProperty(value = "上报项目类别")
    private Long projectCategoryId;

    @ApiModelProperty(value = "上报人编号")
    private Long userId;

    @ApiModelProperty(value = "上报人姓名")
    private String  userName;

    @ApiModelProperty(value = "上报人单位")
    private String userUnit;

    @ApiModelProperty(value = "上报人部门")
    private String userDept;

    @ApiModelProperty(value = "院校通知")
    private Long yxNotice;

    @ApiModelProperty(value = "提交部门")
    private String sumbitDept;

    @ApiModelProperty(value = "上报时间")
    private LocalDateTime reportedTime;

    @ApiModelProperty(value = "推荐报告")
    private Long enclosure1;

    @ApiModelProperty(value = "一览表")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;
}
