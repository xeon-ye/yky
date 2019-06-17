package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : tankui
 * @Date : Create in 2019-04-19
 * @Description :  HrZpcpCheck查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrZpcpCheckQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long declareId;
    private String declareName;
    private Long checkGroudId;
    private Double postVote1;
    private Double postVote2;
    private String recommendFirstClass;
    private String recommendPost;
    private String checkStatus;
    private String organizationCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private String auditOpinion;
    private String checkType;

}
