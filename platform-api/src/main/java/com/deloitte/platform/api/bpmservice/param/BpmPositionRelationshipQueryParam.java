package com.deloitte.platform.api.bpmservice.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description :  BpmPositionRelationship查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BpmPositionRelationshipQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long bpmPositionId;
    private Long physicalPositionId;
    private Long chargeOrgId;
    private String state;
    private LocalDateTime effectTime;
    private LocalDateTime loseEfficacyTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
