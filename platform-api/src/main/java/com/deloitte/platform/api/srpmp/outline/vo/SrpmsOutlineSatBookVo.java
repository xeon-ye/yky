package com.deloitte.platform.api.srpmp.outline.vo;

import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineSatBook返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsOutlineSatBookVo extends SrpmsOutlineVoListBasic {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    @JsonSerialize(using = LongJsonSerializer.class)
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

    @ApiModelProperty(value = "主编/参编字典名称")
    private String bookLevelName;

    @ApiModelProperty(value = "出版社")
    private String press;

    @ApiModelProperty(value = "出版时间")
    private LocalDateTime pressTime;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
