package com.deloitte.platform.api.srpmp.project.mpr.vo;
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
 * @Description : MprEvaTreaTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaTreaTable表单")
@Data
public class MprEvaTreaTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "论文题目")
    private String treaTopic;

    @ApiModelProperty(value = "期刊名称")
    private String journalName;

    @ApiModelProperty(value = "姓名")
    private String author;

    @ApiModelProperty(value = "作者类别")
    private String authorType;

    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "卷（期）")
    private String volume;

    @ApiModelProperty(value = "页")
    private String page;

    @ApiModelProperty(value = "收录来源")
    private String sourceInclusion;

    @ApiModelProperty(value = "SCI分区")
    private String sciPartition;

    @ApiModelProperty(value = "发表时间")
    private String issuTime;

    @ApiModelProperty(value = "期刊影响因子")
    private String impactFactor;

    @ApiModelProperty(value = "引用频次")
    private String refeFreq;

}
