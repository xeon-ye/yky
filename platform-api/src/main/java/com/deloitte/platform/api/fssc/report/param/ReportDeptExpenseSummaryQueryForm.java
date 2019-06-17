package com.deloitte.platform.api.fssc.report.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :   ReportDeptExpenseSummary查询from对象
 * @Modified :
 */
@ApiModel("ReportDeptExpenseSummary查询表单")
@Data
public class ReportDeptExpenseSummaryQueryForm extends BaseQueryForm<ReportDeptExpenseSummaryQueryParam>  {


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
    private Double totalItem1;

    @ApiModelProperty(value = "合计-中国医学科学院本机")
    private Double totalItem2;

    @ApiModelProperty(value = "合计-科学技术支出")
    private Double totalItem3;

    @ApiModelProperty(value = "合计-应用研究")
    private Double totalItem4;

    @ApiModelProperty(value = "合计-机构运行")
    private Double totalItem5;

    @ApiModelProperty(value = "合计-社会公益研究")
    private Double totalItem6;

    @ApiModelProperty(value = "合计-科技条件与服务")
    private Double totalItem7;

    @ApiModelProperty(value = "合计-科技条件专项")
    private Double totalItem8;

    @ApiModelProperty(value = "合计-住房保障支出")
    private Double totalItem9;

    @ApiModelProperty(value = "合计-住房改革支出")
    private Double totalItem10;

    @ApiModelProperty(value = "合计-住房公积金")
    private Double totalItem11;

    @ApiModelProperty(value = "合计-提租补贴")
    private Double totalItem12;

    @ApiModelProperty(value = "合计-购房补贴")
    private Double totalItem13;

    @ApiModelProperty(value = "基本支出-合计")
    private Double baseItem1;

    @ApiModelProperty(value = "基本支出-中国医学科学院本机")
    private Double baseItem2;

    @ApiModelProperty(value = "基本支出-科学技术支出")
    private Double baseItem3;

    @ApiModelProperty(value = "基本支出-应用研究")
    private Double baseItem4;

    @ApiModelProperty(value = "基本支出-机构运行")
    private Double baseItem5;

    @ApiModelProperty(value = "基本支出-社会公益研究")
    private Double baseItem6;

    @ApiModelProperty(value = "基本支出-科技条件与服务")
    private Double baseItem7;

    @ApiModelProperty(value = "基本支出-科技条件专项")
    private Double baseItem8;

    @ApiModelProperty(value = "基本支出-住房保障支出")
    private Double baseItem9;

    @ApiModelProperty(value = "基本支出-住房改革支出")
    private Double baseItem10;

    @ApiModelProperty(value = "基本支出-住房公积金")
    private Double baseItem11;

    @ApiModelProperty(value = "基本支出-提租补贴")
    private Double baseItem12;

    @ApiModelProperty(value = "基本支出-购房补贴")
    private Double baseItem13;

    @ApiModelProperty(value = "项目支出-合计")
    private Double projectItem1;

    @ApiModelProperty(value = "项目支出-中国医学科学院本机")
    private Double projectItem2;

    @ApiModelProperty(value = "项目支出-科学技术支出")
    private Double projectItem3;

    @ApiModelProperty(value = "项目支出-应用研究")
    private Double projectItem4;

    @ApiModelProperty(value = "项目支出-机构运行")
    private Double projectItem5;

    @ApiModelProperty(value = "项目支出-社会公益研究")
    private Double projectItem6;

    @ApiModelProperty(value = "项目支出-科技条件与服务")
    private Double projectItem7;

    @ApiModelProperty(value = "项目支出-科技条件专项")
    private Double projectItem8;

    @ApiModelProperty(value = "项目支出-住房保障支出")
    private Double projectItem9;

    @ApiModelProperty(value = "项目支出-住房改革支出")
    private Double projectItem10;

    @ApiModelProperty(value = "项目支出-住房公积金")
    private Double projectItem11;

    @ApiModelProperty(value = "项目支出-提租补贴")
    private Double projectItem12;

    @ApiModelProperty(value = "项目支出-购房补贴")
    private Double projectItem13;

