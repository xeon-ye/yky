package com.deloitte.platform.api.srpmp.common.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : pengchao
 * @Date : Create in 2019-02-20
 * @Description :  SysDict查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDictQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String dictCode;
    private String dictValue;
    private Long dictParent;
    private LocalDateTime activeDate;
    private LocalDateTime expiredDate;
    private Integer isExpired;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
