package com.deloitte.platform.api.hr.vacation.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-13
 * @Description : EliminateleaveInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EliminateleaveInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "休假具体日期")
    private String vacationdays;

    @ApiModelProperty(value = "休假类型")
    private String vacationtype;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "销假申请标识")
    private String eliId;

    @ApiModelProperty(value = "申请状态")
    private String vacationState;

    @ApiModelProperty(value = "销假选择标记")
    private String choiceFlag;

    @ApiModelProperty(value = "销假详情类型")
    private String state;

}
