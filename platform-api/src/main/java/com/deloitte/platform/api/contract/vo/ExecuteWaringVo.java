package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : yangyq
 * @Date : Create in 2019-04-28
 * @Description : ExecuteWaring返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteWaringVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "公司编码")
    private String deptCode;

    @ApiModelProperty(value = "公司名称")
    private String deptName;

    @ApiModelProperty(value = "部门编码")
    private String orgCode;

    @ApiModelProperty(value = "公司名称")
    private String orgName;

    @ApiModelProperty(value = "履行提醒时间")
    private Double waringTime;

    @ApiModelProperty(value = "提醒频率")
    private Double waringFrequency;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

}
