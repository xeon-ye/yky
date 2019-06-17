package com.deloitte.platform.api.oaservice.attachment.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-05-13
 * @Description : OaDzggInterfaceTempAttach返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OaDzggInterfaceTempAttachVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流程实例id")
    private String requestId;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件地址")
    private String fileAddress;

}
