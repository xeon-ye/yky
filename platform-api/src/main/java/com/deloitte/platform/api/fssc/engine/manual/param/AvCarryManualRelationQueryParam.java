package com.deloitte.platform.api.fssc.engine.manual.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : chenx
 * @Date : Create in 2019-05-08
 * @Description :  AvCarryManualRelation查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvCarryManualRelationQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long jeHeaderId;
    private Long carrayId;
    private LocalDateTime createTime;
    private Long createBy;

}
