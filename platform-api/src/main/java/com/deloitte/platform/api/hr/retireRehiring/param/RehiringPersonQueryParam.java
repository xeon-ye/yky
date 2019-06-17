package com.deloitte.platform.api.hr.retireRehiring.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :  RetireRehiringPerson查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RehiringPersonQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String empCode;
    private Long rehiringDept;
    private Long rehiringPost;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String orgCode;

}
