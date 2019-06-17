package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaTechStanTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaTechStanTable表单")
@Data
public class MprEvaTechStanTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "获得技术标准名称")
    private String obtTechStanName;

    @ApiModelProperty(value = "标准类型")
    private String stanType;

    @ApiModelProperty(value = "标准号")
    private String stanNum;

    @ApiModelProperty(value = "完成人")
    private JSONArray completePerson;

    @ApiModelProperty(value = "发布时间")
    private String postDate;

}
