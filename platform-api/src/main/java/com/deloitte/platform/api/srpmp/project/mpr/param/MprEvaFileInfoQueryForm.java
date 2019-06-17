package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-10
 * @Description :   MprEvaFileInfo查询from对象
 * @Modified :
 */
@ApiModel("MprEvaFileInfo查询表单")
@Data
public class MprEvaFileInfoQueryForm extends BaseQueryForm<MprEvaFileInfoQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "附件二")
    private String annexTwo;

    @ApiModelProperty(value = "附件三")
    private String annexThree;

    @ApiModelProperty(value = "附件四")
    private String annexFour;

    @ApiModelProperty(value = "附件五")
    private String annexFive;

    @ApiModelProperty(value = "附件七")
    private String annexSeven;

    @ApiModelProperty(value = "附件八")
    private String annexEight;

    @ApiModelProperty(value = "附件十")
    private String annexTen;

    @ApiModelProperty(value = "附件十一")
    private String annexEleven;

    @ApiModelProperty(value = "附件十二")
    private String annexTwelve;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "附件一")
    private String annexOne;

    @ApiModelProperty(value = "附件六")
    private String annexSix;

    @ApiModelProperty(value = "其他附件")
    private String annexOther;
}
