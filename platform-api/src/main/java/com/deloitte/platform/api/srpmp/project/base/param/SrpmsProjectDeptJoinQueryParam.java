package com.deloitte.platform.api.srpmp.project.base.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : wangyanyun
 * @Date : Create in 2019-02-16
 * @Description :  SrpmsProjectDeptJoin查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectDeptJoinQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long projectId;
    private Integer sort;
    private String projectNum;
    private String joinWay;
    private String deptName;
    private String taskLeaderName;
    private String email;
    private String phone;
    private String phoneOrEmail;
    private String country;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
