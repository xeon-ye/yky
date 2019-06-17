package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description : HrZpcpWorkgroup返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrZpcpWorkgroupVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "聘任年份")
    private String employYear;

    @ApiModelProperty(value = "小组名称")
    private String groupName;

    @ApiModelProperty(value = "所属单位编号")
    private String unit;

    @ApiModelProperty(value = "所属单位编号")
    private String deptCode;

    @ApiModelProperty(value = "所属单位名称")
    private String deptName;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "授权经办人")
    private String authorizedOperator;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "1，任聘工作组、2，学术委员会、3，教授委员会、4，教职委员会")
    private String groupType;

    @ApiModelProperty(value = "是否有效：0表示无效，1表示 有效")
    private String status;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "修改人id")
    private String updateBy;


}
