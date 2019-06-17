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
 * @Date : Create in 2019-04-13
 * @Description :   MprEvaMonoTable查询from对象
 * @Modified :
 */
@ApiModel("MprEvaMonoTable查询表单")
@Data
public class MprEvaMonoTableQueryForm extends BaseQueryForm<MprEvaMonoTableQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "专著/教材名称")
    private String monographTeachName;

    @ApiModelProperty(value = "完成人")
    private String completePerson;

    @ApiModelProperty(value = "级别（主编、副主编、编委）")
    private String positionLevel;

    @ApiModelProperty(value = "ISBN")
    private String isbn;

    @ApiModelProperty(value = "专著类别（专著/译著/教材）")
    private String monographType;

    @ApiModelProperty(value = "出版社")
    private String pubHouse;

    @ApiModelProperty(value = "出版时间")
    private String pubDate;

    @ApiModelProperty(value = "字数（万字）")
    private String wordCount;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}
