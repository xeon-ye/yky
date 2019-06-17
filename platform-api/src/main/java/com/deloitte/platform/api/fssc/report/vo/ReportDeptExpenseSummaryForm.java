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
 * @Date : Create in 2019-06-12
 * @Description : ReportDeptExpenseSummary新增修改form对象
 * @Modified :
 */
@ApiModel("新增ReportDeptExpenseSummary表单")
@Data
public class ReportDeptExpenseSummaryForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "月份")
    private String month;

    @ApiModelProperty(value = "报表状态")
    private String status;

    @ApiModelProperty(value = "周期类型(Y:年度，M:月)")
    private String periodType;

    @ApiModelProperty(value = "是否多个单位合并")
    private String mergeFlag;

    @ApiModelProperty(value = "合计-合计")
    private BigDecimal totalItem1;

    @ApiModelProperty(value = "合计-中国医学科学院本机")
    private BigDecimal totalItem2;

    @ApiModelProperty(value = "合计-科学技术支出")
    private BigDecimal totalItem3;

    @ApiModelProperty(value = "合计-应用研究")
    private BigDecimal totalItem4;

    @ApiModelProperty(value = "合计-机构运行")
    private BigDecimal totalItem5;

    @ApiModelProperty(value = "合计-社会公益研究")
    private BigDecimal totalItem6;

    @ApiModelProperty(value = "合计-科技条件与服务")
    private BigDecimal totalItem7;

    @ApiModelProperty(value = "合计-科技条件专项")
    private BigDecimal totalItem8;

    @ApiModelProperty(value = "合计-住房保障支出")
    private BigDecimal totalItem9;

    @ApiModelProperty(value = "合计-住房改革支出")
    private BigDecimal totalItem10;

    @ApiModelProperty(value = "合计-住房公积金")
    private BigDecimal totalItem11;

    @ApiModelProperty(value = "合计-提租补贴")
    private BigDecimal totalItem12;

    @ApiModelProperty(value = "合计-购房补贴")
    private BigDecimal totalItem13;

    @ApiModelProperty(value = "基本支出-合计")
    private BigDecimal baseItem1;

    @ApiModelProperty(value = "基本支出-中国医学科学院本机")
    private BigDecimal baseItem2;

    @ApiModelProperty(value = "基本支出-科学技术支出")
    private BigDecimal baseItem3;

    @ApiModelProperty(value = "基本支出-应用研究")
    private BigDecimal baseItem4;

    @ApiModelProperty(value = "基本支出-机构运行")
    private BigDecimal baseItem5;

    @ApiModelProperty(value = "基本支出-社会公益研究")
    private BigDecimal baseItem6;

    @ApiModelProperty(value = "基本支出-科技条件与服务")
    private BigDecimal baseItem7;

    @ApiModelProperty(value = "基本支出-科技条件专项")
    private BigDecimal baseItem8;

    @ApiModelProperty(value = "基本支出-住房保障支出")
    private BigDecimal baseItem9;

    @ApiModelProperty(value = "基本支出-住房改革支出")
    private BigDecimal baseItem10;

    @ApiModelProperty(value = "基本支出-住房公积金")
    private BigDecimal baseItem11;

    @ApiModelProperty(value = "基本支出-提租补贴")
    private BigDecimal baseItem12;

    @ApiModelProperty(value = "基本支出-购房补贴")
    private BigDecimal baseItem13;

    @ApiModelProperty(value = "项目支出-合计")
    private BigDecimal projectItem1;

    @ApiModelProperty(value = "项目支出-中国医学科学院本机")
    private BigDecimal projectItem2;

    @ApiModelProperty(value = "项目支出-科学技术支出")
    private BigDecimal projectItem3;

    @ApiModelProperty(value = "项目支出-应用研究")
    private BigDecimal projectItem4;

    @ApiModelProperty(value = "项目支出-机构运行")
    private BigDecimal projectItem5;

    @ApiModelProperty(value = "项目支出-社会公益研究")
    private BigDecimal projectItem6;

    @ApiModelProperty(value = "项目支出-科技条件与服务")
    private BigDecimal projectItem7;

    @ApiModelProperty(value = "项目支出-科技条件专项")
    private BigDecimal projectItem8;

    @ApiModelProperty(value = "项目支出-住房保障支出")
    private BigDecimal projectItem9;

    @ApiModelProperty(value = "项目支出-住房改革支出")
    private BigDecimal projectItem10;

    @ApiModelProperty(value = "项目支出-住房公积金")
    private BigDecimal projectItem11;

    @ApiModelProperty(value = "项目支出-提租补贴")
    private BigDecimal projectItem12;

    @ApiModelProperty(value = "项目支出-购房补贴")
    private BigDecimal projectItem13;

    @ApiModelProperty(value = "上缴上级-合计")
    private BigDecimal superiorItem1;

    @ApiModelProperty(value = "上缴上级-中国医学科学院本机")
    private BigDecimal superiorItem2;

    @ApiModelProperty(value = "上缴上级-科学技术支出")
    private BigDecimal superiorItem3;

    @ApiModelProperty(value = "上缴上级-应用研究")
    private BigDecimal superiorItem4;

    @ApiModelProperty(value = "上缴上级-机构运行")
    private BigDecimal superiorItem5;

    @ApiModelProperty(value = "上缴上级-社会公益研究")
    private BigDecimal superiorItem6;

    @ApiModelProperty(value = "上缴上级-科技条件与服务")
    private BigDecimal superiorItem7;

    @ApiModelProperty(value = "上缴上级-科技条件专项")
    private BigDecimal superiorItem8;

    @ApiModelProperty(value = "上缴上级-住房保障支出")
    private BigDecimal superiorItem9;

    @ApiModelProperty(value = "上缴上级-住房改革支出")
    private BigDecimal superiorItem10;

    @ApiModelProperty(value = "上缴上级-住房公积金")
    private BigDecimal superiorItem11;

    @ApiModelProperty(value = "上缴上级-提租补贴")
    private BigDecimal superiorItem12;

    @ApiModelProperty(value = "上缴上级-购房补贴")
    private BigDecimal superiorItem13;

    @ApiModelProperty(value = "事业单位运营-合计")
    private BigDecimal institutionItem1;

    @ApiModelProperty(value = "事业单位运营-中国医学科学院本机")
    private BigDecimal institutionItem2;

    @ApiModelProperty(value = "事业单位运营-科学技术支出")
    private BigDecimal institutionItem3;

    @ApiModelProperty(value = "事业单位运营-应用研究")
    private BigDecimal institutionItem4;

    @ApiModelProperty(value = "事业单位运营-机构运行")
    private BigDecimal institutionItem5;

    @ApiModelProperty(value = "事业单位运营-社会公益研究")
    private BigDecimal institutionItem6;

    @ApiModelProperty(value = "事业单位运营-科技条件与服务")
    private BigDecimal institutionItem7;

    @ApiModelProperty(value = "事业单位运营-科技条件专项")
    private BigDecimal institutionItem8;

    @ApiModelProperty(value = "事业单位运营-住房保障支出")
    private BigDecimal institutionItem9;

    @ApiModelProperty(value = "事业单位运营-住房改革支出")
    private BigDecimal institutionItem10;

    @ApiModelProperty(value = "事业单位运营-住房公积金")
    private BigDecimal institutionItem11;

    @ApiModelProperty(value = "事业单位运营-提租补贴")
    private BigDecimal institutionItem12;

    @ApiModelProperty(value = "事业单位运营-购房补贴")
    private BigDecimal institutionItem13;

    @ApiModelProperty(value = "对附属单位补助-合计")
    private BigDecimal soldiersItem1;

    @ApiModelProperty(value = "对附属单位补助-中国医学科学院本机")
    private BigDecimal soldiersItem2;

    @ApiModelProperty(value = "对附属单位补助-科学技术支出")
    private BigDecimal soldiersItem3;

    @ApiModelProperty(value = "对附属单位补助-应用研究")
    private BigDecimal soldiersItem4;

    @ApiModelProperty(value = "对附属单位补助-机构运行")
    private BigDecimal soldiersItem5;

    @ApiModelProperty(value = "对附属单位补助-社会公益研究")
    private BigDecimal soldiersItem6;

    @ApiModelProperty(value = "对附属单位补助-科技条件与服务")
    private BigDecimal soldiersItem7;

    @ApiModelProperty(value = "对附属单位补助-科技条件专项")
    private BigDecimal soldiersItem8;

    @ApiModelProperty(value = "对附属单位补助-住房保障支出")
    private BigDecimal soldiersItem9;

    @ApiModelProperty(value = "对附属单位补助-住房改革支出")
    private BigDecimal soldiersItem10;

    @ApiModelProperty(value = "对附属单位补助-住房公积金")
    private BigDecimal soldiersItem11;

    @ApiModelProperty(value = "对附属单位补助-提租补贴")
    private BigDecimal soldiersItem12;

    @ApiModelProperty(value = "对附属单位补助-购房补贴")
    private BigDecimal soldiersItem13;

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

    @ApiModelProperty(value = "报表名称")
    private String name;

}
