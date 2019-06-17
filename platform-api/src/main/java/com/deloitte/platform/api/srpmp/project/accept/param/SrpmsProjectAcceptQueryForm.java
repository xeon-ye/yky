package com.deloitte.platform.api.srpmp.project.accept.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description :   SrpmsProjectAccept查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectAccept查询表单")
@Data
public class SrpmsProjectAcceptQueryForm extends BaseQueryForm<SrpmsProjectAcceptQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "依托单位")
    private String deptName;

    @ApiModelProperty(value = "项目开始时间")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目结束时间")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "验收状态")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "单位ID")
    private String deptId;

    @ApiModelProperty(value = "显示table标识,1-PI，2-管理员")
    private int tableFlag;

    @ApiModelProperty(value = "管理员查询我的数据标识")
    private int selfDataFlag;
}
