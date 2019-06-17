package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :  CheckAchTempateContent查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAchTempateContentQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String achTempateId;
    private String orderNumber;
    private String displayName;
    private String fieldType;
    private Long wordNumber;
    private Long columnWidth;
    private String columnTitle;
    private String isDisplay;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
