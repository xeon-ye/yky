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
 * @Description : ProCategory返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProCategoryVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "${field.comment}")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "上级code")
    private String parentCode;

    @ApiModelProperty(value = "${field.comment}")
    private String remark;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long createBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long updateBy;

    @ApiModelProperty(value = "所属系统类别")
    private String sysType;

}
