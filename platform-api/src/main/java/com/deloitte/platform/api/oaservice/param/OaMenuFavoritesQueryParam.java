package com.deloitte.platform.api.oaservice.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : fuqiao
 * @Date : Create in 2019-05-31
 * @Description :  OaMenuFavorites查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OaMenuFavoritesQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private String id;
    private String menuCode;
    private String menuName;
    private String menuUrl;
    private String parentId;
    private String moduleCode;
    private String moduleName;
    private String icon;
    private String systemSource;
    private Double orderSort;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;

}
