package com.deloitte.platform.api.fssc.report.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description :  ReportFinAllocIeSum查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportFinAllocIeSumQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long unitId;
    private String unitCode;
    private String name;
    private String year;
    private String month;
    private String status;
    private String periodType;
    private String mergeFlag;
    private Double incomeItem1;
    private Double incomeItem2;
    private Double incomeItem3;
    private Double incomeItem4;
    private Double incomeItem5;
    private Double incomeItem6;
    private Double incomeItem7;
    private Double expenseItem1;
    private Double expenseItem2;
    private Double expenseItem3;
    private Double expenseItem4;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;

}
