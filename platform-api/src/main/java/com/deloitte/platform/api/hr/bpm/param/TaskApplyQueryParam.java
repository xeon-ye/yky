package com.deloitte.platform.api.hr.bpm.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Mr.Zhong
 * @Date ：Created in 2019/4/19 15:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskApplyQueryParam extends BaseParam {

    private String applyId;

    private String sourceSystem = "HR";

    private String taskStauts = "已提交";

}
