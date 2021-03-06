package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :   MprJouPostTable查询from对象
 * @Modified :
 */
@ApiModel("MprJouPostTable查询表单")
@Data
public class MprJouPostTableQueryForm extends BaseQueryForm<MprJouPostTableQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "期刊名称")
    private String jouName;

    @ApiModelProperty(value = "期刊级别")
    private String jouLevel;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "任职职务")
    private String position;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}
