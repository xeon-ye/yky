package com.deloitte.platform.api.oaservice.notice.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jianghaixun
 * @Date : Create in 2019-04-16
 * @Description :  OaDzggInterfaceTemp查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OaDzggInterfaceTempQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    private String requestId;
    private String noticeNo;
    private LocalDateTime applyDate;
    private String typeName;
    private String sortName;
    private Integer isRead;
    private String noticeTitle;
    private String noticeContent;
    private String noticeSrc;
    private String applyOrgCode;
    private String applyOrgName;
    private String applyUserEmpno;
    private String applyUserName;

}
