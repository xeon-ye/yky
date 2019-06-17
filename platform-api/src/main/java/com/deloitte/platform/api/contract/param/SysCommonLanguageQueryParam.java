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
 * @Description :  SysCommonLanguage查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysCommonLanguageQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String applicationCode;
    private String application;
    private String context;
    private String belongPerson;
    private LocalDateTime createTime;
    private String createBy;
    private String isUsed;
    private String spareField1;
    private String spareField2;
    private String spareField3;
    private LocalDateTime spareField4;
    private Long spareField5;

}
