package com.deloitte.platform.api.bpmservice.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description : BpmPositionRelationship返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BpmPositionRelationshipVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关系表ID")
    private Long id;

    @ApiModelProperty(value = "BMP虚拟岗位ID")
    private Long bpmPositionId;

    @ApiModelProperty(value = "实体岗位ID")
    private Long physicalPositionId;

    @ApiModelProperty(value = "负责组织ID")
    private Long chargeOrgId;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "生效时间")
    private LocalDateTime effectTime;

    @ApiModelProperty(value = "失效时间")
    private LocalDateTime loseEfficacyTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