    @ApiModelProperty(value = "上缴上级-合计")
    private Double superiorItem1;

    @ApiModelProperty(value = "上缴上级-中国医学科学院本机")
    private Double superiorItem2;

    @ApiModelProperty(value = "上缴上级-科学技术支出")
    private Double superiorItem3;

    @ApiModelProperty(value = "上缴上级-应用研究")
    private Double superiorItem4;

    @ApiModelProperty(value = "上缴上级-机构运行")
    private Double superiorItem5;

    @ApiModelProperty(value = "上缴上级-社会公益研究")
    private Double superiorItem6;

    @ApiModelProperty(value = "上缴上级-科技条件与服务")
    private Double superiorItem7;

    @ApiModelProperty(value = "上缴上级-科技条件专项")
    private Double superiorItem8;

    @ApiModelProperty(value = "上缴上级-住房保障支出")
    private Double superiorItem9;

    @ApiModelProperty(value = "上缴上级-住房改革支出")
    private Double superiorItem10;

    @ApiModelProperty(value = "上缴上级-住房公积金")
    private Double superiorItem11;

    @ApiModelProperty(value = "上缴上级-提租补贴")
    private Double superiorItem12;

    @ApiModelProperty(value = "上缴上级-购房补贴")
    private Double superiorItem13;

    @ApiModelProperty(value = "事业单位运营-合计")
    private Double institutionItem1;

    @ApiModelProperty(value = "事业单位运营-中国医学科学院本机")
    private Double institutionItem2;

    @ApiModelProperty(value = "事业单位运营-科学技术支出")
    private Double institutionItem3;

    @ApiModelProperty(value = "事业单位运营-应用研究")
    private Double institutionItem4;

    @ApiModelProperty(value = "事业单位运营-机构运行")
    private Double institutionItem5;

    @ApiModelProperty(value = "事业单位运营-社会公益研究")
    private Double institutionItem6;

    @ApiModelProperty(value = "事业单位运营-科技条件与服务")
    private Double institutionItem7;

    @ApiModelProperty(value = "事业单位运营-科技条件专项")
    private Double institutionItem8;

    @ApiModelProperty(value = "事业单位运营-住房保障支出")
    private Double institutionItem9;

    @ApiModelProperty(value = "事业单位运营-住房改革支出")
    private Double institutionItem10;

    @ApiModelProperty(value = "事业单位运营-住房公积金")
    private Double institutionItem11;

    @ApiModelProperty(value = "事业单位运营-提租补贴")
    private Double institutionItem12;

    @ApiModelProperty(value = "事业单位运营-购房补贴")
    private Double institutionItem13;

    @ApiModelProperty(value = "对附属单位补助-合计")
    private Double soldiersItem1;

    @ApiModelProperty(value = "对附属单位补助-中国医学科学院本机")
    private Double soldiersItem2;

    @ApiModelProperty(value = "对附属单位补助-科学技术支出")
    private Double soldiersItem3;

    @ApiModelProperty(value = "对附属单位补助-应用研究")
    private Double soldiersItem4;

    @ApiModelProperty(value = "对附属单位补助-机构运行")
    private Double soldiersItem5;

    @ApiModelProperty(value = "对附属单位补助-社会公益研究")
    private Double soldiersItem6;

    @ApiModelProperty(value = "对附属单位补助-科技条件与服务")
    private Double soldiersItem7;

    @ApiModelProperty(value = "对附属单位补助-科技条件专项")
    private Double soldiersItem8;

    @ApiModelProperty(value = "对附属单位补助-住房保障支出")
    private Double soldiersItem9;

    @ApiModelProperty(value = "对附属单位补助-住房改革支出")
    private Double soldiersItem10;

    @ApiModelProperty(value = "对附属单位补助-住房公积金")
    private Double soldiersItem11;

    @ApiModelProperty(value = "对附属单位补助-提租补贴")
    private Double soldiersItem12;

    @ApiModelProperty(value = "对附属单位补助-购房补贴")
    private Double soldiersItem13;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

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
