package com.deloitte.platform.api.fssc.performance.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description :  PerformancePrjMainType查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformancePrjMainTypeQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String code;
    private String name;
    private String explain;
    private String validFlag;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long orgId;
    private String orgPath;

}
