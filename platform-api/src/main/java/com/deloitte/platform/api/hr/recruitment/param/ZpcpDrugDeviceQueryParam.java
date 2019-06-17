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
 * @Date : Create in 2019-04-23
 * @Description :  ZpcpDrugDevice查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZpcpDrugDeviceQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String year;
    private String drugDevice;
    private String type;
    private String approvalNumber;
    private String approvalStage;
    private String completionRanking;
    private String score;
    private Long noticeId;
    private String remark;
    private String empCode;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
