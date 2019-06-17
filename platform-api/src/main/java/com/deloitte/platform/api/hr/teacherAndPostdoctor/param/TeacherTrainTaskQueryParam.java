package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description :  TeacherTrainTask查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherTrainTaskQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long teacherTrainId;
    private String taskOne;
    private Integer taskOneScale;
    private String taskOneAttachmentUrl;
    private BigDecimal taskOneMark;
    private String taskTwo;
    private BigDecimal taskTwoScale;
    private String taskTwoAttachmentUrl;
    private BigDecimal taskTwoMark;
    private BigDecimal tatolMark;
    private BigDecimal status;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String orgCode;
    private String taskOneTeacherId;
    private String taskTwoTeacherId;

}
