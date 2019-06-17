package com.deloitte.platform.api.isump.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**

 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  Organization查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Long parentId;
    private String type;
    private String state;
    private Long sort;
    private String remark;
    private String reserve;
    private String version;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String parentCode;
    private String code;
    private String path;
    private String commonCreditCode;
    private List<String> ids;
    private List<String> codes;
    private String groupType;
    private String dutyperson;
    private String leader;
    private String simpleName;
    private String orgLevel;

}
