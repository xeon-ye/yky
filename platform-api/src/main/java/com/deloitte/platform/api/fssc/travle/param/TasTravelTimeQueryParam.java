package com.deloitte.platform.api.fssc.travle.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : hjy
 * @Date : Create in 2019-04-02
 * @Description :  TasTravelTime查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasTravelTimeQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long travelLineId;
    private LocalDateTime travelBeginTime;
    private LocalDateTime travelEdnTime;
    private  String documentType;

}
