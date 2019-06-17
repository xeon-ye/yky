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
 * @Date : Create in 2019-05-13
 * @Description :  OaDzggInterfaceTempAttach查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OaDzggInterfaceTempAttachQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private String requestId;
    private String fileName;
    private String fileAddress;

}
