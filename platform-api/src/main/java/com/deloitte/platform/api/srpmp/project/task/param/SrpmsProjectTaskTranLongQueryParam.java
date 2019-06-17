package com.deloitte.platform.api.srpmp.project.task.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : Apeng
 * @Date : Create in 2019-04-17
 * @Description :  SrpmsProjectTaskTranLong查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectTaskTranLongQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectName;
    private String projectResultType;
    private String belongDomain;
    private String taskDecompositionInstruction;
    private String joinPersonInstruction;
    private String budgetInstruction;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
