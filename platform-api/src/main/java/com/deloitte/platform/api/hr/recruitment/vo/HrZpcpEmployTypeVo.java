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
 * @Description : HrZpcpEmployType返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrZpcpEmployTypeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "聘用类型编码")
    private String employCoder;

    @ApiModelProperty(value = "聘用类型名称")
    private String employName;

    @ApiModelProperty(value = "单位编码")
    private String unit;

    @ApiModelProperty(value = "单位---编码")
    private String deptCode;


    @ApiModelProperty(value = "备注")
    private String remarks;

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

    @ApiModelProperty(value = "单位名称")
    private String deptName;
}
