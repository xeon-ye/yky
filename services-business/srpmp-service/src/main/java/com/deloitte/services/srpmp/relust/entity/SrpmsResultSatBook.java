package com.deloitte.services.srpmp.relust.entity;

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
import lombok.experimental.Accessors;

/**
 * <p>
 * 成果管理-科技著作
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_RESULT_SAT_BOOK")
@ApiModel(value="SrpmsResultSatBook对象", description="成果管理-科技著作")
public class SrpmsResultSatBook extends Model<SrpmsResultSatBook> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单位CODE")
    @TableField("DEPT_CODE")
    private Long deptCode;

    @ApiModelProperty(value = "年")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "月")
    @TableField("MONTH")
    private String month;

    @ApiModelProperty(value = "项目编码")
    @TableField("PRO_NUM")
    private String proNum;

    @ApiModelProperty(value = "项目名称")
    @TableField("PRO_NAME")
    private String proName;

    @ApiModelProperty(value = "著作名")
    @TableField("BOOK_NAME")
    private String bookName;

    @ApiModelProperty(value = "作者")
    @TableField("AUTHOR")
    private String author;

    @ApiModelProperty(value = "作者单位")
    @TableField("BOOK_ORG")
    private String bookOrg;

    @ApiModelProperty(value = "主编/参编")
    @TableField("BOOK_LEVEL")
    private String bookLevel;

    @ApiModelProperty(value = "出版社")
    @TableField("PRESS")
    private String press;

    @ApiModelProperty(value = "出版时间")
    @TableField("PRESS_TIME")
    private LocalDateTime pressTime;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;


    public static final String ID = "ID";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String YEAR = "YEAR";

    public static final String MONTH = "MONTH";

    public static final String PRO_NUM = "PRO_NUM";

    public static final String PRO_NAME = "PRO_NAME";

    public static final String BOOK_NAME = "BOOK_NAME";

    public static final String AUTHOR = "AUTHOR";

    public static final String BOOK_ORG = "BOOK_ORG";

    public static final String BOOK_LEVEL = "BOOK_LEVEL";

    public static final String PRESS = "PRESS";

    public static final String PRESS_TIME = "PRESS_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
