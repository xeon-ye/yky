package com.deloitte.platform.api.fssc.report.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description : ReportEduDoStatis新增修改form对象
 * @Modified :
 */
@ApiModel("新增ReportEduDoStatis表单")
@Data
public class ReportEduDoStatisForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "年份")
    @NotBlank(message = "年份不能为空")
    private String year;

    @ApiModelProperty(value = "月份")
    @NotBlank(message = "月份不能为空")
    private String month;

    @ApiModelProperty(value = "报表状态")
    private String status;

    @ApiModelProperty(value = "周期类型(Y:年度，M:月)")
    private String periodType;

    @ApiModelProperty(value = "是否多个单位合并")
    private String mergeFlag;

    @ApiModelProperty(value = "批复/下拨数-本年拨款")
    private BigDecimal replyAllocateItem1;

    @ApiModelProperty(value = "批复/下拨数-基础支出")
    private BigDecimal replyAllocateItem2;

    @ApiModelProperty(value = "批复/下拨数-其中:人员经费")
    private BigDecimal replyAllocateItem3;

    @ApiModelProperty(value = "批复/下拨数-公共经费")
    private BigDecimal replyAllocateItem4;

    @ApiModelProperty(value = "批复/下拨数-项目支出")
    private BigDecimal replyAllocateItem5;

    @ApiModelProperty(value = "批复/下拨数-其中:改善基本办学条件专项资金(教育修购)")
    private BigDecimal replyAllocateItem6;

    @ApiModelProperty(value = "批复/下拨数-高校基本科研业务费")
    private BigDecimal replyAllocateItem7;

    @ApiModelProperty(value = "批复/下拨数-高校管理改革等绩效拨款")
    private BigDecimal replyAllocateItem8;

    @ApiModelProperty(value = "批复/下拨数-高校建设世界一流大学(学科)专项资金")
    private BigDecimal replyAllocateItem9;

    @ApiModelProperty(value = "批复/下拨数-教育教学改革专项资金")
    private BigDecimal replyAllocateItem10;

    @ApiModelProperty(value = "截止上年底结转结余数-以前年度结转结余资金")
    private BigDecimal carryForwardItem1;

    @ApiModelProperty(value = "截止上年底结转结余数-基本支出")
    private BigDecimal carryForwardItem2;

    @ApiModelProperty(value = "截止上年底结转结余数-其中:人员经费")
    private BigDecimal carryForwardItem3;

    @ApiModelProperty(value = "截止上年底结转结余数-公用经费")
    private BigDecimal carryForwardItem4;

    @ApiModelProperty(value = "截止上年底结转结余数-项目支出")
    private BigDecimal carryForwardItem5;

    @ApiModelProperty(value = "截止上年底结转结余数-其中:改善基本办学条件专项资金(教育修购)")
    private BigDecimal carryForwardItem6;

    @ApiModelProperty(value = "截止上年底结转结余数-高校基本科研业务费")
    private BigDecimal carryForwardItem7;

    @ApiModelProperty(value = "截止上年底结转结余数-高校管理改革等绩效拨款")
    private BigDecimal carryForwardItem8;

    @ApiModelProperty(value = "截止上年底结转结余数-高校建设世界一流大学(学科)专项资金")
    private BigDecimal carryForwardItem9;

    @ApiModelProperty(value = "截止上年底结转结余数-教育教学改革专项资金")
    private BigDecimal carryForwardItem10;

    @ApiModelProperty(value = "累计支出金额-以前年度结转结余资金")
    private BigDecimal addUpItem1;

    @ApiModelProperty(value = "累计支出金额-结转-基本支出")
    private BigDecimal addUpItem2;

    @ApiModelProperty(value = "累计支出金额-结转--公用经费")
    private BigDecimal addUpItem4;

    @ApiModelProperty(value = "累计支出金额-结转--项目支出")
    private BigDecimal addUpItem5;

    @ApiModelProperty(value = "累计支出金额-结转--其中:改善基本办学条件专项资金(教育修购)")
    private BigDecimal addUpItem6;

    @ApiModelProperty(value = "累计支出金额-结转--高校基本科研业务费")
    private BigDecimal addUpItem7;

    @ApiModelProperty(value = "累计支出金额-结转--高校管理改革等绩效拨款")
    private BigDecimal addUpItem8;

    @ApiModelProperty(value = "累计支出金额-结转--高校建设世界一流大学(学科)专项资金")
    private BigDecimal addUpItem9;

    @ApiModelProperty(value = "累计支出金额-结转--教育教学改革专项资金")
    private BigDecimal addUpItem10;

    @ApiModelProperty(value = "累计支出金额-结转-其中:人员经费")
    private BigDecimal addUpItem3;

    @ApiModelProperty(value = "累计支出金额-本年拨款")
    private BigDecimal addUpItem11;

    @ApiModelProperty(value = "累计支出金额-拨款-基本支出")
    private BigDecimal addUpItem12;

    @ApiModelProperty(value = "累计支出金额-拨款-其中:人员经费")
    private BigDecimal addUpItem13;

    @ApiModelProperty(value = "累计支出金额-拨款--公用经费")
    private BigDecimal addUpItem14;

    @ApiModelProperty(value = "累计支出金额-拨款--项目支出")
    private BigDecimal addUpItem15;

    @ApiModelProperty(value = "累计支出金额-拨款--其中:改善基本办学条件专项资金(教育修购)")
    private BigDecimal addUpItem16;

    @ApiModelProperty(value = "累计支出金额-拨款--高校基本科研业务费")
    private BigDecimal addUpItem17;

    @ApiModelProperty(value = "累计支出金额-拨款--高校管理改革等绩效拨款")
    private BigDecimal addUpItem18;

    @ApiModelProperty(value = "累计支出金额-拨款--高校建设世界一流大学(学科)专项资金")
    private BigDecimal addUpItem19;

    @ApiModelProperty(value = "累计支出金额-拨款--教育教学改革专项资金")
    private BigDecimal addUpItem20;

    @ApiModelProperty(value = "执行进度-以前年度结转结余资金")
    private BigDecimal processItem1;

    @ApiModelProperty(value = "执行进度-结转--基本支出")
    private BigDecimal processItem2;

    @ApiModelProperty(value = "执行进度-结转--公用经费")
    private BigDecimal processItem3;

    @ApiModelProperty(value = "执行进度-结转--项目支出")
    private BigDecimal processItem4;

    @ApiModelProperty(value = "执行进度-结转--其中:改善基本办学条件专项资金(教育修购)")
    private BigDecimal processItem5;

    @ApiModelProperty(value = "执行进度-结转--高校基本科研业务费")
    private BigDecimal processItem6;

    @ApiModelProperty(value = "执行进度-结转--高校管理改革等绩效拨款")
    private BigDecimal processItem7;

    @ApiModelProperty(value = "执行进度-结转--高校建设世界一流大学(学科)专项资金")
    private BigDecimal processItem8;

    @ApiModelProperty(value = "执行进度-结转--教育教学改革专项资金")
    private BigDecimal processItem9;

    @ApiModelProperty(value = "执行进度-结转-其中:人员经费")
    private BigDecimal processItem10;

    @ApiModelProperty(value = "执行进度-本年拨款")
    private BigDecimal processItem11;

    @ApiModelProperty(value = "执行进度-拨款-基本支出")
    private BigDecimal processItem12;

    @ApiModelProperty(value = "执行进度-拨款-其中:人员经费")
    private BigDecimal processItem13;

    @ApiModelProperty(value = "执行进度-拨款--公用经费")
    private BigDecimal processItem14;

    @ApiModelProperty(value = "执行进度-拨款--项目支出")
    private BigDecimal processItem15;

    @ApiModelProperty(value = "执行进度-拨款--其中:改善基本办学条件专项资金(教育修购)")
    private BigDecimal processItem16;

    @ApiModelProperty(value = "执行进度-拨款--高校基本科研业务费")
    private BigDecimal processItem17;

    @ApiModelProperty(value = "执行进度-拨款--高校管理改革等绩效拨款")
    private BigDecimal processItem18;

    @ApiModelProperty(value = "执行进度-拨款--高校建设世界一流大学(学科)专项资金")
    private BigDecimal processItem19;

    @ApiModelProperty(value = "执行进度-拨款--教育教学改革专项资金")
    private BigDecimal processItem20;

    @ApiModelProperty(value = "未执行原因-以前年度结转结余资金")
    private String reasonItem1;

    @ApiModelProperty(value = "未执行原因-结转--基本支出")
    private String reasonItem2;

    @ApiModelProperty(value = "未执行原因-结转--公用经费")
    private String reasonItem3;

    @ApiModelProperty(value = "未执行原因-结转--项目支出")
    private String reasonItem4;

    @ApiModelProperty(value = "未执行原因-结转--其中:改善基本办学条件专项资金(教育修购)")
    private String reasonItem5;

    @ApiModelProperty(value = "未执行原因-结转--高校基本科研业务费")
    private String reasonItem6;

    @ApiModelProperty(value = "未执行原因-结转--高校管理改革等绩效拨款")
    private String reasonItem7;

    @ApiModelProperty(value = "未执行原因-结转--高校建设世界一流大学(学科)专项资金")
    private String reasonItem8;

    @ApiModelProperty(value = "未执行原因-结转--教育教学改革专项资金")
    private String reasonItem9;

    @ApiModelProperty(value = "未执行原因-结转-其中:人员经费")
    private String reasonItem10;

    @ApiModelProperty(value = "未执行原因-本年拨款")
    private String reasonItem11;

    @ApiModelProperty(value = "未执行原因-拨款-基本支出")
    private String reasonItem12;

    @ApiModelProperty(value = "未执行原因-拨款-其中:人员经费")
    private String reasonItem13;

    @ApiModelProperty(value = "未执行原因-拨款--公用经费")
    private String reasonItem14;

    @ApiModelProperty(value = "未执行原因-拨款--项目支出")
    private String reasonItem15;

    @ApiModelProperty(value = "未执行原因-拨款--其中:改善基本办学条件专项资金(教育修购)")
    private String reasonItem16;

    @ApiModelProperty(value = "未执行原因-拨款--高校基本科研业务费")
    private String reasonItem17;

    @ApiModelProperty(value = "未执行原因-拨款--高校管理改革等绩效拨款")
    private String reasonItem18;

    @ApiModelProperty(value = "未执行原因-拨款--高校建设世界一流大学(学科)专项资金")
    private String reasonItem19;

    @ApiModelProperty(value = "未执行原因-拨款--教育教学改革专项资金")
    private String reasonItem20;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    private String ext5;

}
