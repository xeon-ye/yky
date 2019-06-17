package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-05-18
 * @Description : TeacherdisApply返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherdisApplyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "备注")
    private String remake;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "流程编号")
    private String processNum;

    @ApiModelProperty(value = "员工基础信息表ID")
    private String empId;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "优惠号")
    private String disNum;

    @ApiModelProperty(value = "售房单位")
    private String sellingUnit;

    @ApiModelProperty(value = "申请次数")
    private String applyNum;

    @ApiModelProperty(value = "售房地址")
    private String sellingAddress;

}
