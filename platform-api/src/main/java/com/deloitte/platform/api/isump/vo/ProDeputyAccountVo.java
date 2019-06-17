package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description : ProDeputyAccount返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProDeputyAccountVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "${field.comment}")
    private Long deputyAccountId;

    @ApiModelProperty(value = "${field.comment}")
    private Long proCategoryId;

}
