package com.deloitte.services.fssc.report.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
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
 * 部门支出总表
 * </p>
 *
 * @author jaws
 * @since 2019-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("REPORT_DEPT_EXPENSE_SUMMARY")
@ApiModel(value="ReportDeptExpenseSummary对象", description="部门支出总表")
public class ReportDeptExpenseSummary extends Model<ReportDeptExpenseSummary> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "单位ID")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    @TableField("UNIT_CODE")
    private String unitCode;

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

    @ApiModelProperty(value = "合计-合计")
    @TableField("TOTAL_ITEM_1")
    private BigDecimal totalItem1;

    @ApiModelProperty(value = "合计-中国医学科学院本机")
    @TableField("TOTAL_ITEM_2")
    private BigDecimal totalItem2;

    @ApiModelProperty(value = "合计-科学技术支出")
    @TableField("TOTAL_ITEM_3")
    private BigDecimal totalItem3;

    @ApiModelProperty(value = "合计-应用研究")
    @TableField("TOTAL_ITEM_4")
    private BigDecimal totalItem4;

    @ApiModelProperty(value = "合计-机构运行")
    @TableField("TOTAL_ITEM_5")
    private BigDecimal totalItem5;

    @ApiModelProperty(value = "合计-社会公益研究")
    @TableField("TOTAL_ITEM_6")
    private BigDecimal totalItem6;

    @ApiModelProperty(value = "合计-科技条件与服务")
    @TableField("TOTAL_ITEM_7")
    private BigDecimal totalItem7;

    @ApiModelProperty(value = "合计-科技条件专项")
    @TableField("TOTAL_ITEM_8")
    private BigDecimal totalItem8;

    @ApiModelProperty(value = "合计-住房保障支出")
    @TableField("TOTAL_ITEM_9")
    private BigDecimal totalItem9;

    @ApiModelProperty(value = "合计-住房改革支出")
    @TableField("TOTAL_ITEM_10")
    private BigDecimal totalItem10;

    @ApiModelProperty(value = "合计-住房公积金")
    @TableField("TOTAL_ITEM_11")
    private BigDecimal totalItem11;

    @ApiModelProperty(value = "合计-提租补贴")
    @TableField("TOTAL_ITEM_12")
    private BigDecimal totalItem12;

    @ApiModelProperty(value = "合计-购房补贴")
    @TableField("TOTAL_ITEM_13")
    private BigDecimal totalItem13;

    @ApiModelProperty(value = "基本支出-合计")
    @TableField("BASE_ITEM_1")
    private BigDecimal baseItem1;

    @ApiModelProperty(value = "基本支出-中国医学科学院本机")
    @TableField("BASE_ITEM_2")
    private BigDecimal baseItem2;

    @ApiModelProperty(value = "基本支出-科学技术支出")
    @TableField("BASE_ITEM_3")
    private BigDecimal baseItem3;

    @ApiModelProperty(value = "基本支出-应用研究")
    @TableField("BASE_ITEM_4")
    private BigDecimal baseItem4;

    @ApiModelProperty(value = "基本支出-机构运行")
    @TableField("BASE_ITEM_5")
    private BigDecimal baseItem5;

    @ApiModelProperty(value = "基本支出-社会公益研究")
    @TableField("BASE_ITEM_6")
    private BigDecimal baseItem6;

    @ApiModelProperty(value = "基本支出-科技条件与服务")
    @TableField("BASE_ITEM_7")
    private BigDecimal baseItem7;

    @ApiModelProperty(value = "基本支出-科技条件专项")
    @TableField("BASE_ITEM_8")
    private BigDecimal baseItem8;

    @ApiModelProperty(value = "基本支出-住房保障支出")
    @TableField("BASE_ITEM_9")
    private BigDecimal baseItem9;

    @ApiModelProperty(value = "基本支出-住房改革支出")
    @TableField("BASE_ITEM_10")
    private BigDecimal baseItem10;

    @ApiModelProperty(value = "基本支出-住房公积金")
    @TableField("BASE_ITEM_11")
    private BigDecimal baseItem11;

    @ApiModelProperty(value = "基本支出-提租补贴")
    @TableField("BASE_ITEM_12")
    private BigDecimal baseItem12;

    @ApiModelProperty(value = "基本支出-购房补贴")
    @TableField("BASE_ITEM_13")
    private BigDecimal baseItem13;

    @ApiModelProperty(value = "项目支出-合计")
    @TableField("PROJECT_ITEM_1")
    private BigDecimal projectItem1;

    @ApiModelProperty(value = "项目支出-中国医学科学院本机")
    @TableField("PROJECT_ITEM_2")
    private BigDecimal projectItem2;

    @ApiModelProperty(value = "项目支出-科学技术支出")
    @TableField("PROJECT_ITEM_3")
    private BigDecimal projectItem3;

    @ApiModelProperty(value = "项目支出-应用研究")
    @TableField("PROJECT_ITEM_4")
    private BigDecimal projectItem4;

    @ApiModelProperty(value = "项目支出-机构运行")
    @TableField("PROJECT_ITEM_5")
    private BigDecimal projectItem5;

    @ApiModelProperty(value = "项目支出-社会公益研究")
    @TableField("PROJECT_ITEM_6")
    private BigDecimal projectItem6;

    @ApiModelProperty(value = "项目支出-科技条件与服务")
    @TableField("PROJECT_ITEM_7")
    private BigDecimal projectItem7;

    @ApiModelProperty(value = "项目支出-科技条件专项")
    @TableField("PROJECT_ITEM_8")
    private BigDecimal projectItem8;

    @ApiModelProperty(value = "项目支出-住房保障支出")
    @TableField("PROJECT_ITEM_9")
    private BigDecimal projectItem9;

    @ApiModelProperty(value = "项目支出-住房改革支出")
    @TableField("PROJECT_ITEM_10")
    private BigDecimal projectItem10;

    @ApiModelProperty(value = "项目支出-住房公积金")
    @TableField("PROJECT_ITEM_11")
    private BigDecimal projectItem11;

    @ApiModelProperty(value = "项目支出-提租补贴")
    @TableField("PROJECT_ITEM_12")
    private BigDecimal projectItem12;

    @ApiModelProperty(value = "项目支出-购房补贴")
    @TableField("PROJECT_ITEM_13")
    private BigDecimal projectItem13;

    @ApiModelProperty(value = "上缴上级-合计")
    @TableField("SUPERIOR_ITEM_1")
    private BigDecimal superiorItem1;

    @ApiModelProperty(value = "上缴上级-中国医学科学院本机")
    @TableField("SUPERIOR_ITEM_2")
    private BigDecimal superiorItem2;

    @ApiModelProperty(value = "上缴上级-科学技术支出")
    @TableField("SUPERIOR_ITEM_3")
    private BigDecimal superiorItem3;

    @ApiModelProperty(value = "上缴上级-应用研究")
    @TableField("SUPERIOR_ITEM_4")
    private BigDecimal superiorItem4;

    @ApiModelProperty(value = "上缴上级-机构运行")
    @TableField("SUPERIOR_ITEM_5")
    private BigDecimal superiorItem5;

    @ApiModelProperty(value = "上缴上级-社会公益研究")
    @TableField("SUPERIOR_ITEM_6")
    private BigDecimal superiorItem6;

    @ApiModelProperty(value = "上缴上级-科技条件与服务")
    @TableField("SUPERIOR_ITEM_7")
    private BigDecimal superiorItem7;

    @ApiModelProperty(value = "上缴上级-科技条件专项")
    @TableField("SUPERIOR_ITEM_8")
    private BigDecimal superiorItem8;

    @ApiModelProperty(value = "上缴上级-住房保障支出")
    @TableField("SUPERIOR_ITEM_9")
    private BigDecimal superiorItem9;

    @ApiModelProperty(value = "上缴上级-住房改革支出")
    @TableField("SUPERIOR_ITEM_10")
    private BigDecimal superiorItem10;

    @ApiModelProperty(value = "上缴上级-住房公积金")
    @TableField("SUPERIOR_ITEM_11")
    private BigDecimal superiorItem11;

    @ApiModelProperty(value = "上缴上级-提租补贴")
    @TableField("SUPERIOR_ITEM_12")
    private BigDecimal superiorItem12;

    @ApiModelProperty(value = "上缴上级-购房补贴")
    @TableField("SUPERIOR_ITEM_13")
    private BigDecimal superiorItem13;

    @ApiModelProperty(value = "事业单位运营-合计")
    @TableField("INSTITUTION_ITEM_1")
    private BigDecimal institutionItem1;

    @ApiModelProperty(value = "事业单位运营-中国医学科学院本机")
    @TableField("INSTITUTION_ITEM_2")
    private BigDecimal institutionItem2;

    @ApiModelProperty(value = "事业单位运营-科学技术支出")
    @TableField("INSTITUTION_ITEM_3")
    private BigDecimal institutionItem3;

    @ApiModelProperty(value = "事业单位运营-应用研究")
    @TableField("INSTITUTION_ITEM_4")
    private BigDecimal institutionItem4;

    @ApiModelProperty(value = "事业单位运营-机构运行")
    @TableField("INSTITUTION_ITEM_5")
    private BigDecimal institutionItem5;

    @ApiModelProperty(value = "事业单位运营-社会公益研究")
    @TableField("INSTITUTION_ITEM_6")
    private BigDecimal institutionItem6;

    @ApiModelProperty(value = "事业单位运营-科技条件与服务")
    @TableField("INSTITUTION_ITEM_7")
    private BigDecimal institutionItem7;

    @ApiModelProperty(value = "事业单位运营-科技条件专项")
    @TableField("INSTITUTION_ITEM_8")
    private BigDecimal institutionItem8;

    @ApiModelProperty(value = "事业单位运营-住房保障支出")
    @TableField("INSTITUTION_ITEM_9")
    private BigDecimal institutionItem9;

    @ApiModelProperty(value = "事业单位运营-住房改革支出")
    @TableField("INSTITUTION_ITEM_10")
    private BigDecimal institutionItem10;

    @ApiModelProperty(value = "事业单位运营-住房公积金")
    @TableField("INSTITUTION_ITEM_11")
    private BigDecimal institutionItem11;

    @ApiModelProperty(value = "事业单位运营-提租补贴")
    @TableField("INSTITUTION_ITEM_12")
    private BigDecimal institutionItem12;

    @ApiModelProperty(value = "事业单位运营-购房补贴")
    @TableField("INSTITUTION_ITEM_13")
    private BigDecimal institutionItem13;

    @ApiModelProperty(value = "对附属单位补助-合计")
    @TableField("SOLDIERS_ITEM_1")
    private BigDecimal soldiersItem1;

    @ApiModelProperty(value = "对附属单位补助-中国医学科学院本机")
    @TableField("SOLDIERS_ITEM_2")
    private BigDecimal soldiersItem2;

    @ApiModelProperty(value = "对附属单位补助-科学技术支出")
    @TableField("SOLDIERS_ITEM_3")
    private BigDecimal soldiersItem3;

    @ApiModelProperty(value = "对附属单位补助-应用研究")
    @TableField("SOLDIERS_ITEM_4")
    private BigDecimal soldiersItem4;

    @ApiModelProperty(value = "对附属单位补助-机构运行")
    @TableField("SOLDIERS_ITEM_5")
    private BigDecimal soldiersItem5;

    @ApiModelProperty(value = "对附属单位补助-社会公益研究")
    @TableField("SOLDIERS_ITEM_6")
    private BigDecimal soldiersItem6;

    @ApiModelProperty(value = "对附属单位补助-科技条件与服务")
    @TableField("SOLDIERS_ITEM_7")
    private BigDecimal soldiersItem7;

    @ApiModelProperty(value = "对附属单位补助-科技条件专项")
    @TableField("SOLDIERS_ITEM_8")
    private BigDecimal soldiersItem8;

    @ApiModelProperty(value = "对附属单位补助-住房保障支出")
    @TableField("SOLDIERS_ITEM_9")
    private BigDecimal soldiersItem9;

    @ApiModelProperty(value = "对附属单位补助-住房改革支出")
    @TableField("SOLDIERS_ITEM_10")
    private BigDecimal soldiersItem10;

    @ApiModelProperty(value = "对附属单位补助-住房公积金")
    @TableField("SOLDIERS_ITEM_11")
    private BigDecimal soldiersItem11;

    @ApiModelProperty(value = "对附属单位补助-提租补贴")
    @TableField("SOLDIERS_ITEM_12")
    private BigDecimal soldiersItem12;

    @ApiModelProperty(value = "对附属单位补助-购房补贴")
    @TableField("SOLDIERS_ITEM_13")
    private BigDecimal soldiersItem13;

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

    @ApiModelProperty(value = "报表名称")
    @TableField("NAME")
    private String name;


    public static final String ID = "ID";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String YEAR = "YEAR";

    public static final String MONTH = "MONTH";

    public static final String STATUS = "STATUS";

    public static final String PERIOD_TYPE = "PERIOD_TYPE";

    public static final String MERGE_FLAG = "MERGE_FLAG";

    public static final String TOTAL_ITEM_1 = "TOTAL_ITEM_1";

    public static final String TOTAL_ITEM_2 = "TOTAL_ITEM_2";

    public static final String TOTAL_ITEM_3 = "TOTAL_ITEM_3";

    public static final String TOTAL_ITEM_4 = "TOTAL_ITEM_4";

    public static final String TOTAL_ITEM_5 = "TOTAL_ITEM_5";

    public static final String TOTAL_ITEM_6 = "TOTAL_ITEM_6";

    public static final String TOTAL_ITEM_7 = "TOTAL_ITEM_7";

    public static final String TOTAL_ITEM_8 = "TOTAL_ITEM_8";

    public static final String TOTAL_ITEM_9 = "TOTAL_ITEM_9";

    public static final String TOTAL_ITEM_10 = "TOTAL_ITEM_10";

    public static final String TOTAL_ITEM_11 = "TOTAL_ITEM_11";

    public static final String TOTAL_ITEM_12 = "TOTAL_ITEM_12";

    public static final String TOTAL_ITEM_13 = "TOTAL_ITEM_13";

    public static final String BASE_ITEM_1 = "BASE_ITEM_1";

    public static final String BASE_ITEM_2 = "BASE_ITEM_2";

    public static final String BASE_ITEM_3 = "BASE_ITEM_3";

    public static final String BASE_ITEM_4 = "BASE_ITEM_4";

    public static final String BASE_ITEM_5 = "BASE_ITEM_5";

    public static final String BASE_ITEM_6 = "BASE_ITEM_6";

    public static final String BASE_ITEM_7 = "BASE_ITEM_7";

    public static final String BASE_ITEM_8 = "BASE_ITEM_8";

    public static final String BASE_ITEM_9 = "BASE_ITEM_9";

    public static final String BASE_ITEM_10 = "BASE_ITEM_10";

    public static final String BASE_ITEM_11 = "BASE_ITEM_11";

    public static final String BASE_ITEM_12 = "BASE_ITEM_12";

    public static final String BASE_ITEM_13 = "BASE_ITEM_13";

    public static final String PROJECT_ITEM_1 = "PROJECT_ITEM_1";

    public static final String PROJECT_ITEM_2 = "PROJECT_ITEM_2";

    public static final String PROJECT_ITEM_3 = "PROJECT_ITEM_3";

    public static final String PROJECT_ITEM_4 = "PROJECT_ITEM_4";

    public static final String PROJECT_ITEM_5 = "PROJECT_ITEM_5";

    public static final String PROJECT_ITEM_6 = "PROJECT_ITEM_6";

    public static final String PROJECT_ITEM_7 = "PROJECT_ITEM_7";

    public static final String PROJECT_ITEM_8 = "PROJECT_ITEM_8";

    public static final String PROJECT_ITEM_9 = "PROJECT_ITEM_9";

    public static final String PROJECT_ITEM_10 = "PROJECT_ITEM_10";

    public static final String PROJECT_ITEM_11 = "PROJECT_ITEM_11";

    public static final String PROJECT_ITEM_12 = "PROJECT_ITEM_12";

    public static final String PROJECT_ITEM_13 = "PROJECT_ITEM_13";

    public static final String SUPERIOR_ITEM_1 = "SUPERIOR_ITEM_1";

    public static final String SUPERIOR_ITEM_2 = "SUPERIOR_ITEM_2";

    public static final String SUPERIOR_ITEM_3 = "SUPERIOR_ITEM_3";

    public static final String SUPERIOR_ITEM_4 = "SUPERIOR_ITEM_4";

    public static final String SUPERIOR_ITEM_5 = "SUPERIOR_ITEM_5";

    public static final String SUPERIOR_ITEM_6 = "SUPERIOR_ITEM_6";

    public static final String SUPERIOR_ITEM_7 = "SUPERIOR_ITEM_7";

    public static final String SUPERIOR_ITEM_8 = "SUPERIOR_ITEM_8";

    public static final String SUPERIOR_ITEM_9 = "SUPERIOR_ITEM_9";

    public static final String SUPERIOR_ITEM_10 = "SUPERIOR_ITEM_10";

    public static final String SUPERIOR_ITEM_11 = "SUPERIOR_ITEM_11";

    public static final String SUPERIOR_ITEM_12 = "SUPERIOR_ITEM_12";

    public static final String SUPERIOR_ITEM_13 = "SUPERIOR_ITEM_13";

    public static final String INSTITUTION_ITEM_1 = "INSTITUTION_ITEM_1";

    public static final String INSTITUTION_ITEM_2 = "INSTITUTION_ITEM_2";

    public static final String INSTITUTION_ITEM_3 = "INSTITUTION_ITEM_3";

    public static final String INSTITUTION_ITEM_4 = "INSTITUTION_ITEM_4";

    public static final String INSTITUTION_ITEM_5 = "INSTITUTION_ITEM_5";

    public static final String INSTITUTION_ITEM_6 = "INSTITUTION_ITEM_6";

    public static final String INSTITUTION_ITEM_7 = "INSTITUTION_ITEM_7";

    public static final String INSTITUTION_ITEM_8 = "INSTITUTION_ITEM_8";

    public static final String INSTITUTION_ITEM_9 = "INSTITUTION_ITEM_9";

    public static final String INSTITUTION_ITEM_10 = "INSTITUTION_ITEM_10";

    public static final String INSTITUTION_ITEM_11 = "INSTITUTION_ITEM_11";

    public static final String INSTITUTION_ITEM_12 = "INSTITUTION_ITEM_12";

    public static final String INSTITUTION_ITEM_13 = "INSTITUTION_ITEM_13";

    public static final String SOLDIERS_ITEM_1 = "SOLDIERS_ITEM_1";

    public static final String SOLDIERS_ITEM_2 = "SOLDIERS_ITEM_2";

    public static final String SOLDIERS_ITEM_3 = "SOLDIERS_ITEM_3";

    public static final String SOLDIERS_ITEM_4 = "SOLDIERS_ITEM_4";

    public static final String SOLDIERS_ITEM_5 = "SOLDIERS_ITEM_5";

    public static final String SOLDIERS_ITEM_6 = "SOLDIERS_ITEM_6";

    public static final String SOLDIERS_ITEM_7 = "SOLDIERS_ITEM_7";

    public static final String SOLDIERS_ITEM_8 = "SOLDIERS_ITEM_8";

    public static final String SOLDIERS_ITEM_9 = "SOLDIERS_ITEM_9";

    public static final String SOLDIERS_ITEM_10 = "SOLDIERS_ITEM_10";

    public static final String SOLDIERS_ITEM_11 = "SOLDIERS_ITEM_11";

    public static final String SOLDIERS_ITEM_12 = "SOLDIERS_ITEM_12";

    public static final String SOLDIERS_ITEM_13 = "SOLDIERS_ITEM_13";

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
        return null;
    }

}
