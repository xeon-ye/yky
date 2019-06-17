package com.deloitte.platform.api.fssc.engine.automatic.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : chenx
 * @Date : Create in 2019-04-26
 * @Description :  AvLogicHeadLineDic查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvLogicHeadLineDicQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String documentModule;
    private String headTable;
    private String lineTable;
    private String lineType;
    private String etx1;
    private String etx2;
    private String etx3;
    private String etx4;
    private String etx5;

}
