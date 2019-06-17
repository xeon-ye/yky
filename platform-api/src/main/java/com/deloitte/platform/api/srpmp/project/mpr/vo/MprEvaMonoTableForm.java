package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.alibaba.fastjson.JSONArray;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-13
 * @Description : MprEvaMonoTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaMonoTable表单")
@Data
public class MprEvaMonoTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "专著/教材名称")
    private String monographTeachName;

    @ApiModelProperty(value = "完成人")
    private JSONArray completePerson;

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

}
