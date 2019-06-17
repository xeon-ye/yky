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
 * @Date : Create in 2019-04-19
 * @Description :  ZpcpCheckNumber查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZpcpCheckNumberQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long noticeId;
    private Long checkGroupId;
    private Long shouldNumber;
    private String trueNumber;
    private String organizationCode;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String createTime;
    private String createBy;
    private String checkType;

}
