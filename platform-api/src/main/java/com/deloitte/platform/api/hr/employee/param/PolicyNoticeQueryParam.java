package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-04-04
 * @Description :  PolicyNotice查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyNoticeQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String noticeName;
    private String noticeIndex;
    private String noticeType;
    private String depScope;
    private String posScope;
    private String isdelete;
    private LocalDateTime createTime;
    private LocalDateTime updateBy;
    private String careteBy;
    private LocalDateTime updateTime;
    private String state;
    private String fileName;
    private String fileUrl;
    private String month;
    private String year;
}
