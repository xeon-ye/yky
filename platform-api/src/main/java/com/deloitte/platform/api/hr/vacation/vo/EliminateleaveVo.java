package com.deloitte.platform.api.hr.vacation.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-01
 * @Description : Eliminateleave返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EliminateleaveVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "请假申请表ID")
    private String vacationTrainId;

    @ApiModelProperty(value = "销假状态")
    private String states;

    @ApiModelProperty(value = "销假说明")
    private String eliminateleaveIllu;

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

    @ApiModelProperty(value = "休假日期")
    private String vacationDays;

    @ApiModelProperty(value = "休假类型")
    private String vacationType;

    @ApiModelProperty(value = "申请状态")
    private String vacationState;

    @ApiModelProperty(value = "销假详情明细")
    private List<EliminateleaveInfoVo> eliminateleaveInfoVos;
}
