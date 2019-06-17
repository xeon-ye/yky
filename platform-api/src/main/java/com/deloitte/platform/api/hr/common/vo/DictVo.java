package com.deloitte.platform.api.hr.common.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : Dict返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "上级编码")
    private String parentCode;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "状态")
    private String state;

}
