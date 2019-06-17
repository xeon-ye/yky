package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :  CheckAchUserContent查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAchUserContentQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String checkAchUserId;
    private String quotaContent;
    private String quotaDescribe;
    private String evaluateStandard;
    private String finishStatus;
    private String evaluateOpinion;
    private String finishDate;
    private String workStandard;
    private String selfRating;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
