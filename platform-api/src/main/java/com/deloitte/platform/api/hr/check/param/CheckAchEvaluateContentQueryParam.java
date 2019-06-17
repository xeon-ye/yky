package com.deloitte.platform.api.hr.check.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :  CheckAchEvaluateContent查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAchEvaluateContentQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String checkEvaluateNotifyId;
    private String achEvaluateUserId;
    private String evaluateUserId;
    private String achUserId;
    private String grade;
    private String evaluateModeId;
    private String userId;
    private String remark;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
