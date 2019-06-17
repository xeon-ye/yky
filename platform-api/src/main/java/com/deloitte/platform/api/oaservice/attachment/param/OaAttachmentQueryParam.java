package com.deloitte.platform.api.oaservice.attachment.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jianghaixun
 * @Date : Create in 2019-04-15
 * @Description :  OaAttachment查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OaAttachmentQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String busicessId;
    private String busicessName;
    private String attachName;
    private String attachUrl;
    private String delFlag;
    private String attachPath;
    private LocalDateTime applyDatetime;
    private String fileId;
}
