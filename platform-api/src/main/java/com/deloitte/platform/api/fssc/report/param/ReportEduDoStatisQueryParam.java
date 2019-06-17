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
 * @Description :  ReportEduDoStatis查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportEduDoStatisQueryParam extends BaseParam {
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
    private Double replyAllocateItem1;
    private Double replyAllocateItem2;
    private Double replyAllocateItem3;
    private Double replyAllocateItem4;
    private Double replyAllocateItem5;
    private Double replyAllocateItem6;
    private Double replyAllocateItem7;
    private Double replyAllocateItem8;
    private Double replyAllocateItem9;
    private Double replyAllocateItem10;
    private Double carryForwardItem1;
    private Double carryForwardItem2;
    private Double carryForwardItem3;
    private Double carryForwardItem4;
    private Double carryForwardItem5;
    private Double carryForwardItem6;
    private Double carryForwardItem7;
    private Double carryForwardItem8;
    private Double carryForwardItem9;
    private Double carryForwardItem10;
    private Double addUpItem1;
    private Double addUpItem2;
    private Double addUpItem4;
    private Double addUpItem5;
    private Double addUpItem6;
    private Double addUpItem7;
    private Double addUpItem8;
    private Double addUpItem9;
    private Double addUpItem10;
    private Double addUpItem3;
    private Double addUpItem11;
    private Double addUpItem12;
    private Double addUpItem13;
    private Double addUpItem14;
    private Double addUpItem15;
    private Double addUpItem16;
    private Double addUpItem17;
    private Double addUpItem18;
    private Double addUpItem19;
    private Double addUpItem20;
    private Double processItem1;
    private Double processItem2;
    private Double processItem3;
    private Double processItem4;
    private Double processItem5;
    private Double processItem6;
    private Double processItem7;
    private Double processItem8;
    private Double processItem9;
    private Double processItem10;
    private Double processItem11;
    private Double processItem12;
    private Double processItem13;
    private Double processItem14;
    private Double processItem15;
    private Double processItem16;
    private Double processItem17;
    private Double processItem18;
    private Double processItem19;
    private Double processItem20;
    private String reasonItem1;
    private String reasonItem2;
    private String reasonItem3;
    private String reasonItem4;
    private String reasonItem5;
    private String reasonItem6;
    private String reasonItem7;
    private String reasonItem8;
    private String reasonItem9;
    private String reasonItem10;
    private String reasonItem11;
    private String reasonItem12;
    private String reasonItem13;
    private String reasonItem14;
    private String reasonItem15;
    private String reasonItem16;
    private String reasonItem17;
    private String reasonItem18;
    private String reasonItem19;
    private String reasonItem20;
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
