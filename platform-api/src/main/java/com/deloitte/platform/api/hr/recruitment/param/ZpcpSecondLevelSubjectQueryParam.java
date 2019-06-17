package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :  ZpcpSecondLevelSubject查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZpcpSecondLevelSubjectQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String subjectCode;
    private String subjectName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Double createBy;
    private Double updateBy;
    private Long firstSubjectId;

}
