package com.deloitte.platform.api.srpmp.common.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-20
 * @Description : SysDict返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDictVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private String id;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "字典值")
    private String dictValue;

    @ApiModelProperty(value = "父级字典编码，顶级为NULL")
    private Long dictParent;

    @ApiModelProperty(value = "字典生效日期")
    private LocalDateTime activeDate;

    @ApiModelProperty(value = "字典失效日期")
    private LocalDateTime expiredDate;

    @ApiModelProperty(value = "是否过期，0正常 1过期")
    private Integer isExpired;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
