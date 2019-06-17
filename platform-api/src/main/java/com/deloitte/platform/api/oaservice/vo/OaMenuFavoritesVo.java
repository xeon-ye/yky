package com.deloitte.platform.api.oaservice.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-05-31
 * @Description : OaMenuFavorites返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OaMenuFavoritesVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "菜单编码")
    private String menuCode;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单地址")
    private String menuUrl;

    @ApiModelProperty(value = "父菜单id")
    private String parentId;

    @ApiModelProperty(value = "所属模块编码")
    private String moduleCode;

    @ApiModelProperty(value = "所属模块名称")
    private String moduleName;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "所属系统")
    private String systemSource;

    @ApiModelProperty(value = "排序号")
    private Double orderSort;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新人")
    private String updateBy;

    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备用字段")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    private String ext4;

    @ApiModelProperty(value = "备用字段")
    private String ext5;

}
