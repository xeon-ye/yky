package com.deloitte.platform.api.srpmp.outline.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description :   SrpmsOutlineSatBook查询from对象
 * @Modified :
 */
@ApiModel("SrpmsOutlineSatBook查询表单")
@Data
public class SrpmsOutlineSatBookQueryForm extends BaseQueryForm<SrpmsOutlineSatBookQueryParam>  {


    @ApiModelProperty(value = "ID，主键，业务不相关")
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private Long baseId;

    @ApiModelProperty(value = "项目编码")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "著作名")
    private String bookName;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "作者单位")
    private String bookOrg;

    @ApiModelProperty(value = "主编/参编")
    private String bookLevel;

    @ApiModelProperty(value = "出版社")
    private String press;

    @ApiModelProperty(value = "出版时间")
    private String pressTime;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "月")
    private String month;
}
