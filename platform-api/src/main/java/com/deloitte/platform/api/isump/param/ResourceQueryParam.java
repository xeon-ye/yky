package com.deloitte.platform.api.isump.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  Resource查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String icon;
    private String name;
    private String code;
    private String uri;
    private String perms;
    private Long parentId;
    private String levels;
    private Integer leaf;
    private Integer open;
    private String type;
    private Long sort;
    private String state;
    private String remark;
    private String reserve;
    private String version;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String sysCode;

}
