package com.deloitte.services.fssc.report.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 教育经费预算执行情况统计表
 * </p>
 *
 * @author jaws
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("REPORT_EDU_DO_STATIS")
@ApiModel(value="ReportEduDoStatis对象", description="教育经费预算执行情况统计表")
public class ReportEduDoStatis extends Model<ReportEduDoStatis> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单位ID")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "年份")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "月份")
    @TableField("MONTH")
    private String month;

    @ApiModelProperty(value = "报表状态")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "周期类型(Y:年度，M:月)")
    @TableField("PERIOD_TYPE")
    private String periodType;

    @ApiModelProperty(value = "是否多个单位合并")
    @TableField("MERGE_FLAG")
    private String mergeFlag;

    @ApiModelProperty(value = "批复/下拨数-本年拨款")
    @TableField("REPLY_ALLOCATE_ITEM_1")
    private Double replyAllocateItem1;

    @ApiModelProperty(value = "批复/下拨数-基础支出")
    @TableField("REPLY_ALLOCATE_ITEM_2")
    private Double replyAllocateItem2;

    @ApiModelProperty(value = "批复/下拨数-其中:人员经费")
    @TableField("REPLY_ALLOCATE_ITEM_3")
    private Double replyAllocateItem3;

    @ApiModelProperty(value = "批复/下拨数-公共经费")
    @TableField("REPLY_ALLOCATE_ITEM_4")
    private Double replyAllocateItem4;

    @ApiModelProperty(value = "批复/下拨数-项目支出")
    @TableField("REPLY_ALLOCATE_ITEM_5")
    private Double replyAllocateItem5;

    @ApiModelProperty(value = "批复/下拨数-其中:改善基本办学条件专项资金(教育修购)")
    @TableField("REPLY_ALLOCATE_ITEM_6")
    private Double replyAllocateItem6;

    @ApiModelProperty(value = "批复/下拨数-高校基本科研业务费")
    @TableField("REPLY_ALLOCATE_ITEM_7")
    private Double replyAllocateItem7;

    @ApiModelProperty(value = "批复/下拨数-高校管理改革等绩效拨款")
    @TableField("REPLY_ALLOCATE_ITEM_8")
    private Double replyAllocateItem8;

    @ApiModelProperty(value = "批复/下拨数-高校建设世界一流大学(学科)专项资金")
    @TableField("REPLY_ALLOCATE_ITEM_9")
    private Double replyAllocateItem9;

    @ApiModelProperty(value = "批复/下拨数-教育教学改革专项资金")
    @TableField("REPLY_ALLOCATE_ITEM_10")
    private Double replyAllocateItem10;

    @ApiModelProperty(value = "截止上年底结转结余数-以前年度结转结余资金")
    @TableField("CARRY_FORWARD_ITEM_1")
    private Double carryForwardItem1;

    @ApiModelProperty(value = "截止上年底结转结余数-基本支出")
    @TableField("CARRY_FORWARD_ITEM_2")
    private Double carryForwardItem2;

    @ApiModelProperty(value = "截止上年底结转结余数-其中:人员经费")
    @TableField("CARRY_FORWARD_ITEM_3")
    private Double carryForwardItem3;

    @ApiModelProperty(value = "截止上年底结转结余数-公用经费")
    @TableField("CARRY_FORWARD_ITEM_4")
    private Double carryForwardItem4;

    @ApiModelProperty(value = "截止上年底结转结余数-项目支出")
    @TableField("CARRY_FORWARD_ITEM_5")
    private Double carryForwardItem5;

    @ApiModelProperty(value = "截止上年底结转结余数-其中:改善基本办学条件专项资金(教育修购)")
    @TableField("CARRY_FORWARD_ITEM_6")
    private Double carryForwardItem6;

    @ApiModelProperty(value = "截止上年底结转结余数-高校基本科研业务费")
    @TableField("CARRY_FORWARD_ITEM_7")
    private Double carryForwardItem7;

    @ApiModelProperty(value = "截止上年底结转结余数-高校管理改革等绩效拨款")
    @TableField("CARRY_FORWARD_ITEM_8")
    private Double carryForwardItem8;

    @ApiModelProperty(value = "截止上年底结转结余数-高校建设世界一流大学(学科)专项资金")
    @TableField("CARRY_FORWARD_ITEM_9")
    private Double carryForwardItem9;

    @ApiModelProperty(value = "截止上年底结转结余数-教育教学改革专项资金")
    @TableField("CARRY_FORWARD_ITEM_10")
    private Double carryForwardItem10;

    @ApiModelProperty(value = "累计支出金额-以前年度结转结余资金")
    @TableField("ADD_UP_ITEM_1")
    private Double addUpItem1;

    @ApiModelProperty(value = "累计支出金额-结转-基本支出")
    @TableField("ADD_UP_ITEM_2")
    private Double addUpItem2;

    @ApiModelProperty(value = "累计支出金额-结转--公用经费")
    @TableField("ADD_UP_ITEM_4")
    private Double addUpItem4;

    @ApiModelProperty(value = "累计支出金额-结转--项目支出")
    @TableField("ADD_UP_ITEM_5")
    private Double addUpItem5;

    @ApiModelProperty(value = "累计支出金额-结转--其中:改善基本办学条件专项资金(教育修购)")
    @TableField("ADD_UP_ITEM_6")
    private Double addUpItem6;

    @ApiModelProperty(value = "累计支出金额-结转--高校基本科研业务费")
    @TableField("ADD_UP_ITEM_7")
    private Double addUpItem7;

    @ApiModelProperty(value = "累计支出金额-结转--高校管理改革等绩效拨款")
    @TableField("ADD_UP_ITEM_8")
    private Double addUpItem8;

    @ApiModelProperty(value = "累计支出金额-结转--高校建设世界一流大学(学科)专项资金")
    @TableField("ADD_UP_ITEM_9")
    private Double addUpItem9;

    @ApiModelProperty(value = "累计支出金额-结转--教育教学改革专项资金")
    @TableField("ADD_UP_ITEM_10")
    private Double addUpItem10;

    @ApiModelProperty(value = "累计支出金额-结转-其中:人员经费")
    @TableField("ADD_UP_ITEM_3")
    private Double addUpItem3;

    @ApiModelProperty(value = "累计支出金额-本年拨款")
    @TableField("ADD_UP_ITEM_11")
    private Double addUpItem11;

    @ApiModelProperty(value = "累计支出金额-拨款-基本支出")
    @TableField("ADD_UP_ITEM_12")
    private Double addUpItem12;

    @ApiModelProperty(value = "累计支出金额-拨款-其中:人员经费")
    @TableField("ADD_UP_ITEM_13")
    private Double addUpItem13;

    @ApiModelProperty(value = "累计支出金额-拨款--公用经费")
    @TableField("ADD_UP_ITEM_14")
    private Double addUpItem14;

    @ApiModelProperty(value = "累计支出金额-拨款--项目支出")
    @TableField("ADD_UP_ITEM_15")
    private Double addUpItem15;

    @ApiModelProperty(value = "累计支出金额-拨款--其中:改善基本办学条件专项资金(教育修购)")
    @TableField("ADD_UP_ITEM_16")
    private Double addUpItem16;

    @ApiModelProperty(value = "累计支出金额-拨款--高校基本科研业务费")
    @TableField("ADD_UP_ITEM_17")
    private Double addUpItem17;

    @ApiModelProperty(value = "累计支出金额-拨款--高校管理改革等绩效拨款")
    @TableField("ADD_UP_ITEM_18")
    private Double addUpItem18;

    @ApiModelProperty(value = "累计支出金额-拨款--高校建设世界一流大学(学科)专项资金")
    @TableField("ADD_UP_ITEM_19")
    private Double addUpItem19;

    @ApiModelProperty(value = "累计支出金额-拨款--教育教学改革专项资金")
    @TableField("ADD_UP_ITEM_20")
    private Double addUpItem20;

    @ApiModelProperty(value = "执行进度-以前年度结转结余资金")
    @TableField("PROCESS_ITEM_1")
    private Double processItem1;

    @ApiModelProperty(value = "执行进度-结转--基本支出")
    @TableField("PROCESS_ITEM_2")
    private Double processItem2;

    @ApiModelProperty(value = "执行进度-结转--公用经费")
    @TableField("PROCESS_ITEM_3")
    private Double processItem3;

    @ApiModelProperty(value = "执行进度-结转--项目支出")
    @TableField("PROCESS_ITEM_4")
    private Double processItem4;

    @ApiModelProperty(value = "执行进度-结转--其中:改善基本办学条件专项资金(教育修购)")
    @TableField("PROCESS_ITEM_5")
    private Double processItem5;

    @ApiModelProperty(value = "执行进度-结转--高校基本科研业务费")
    @TableField("PROCESS_ITEM_6")
    private Double processItem6;

    @ApiModelProperty(value = "执行进度-结转--高校管理改革等绩效拨款")
    @TableField("PROCESS_ITEM_7")
    private Double processItem7;

    @ApiModelProperty(value = "执行进度-结转--高校建设世界一流大学(学科)专项资金")
    @TableField("PROCESS_ITEM_8")
    private Double processItem8;

    @ApiModelProperty(value = "执行进度-结转--教育教学改革专项资金")
    @TableField("PROCESS_ITEM_9")
    private Double processItem9;

    @ApiModelProperty(value = "执行进度-结转-其中:人员经费")
    @TableField("PROCESS_ITEM_10")
    private Double processItem10;

    @ApiModelProperty(value = "执行进度-本年拨款")
    @TableField("PROCESS_ITEM_11")
    private Double processItem11;

    @ApiModelProperty(value = "执行进度-拨款-基本支出")
    @TableField("PROCESS_ITEM_12")
    private Double processItem12;

    @ApiModelProperty(value = "执行进度-拨款-其中:人员经费")
    @TableField("PROCESS_ITEM_13")
    private Double processItem13;

    @ApiModelProperty(value = "执行进度-拨款--公用经费")
    @TableField("PROCESS_ITEM_14")
    private Double processItem14;

    @ApiModelProperty(value = "执行进度-拨款--项目支出")
    @TableField("PROCESS_ITEM_15")
    private Double processItem15;

    @ApiModelProperty(value = "执行进度-拨款--其中:改善基本办学条件专项资金(教育修购)")
    @TableField("PROCESS_ITEM_16")
    private Double processItem16;

    @ApiModelProperty(value = "执行进度-拨款--高校基本科研业务费")
    @TableField("PROCESS_ITEM_17")
    private Double processItem17;

    @ApiModelProperty(value = "执行进度-拨款--高校管理改革等绩效拨款")
    @TableField("PROCESS_ITEM_18")
    private Double processItem18;

    @ApiModelProperty(value = "执行进度-拨款--高校建设世界一流大学(学科)专项资金")
    @TableField("PROCESS_ITEM_19")
    private Double processItem19;

    @ApiModelProperty(value = "执行进度-拨款--教育教学改革专项资金")
    @TableField("PROCESS_ITEM_20")
    private Double processItem20;

    @ApiModelProperty(value = "未执行原因-以前年度结转结余资金")
    @TableField("REASON_ITEM_1")
    private String reasonItem1;

    @ApiModelProperty(value = "未执行原因-结转--基本支出")
    @TableField("REASON_ITEM_2")
    private String reasonItem2;

    @ApiModelProperty(value = "未执行原因-结转--公用经费")
    @TableField("REASON_ITEM_3")
    private String reasonItem3;

    @ApiModelProperty(value = "未执行原因-结转--项目支出")
    @TableField("REASON_ITEM_4")
    private String reasonItem4;

    @ApiModelProperty(value = "未执行原因-结转--其中:改善基本办学条件专项资金(教育修购)")
    @TableField("REASON_ITEM_5")
    private String reasonItem5;

    @ApiModelProperty(value = "未执行原因-结转--高校基本科研业务费")
    @TableField("REASON_ITEM_6")
    private String reasonItem6;

    @ApiModelProperty(value = "未执行原因-结转--高校管理改革等绩效拨款")
    @TableField("REASON_ITEM_7")
    private String reasonItem7;

    @ApiModelProperty(value = "未执行原因-结转--高校建设世界一流大学(学科)专项资金")
    @TableField("REASON_ITEM_8")
    private String reasonItem8;

    @ApiModelProperty(value = "未执行原因-结转--教育教学改革专项资金")
    @TableField("REASON_ITEM_9")
    private String reasonItem9;

    @ApiModelProperty(value = "未执行原因-结转-其中:人员经费")
    @TableField("REASON_ITEM_10")
    private String reasonItem10;

    @ApiModelProperty(value = "未执行原因-本年拨款")
    @TableField("REASON_ITEM_11")
    private String reasonItem11;

    @ApiModelProperty(value = "未执行原因-拨款-基本支出")
    @TableField("REASON_ITEM_12")
    private String reasonItem12;

    @ApiModelProperty(value = "未执行原因-拨款-其中:人员经费")
    @TableField("REASON_ITEM_13")
    private String reasonItem13;

    @ApiModelProperty(value = "未执行原因-拨款--公用经费")
    @TableField("REASON_ITEM_14")
    private String reasonItem14;

    @ApiModelProperty(value = "未执行原因-拨款--项目支出")
    @TableField("REASON_ITEM_15")
    private String reasonItem15;

    @ApiModelProperty(value = "未执行原因-拨款--其中:改善基本办学条件专项资金(教育修购)")
    @TableField("REASON_ITEM_16")
    private String reasonItem16;

    @ApiModelProperty(value = "未执行原因-拨款--高校基本科研业务费")
    @TableField("REASON_ITEM_17")
    private String reasonItem17;

    @ApiModelProperty(value = "未执行原因-拨款--高校管理改革等绩效拨款")
    @TableField("REASON_ITEM_18")
    private String reasonItem18;

    @ApiModelProperty(value = "未执行原因-拨款--高校建设世界一流大学(学科)专项资金")
    @TableField("REASON_ITEM_19")
    private String reasonItem19;

    @ApiModelProperty(value = "未执行原因-拨款--教育教学改革专项资金")
    @TableField("REASON_ITEM_20")
    private String reasonItem20;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "扩展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    @TableField("EXT5")
    private String ext5;


    public static final String ID = "ID";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String NAME = "NAME";

    public static final String YEAR = "YEAR";

    public static final String MONTH = "MONTH";

    public static final String STATUS = "STATUS";

    public static final String PERIOD_TYPE = "PERIOD_TYPE";

    public static final String MERGE_FLAG = "MERGE_FLAG";

    public static final String REPLY_ALLOCATE_ITEM_1 = "REPLY_ALLOCATE_ITEM_1";

    public static final String REPLY_ALLOCATE_ITEM_2 = "REPLY_ALLOCATE_ITEM_2";

    public static final String REPLY_ALLOCATE_ITEM_3 = "REPLY_ALLOCATE_ITEM_3";

    public static final String REPLY_ALLOCATE_ITEM_4 = "REPLY_ALLOCATE_ITEM_4";

    public static final String REPLY_ALLOCATE_ITEM_5 = "REPLY_ALLOCATE_ITEM_5";

    public static final String REPLY_ALLOCATE_ITEM_6 = "REPLY_ALLOCATE_ITEM_6";

    public static final String REPLY_ALLOCATE_ITEM_7 = "REPLY_ALLOCATE_ITEM_7";

    public static final String REPLY_ALLOCATE_ITEM_8 = "REPLY_ALLOCATE_ITEM_8";

    public static final String REPLY_ALLOCATE_ITEM_9 = "REPLY_ALLOCATE_ITEM_9";

    public static final String REPLY_ALLOCATE_ITEM_10 = "REPLY_ALLOCATE_ITEM_10";

    public static final String CARRY_FORWARD_ITEM_1 = "CARRY_FORWARD_ITEM_1";

    public static final String CARRY_FORWARD_ITEM_2 = "CARRY_FORWARD_ITEM_2";

    public static final String CARRY_FORWARD_ITEM_3 = "CARRY_FORWARD_ITEM_3";

    public static final String CARRY_FORWARD_ITEM_4 = "CARRY_FORWARD_ITEM_4";

    public static final String CARRY_FORWARD_ITEM_5 = "CARRY_FORWARD_ITEM_5";

    public static final String CARRY_FORWARD_ITEM_6 = "CARRY_FORWARD_ITEM_6";

    public static final String CARRY_FORWARD_ITEM_7 = "CARRY_FORWARD_ITEM_7";

    public static final String CARRY_FORWARD_ITEM_8 = "CARRY_FORWARD_ITEM_8";

    public static final String CARRY_FORWARD_ITEM_9 = "CARRY_FORWARD_ITEM_9";

    public static final String CARRY_FORWARD_ITEM_10 = "CARRY_FORWARD_ITEM_10";

    public static final String ADD_UP_ITEM_1 = "ADD_UP_ITEM_1";

    public static final String ADD_UP_ITEM_2 = "ADD_UP_ITEM_2";

    public static final String ADD_UP_ITEM_4 = "ADD_UP_ITEM_4";

    public static final String ADD_UP_ITEM_5 = "ADD_UP_ITEM_5";

    public static final String ADD_UP_ITEM_6 = "ADD_UP_ITEM_6";

    public static final String ADD_UP_ITEM_7 = "ADD_UP_ITEM_7";

    public static final String ADD_UP_ITEM_8 = "ADD_UP_ITEM_8";

    public static final String ADD_UP_ITEM_9 = "ADD_UP_ITEM_9";

    public static final String ADD_UP_ITEM_10 = "ADD_UP_ITEM_10";

    public static final String ADD_UP_ITEM_3 = "ADD_UP_ITEM_3";

    public static final String ADD_UP_ITEM_11 = "ADD_UP_ITEM_11";

    public static final String ADD_UP_ITEM_12 = "ADD_UP_ITEM_12";

    public static final String ADD_UP_ITEM_13 = "ADD_UP_ITEM_13";

    public static final String ADD_UP_ITEM_14 = "ADD_UP_ITEM_14";

    public static final String ADD_UP_ITEM_15 = "ADD_UP_ITEM_15";

    public static final String ADD_UP_ITEM_16 = "ADD_UP_ITEM_16";

    public static final String ADD_UP_ITEM_17 = "ADD_UP_ITEM_17";

    public static final String ADD_UP_ITEM_18 = "ADD_UP_ITEM_18";

    public static final String ADD_UP_ITEM_19 = "ADD_UP_ITEM_19";

    public static final String ADD_UP_ITEM_20 = "ADD_UP_ITEM_20";

    public static final String PROCESS_ITEM_1 = "PROCESS_ITEM_1";

    public static final String PROCESS_ITEM_2 = "PROCESS_ITEM_2";

    public static final String PROCESS_ITEM_3 = "PROCESS_ITEM_3";

    public static final String PROCESS_ITEM_4 = "PROCESS_ITEM_4";

    public static final String PROCESS_ITEM_5 = "PROCESS_ITEM_5";

    public static final String PROCESS_ITEM_6 = "PROCESS_ITEM_6";

    public static final String PROCESS_ITEM_7 = "PROCESS_ITEM_7";

    public static final String PROCESS_ITEM_8 = "PROCESS_ITEM_8";

    public static final String PROCESS_ITEM_9 = "PROCESS_ITEM_9";

    public static final String PROCESS_ITEM_10 = "PROCESS_ITEM_10";

    public static final String PROCESS_ITEM_11 = "PROCESS_ITEM_11";

    public static final String PROCESS_ITEM_12 = "PROCESS_ITEM_12";

    public static final String PROCESS_ITEM_13 = "PROCESS_ITEM_13";

    public static final String PROCESS_ITEM_14 = "PROCESS_ITEM_14";

    public static final String PROCESS_ITEM_15 = "PROCESS_ITEM_15";

    public static final String PROCESS_ITEM_16 = "PROCESS_ITEM_16";

    public static final String PROCESS_ITEM_17 = "PROCESS_ITEM_17";

    public static final String PROCESS_ITEM_18 = "PROCESS_ITEM_18";

    public static final String PROCESS_ITEM_19 = "PROCESS_ITEM_19";

    public static final String PROCESS_ITEM_20 = "PROCESS_ITEM_20";

    public static final String REASON_ITEM_1 = "REASON_ITEM_1";

    public static final String REASON_ITEM_2 = "REASON_ITEM_2";

    public static final String REASON_ITEM_3 = "REASON_ITEM_3";

    public static final String REASON_ITEM_4 = "REASON_ITEM_4";

    public static final String REASON_ITEM_5 = "REASON_ITEM_5";

    public static final String REASON_ITEM_6 = "REASON_ITEM_6";

    public static final String REASON_ITEM_7 = "REASON_ITEM_7";

    public static final String REASON_ITEM_8 = "REASON_ITEM_8";

    public static final String REASON_ITEM_9 = "REASON_ITEM_9";

    public static final String REASON_ITEM_10 = "REASON_ITEM_10";

    public static final String REASON_ITEM_11 = "REASON_ITEM_11";

    public static final String REASON_ITEM_12 = "REASON_ITEM_12";

    public static final String REASON_ITEM_13 = "REASON_ITEM_13";

    public static final String REASON_ITEM_14 = "REASON_ITEM_14";

    public static final String REASON_ITEM_15 = "REASON_ITEM_15";

    public static final String REASON_ITEM_16 = "REASON_ITEM_16";

    public static final String REASON_ITEM_17 = "REASON_ITEM_17";

    public static final String REASON_ITEM_18 = "REASON_ITEM_18";

    public static final String REASON_ITEM_19 = "REASON_ITEM_19";

    public static final String REASON_ITEM_20 = "REASON_ITEM_20";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
