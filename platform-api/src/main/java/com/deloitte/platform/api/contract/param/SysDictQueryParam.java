package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysDict查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDictQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String type;
    private String code;
    private String value;
    private Long parentCode;
    private LocalDateTime activeDate;
    private LocalDateTime expiredDate;
    private String isUesd;
    private String describe;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String spareField1;
    private String spareField2;
    private String spareField3;
    private LocalDateTime spareField4;
    private Long spareField5;

}
