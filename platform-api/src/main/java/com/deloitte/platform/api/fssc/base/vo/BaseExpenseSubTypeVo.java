package com.deloitte.platform.api.fssc.base.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-02
 * @Description : BaseExpenseSubType返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseExpenseSubTypeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.AUTO)
    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "支出大类ID")
    private String expenseMainTypeId;

    @ApiModelProperty(value = "支出大类编码")
    private String expenseMainTypeCode;

    @ApiModelProperty(value = "支出大类名称")
    private String expenseMainTypeName;

    @ApiModelProperty(value = "财-会计科目编码")
    private String cAccountCode;

    @ApiModelProperty(value = "财-会计科目名称")
    private String cAccountName;

    @ApiModelProperty(value = "预-会计科目编码")
    private String yAccountCode;

    @ApiModelProperty(value = "预-会计科目名称")
    private String yAccountName;

    @ApiModelProperty(value = "是否有效")
    private String validFlag;

    @ApiModelProperty(value = "生效日期")
    private LocalDateTime validDate;

    @ApiModelProperty(value = "失效日期")
    private LocalDateTime invalidDate;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "组织ID")
    private String orgId;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "费用类型")
    private String costType;


}
