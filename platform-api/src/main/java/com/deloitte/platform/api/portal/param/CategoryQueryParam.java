package com.deloitte.platform.api.portal.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : pengchao
 * @Date : Create in 2019-04-03
 * @Description :  Category查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long categoryId;
    private String categoryTitle;
    private String categoryKey;
    private Long categorySort;
    private LocalDateTime categoryDatetime;
    private String categoryDescription;
    private String delFlg;

}
