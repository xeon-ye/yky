package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : yangyq
 * @Date : Create in 2019-04-23
 * @Description :  BasicConfigura查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicConfiguraQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String contractCode;
    private String basicFlag;
    private String contractFlag;
    private String executFlag;
    private String financeFlag;
    private String orderFlag;
    private String projectFlag;
    private String monitorFlag;
    private String ticketFlag;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
