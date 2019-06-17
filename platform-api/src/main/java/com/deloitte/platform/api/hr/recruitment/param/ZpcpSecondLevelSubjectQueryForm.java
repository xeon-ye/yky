package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :   ZpcpSecondLevelSubject查询from对象
 * @Modified :
 */
@ApiModel("ZpcpSecondLevelSubject查询表单")
@Data
public class ZpcpSecondLevelSubjectQueryForm extends BaseQueryForm<ZpcpSecondLevelSubjectQueryParam>  {

    @ApiModelProperty(value = "一级学科ID")
    private Long firstSubjectId;
}
