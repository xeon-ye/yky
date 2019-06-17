package com.deloitte.platform.api.fssc.dic.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description :  Dic查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DicQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String eumCode;
    private String eumName;
    private String eumDesciption;
    private Double eumOrder;
    private String eumConcatTab;
    private String isValid;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createBy;
    private Long updateBy;

}
