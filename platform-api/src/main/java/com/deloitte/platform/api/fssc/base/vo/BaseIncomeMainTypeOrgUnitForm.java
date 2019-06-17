package com.deloitte.platform.api.fssc.base.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 收入大类-组织单位关系返回的VO对象
 * </p>
 *
 * @author jaws
 * @since 2019-02-21
 */
@ApiModel("新增收入大类-组织单位关系表单")
@Data
public class BaseIncomeMainTypeOrgUnitForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "收入大类ID")
    private Long incomeMainTypeId;

    @ApiModelProperty(value = "组织单位ID")
    private Long orgUnitId;

    @ApiModelProperty(value = "组织单位编码")
    private String orgUnitCode;

    @ApiModelProperty(value = "组织单位名称")
    private String orgUnitName;

    @ApiModelProperty(value = "生失效标志")
    private String validFlag;
}
