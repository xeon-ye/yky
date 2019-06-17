package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckGroupUser返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckGroupUserCountVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考核分组id")
    private String id;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "分组类别 1评价人，2被评价人")
    private String groupType;

    @ApiModelProperty(value = "人员人数")
    private Long userCount;

}
