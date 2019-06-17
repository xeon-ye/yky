package com.deloitte.platform.api.portal.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-03
 * @Description : Category返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "栏目ID")
    private Long categoryId;

    @ApiModelProperty(value = "栏目标题")
    private String categoryTitle;

    @ApiModelProperty(value = "栏目关键字")
    private String categoryKey;

    @ApiModelProperty(value = "栏目排序")
    private Long categorySort;

    @ApiModelProperty(value = "栏目发布时间")
    private LocalDateTime categoryDatetime;

    @ApiModelProperty(value = "栏目描述")
    private String categoryDescription;

    @ApiModelProperty(value = "删除状态")
    private String delFlg;

}
