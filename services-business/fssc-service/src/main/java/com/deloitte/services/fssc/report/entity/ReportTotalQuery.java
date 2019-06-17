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
 * 财务-报表-查询页面
 * </p>
 *
 * @author jaws
 * @since 2019-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("REPORT_TOTAL_QUERY")
@ApiModel(value="ReportTotalQuery对象", description="财务-报表-查询页面")
public class ReportTotalQuery extends Model<ReportTotalQuery> {

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

    @ApiModelProperty(value = "报表类型")
    @TableField("REPORT_TYPE")
    private String reportType;

    @ApiModelProperty(value = "报表名称")
    @TableField("REPORT_NAME")
    private String reportName;

    @ApiModelProperty(value = "报表ID")
    @TableField("REPORT_ID")
    private Long reportId;

    @ApiModelProperty(value = "年份")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "月份")
    @TableField("MONTH")
    private String month;

    @ApiModelProperty(value = "报表状态")
    @TableField("REPORT_STATUS")
    private String reportStatus;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "属性1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "属性2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "属性3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "属性4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "属性5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "周期类型(Y:年度，M:月)")
    @TableField("PERIOD_TYPE")
    private String periodType;

    @ApiModelProperty(value = "是否多个单位合并")
    @TableField("MERGE_FLAG")
    private String mergeFlag;


    public static final String ID = "ID";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String REPORT_TYPE = "REPORT_TYPE";

    public static final String REPORT_NAME = "REPORT_NAME";

    public static final String REPORT_ID = "REPORT_ID";

    public static final String YEAR = "YEAR";

    public static final String MONTH = "MONTH";

    public static final String REPORT_STATUS = "REPORT_STATUS";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String PERIOD_TYPE = "PERIOD_TYPE";

    public static final String MERGE_FLAG = "MERGE_FLAG";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
