package com.deloitte.platform.api.srpmp.relust.param;
import java.time.LocalDateTime;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description :   SrpmsResult查询from对象
 * @Modified :
 */
@ApiModel("SrpmsResult查询表单")	
@Data
public class SrpmsResultQueryForm extends BaseQueryForm<SrpmsResultQueryParam>  {


    @ApiModelProperty(value = "成果id")
    private Long id;

    @ApiModelProperty(value = "成果入库编号")
    private String resultNum;

    @ApiModelProperty(value = "成果名称")
    private String resultName;

    @ApiModelProperty(value = "成果类型")
    private String resultType;

    @ApiModelProperty(value = "是否转化")
    private String transFlag;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "所属单位")
    private String deptName;

    @ApiModelProperty(value = "完成人")
    private String personName;

    @ApiModelProperty(value = "明细")
    private String detail;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "单位CODE")
    private String deptCode;

    @ApiModelProperty(value = "显示table标识,1-PI，2-管理员")
    private int tableFlag;

    @ApiModelProperty(value = "管理员查询我的数据标识")
    private int selfDataFlag;
}
