package com.deloitte.platform.api.hr.common.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jianglong
 * @Date : Create in 2019-04-18
 * @Description :  Organization查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrOrganizationQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String code;
    private String parentCode;
    private String path;
    private Long parentId;
    private String type;
    private String state;
    private Long sort;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String remark;
    private String reserve;
    private String version;
    private String commonCreditCode;

}
