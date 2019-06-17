package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-05-28
 * @Description :  SubactBak查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubactBakQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String subactId;
    private String actId;
    private String subact;
    private String subactAbs;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String reviewAmount;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long orgId;
    private String orgPath;
    private String isRelated;
    private String quantityFrenquence;
    private String subExpense;
    private String priceStandard;
    private String actPlan;
    private String remarks;
    private String actName;
    private String actAbs;

}
