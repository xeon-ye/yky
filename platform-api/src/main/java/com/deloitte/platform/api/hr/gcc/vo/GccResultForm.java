package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-13
 * @Description : GccResult新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccResult表单")
@Data
public class GccResultForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申报项目名称")
    private String projectName;

    @ApiModelProperty(value = "申报项目编号")
    private Long projectId;

    @ApiModelProperty(value = "申报项目类别")
    private String categoryName;

    @ApiModelProperty(value = "申报项目类别编号")
    private Long categoryId;

    @ApiModelProperty(value = "教职工编号")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "申报单位")
    private String applyUnit;

    @ApiModelProperty(value = "申报时间")
    private LocalDateTime applyDate;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

}
