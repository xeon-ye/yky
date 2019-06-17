package com.deloitte.platform.api.srpmp.outline.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineSatBook新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsOutlineSatBook表单")
@Data
public class SrpmsOutlineSatBookForm extends SrpmsOutlineFormListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private String id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    private String baseId;

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
    private LocalDateTime pressTime;

}
