package com.deloitte.platform.api.hr.retireRehiring.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : tankui
 * @Date : Create in 2019-05-15
 * @Description :  RetireRemind查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemindQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String remindType;
    private String content;
    private LocalDateTime remindTime;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateBy;
    private LocalDateTime updateTime;
    private String orgCode;
    private String ex1;
    private String ex2;
    private String ex3;

}
