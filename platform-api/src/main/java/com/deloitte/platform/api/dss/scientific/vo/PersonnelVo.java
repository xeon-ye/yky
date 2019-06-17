package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 科研人才 各个级别数据展示vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "职级")
    private String positionTitle;

    @ApiModelProperty(value = "该职级所有人数")
    private Long totalNum;

    @ApiModelProperty(value = "超过45岁人数")
    private Long over45Num;

}
