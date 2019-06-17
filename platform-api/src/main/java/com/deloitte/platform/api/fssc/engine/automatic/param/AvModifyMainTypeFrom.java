package com.deloitte.platform.api.fssc.engine.automatic.param;

import com.deloitte.platform.api.fssc.base.vo.BaseModifyStatusForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Data
public class AvModifyMainTypeFrom  {
    private List<Long> ledgerIds;

    private Long elementId;
}
