package com.deloitte.platform.api.oaservice.fastway.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-17
 * @Description : FastWay返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastWayVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增长id")
    private String id;

    @ApiModelProperty(value = "图片URL")
    private String picUrl;

    @ApiModelProperty(value = "应用名称")
    private String applyName;

    @ApiModelProperty(value = "应用排序")
    private String applySort;

    @ApiModelProperty(value = "发布时间，默认系统时间")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "更新时间，默认系统时间")
    private LocalDateTime applyUpdatetime;

    @ApiModelProperty(value = "跳转的url")
    private String applyUrl;

}
