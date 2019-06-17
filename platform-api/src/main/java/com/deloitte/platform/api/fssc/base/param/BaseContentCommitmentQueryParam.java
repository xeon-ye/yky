package com.deloitte.platform.api.fssc.base.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : hjy
 * @Date : Create in 2019-03-07
 * @Description :  BaseContentCommitment查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseContentCommitmentQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String billName;
    private String validFlag;
    private String contentCommitment;
    private LocalDateTime validDate;
    private LocalDateTime invalidDate;
    private LocalDateTime createTime;
    private String createBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private Long documentTypeId;
    private Long orgId;
    private String unitCode;
    private String orgPath;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;

}
