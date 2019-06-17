package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-19
 * @Description : HrZpcpCheck返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrZpcpCheckVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "申报表编号")
    private Long declareId;

    @ApiModelProperty(value = "审核小组编号")
    private Long checkGroudId;

    @ApiModelProperty(value = "申报岗位1票数")
    private Double postVote1;

    @ApiModelProperty(value = "申报岗位2票数")
    private Double postVote2;

    @ApiModelProperty(value = "推荐一级学科")
    private String recommendFirstClass;

    @ApiModelProperty(value = "推荐岗位")
    private String recommendPost;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "审核类型(1,学术委员会,2.教授聘任委员会,3教职聘任委员会)")
    private String checkType;

}
