package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : LJH
 * @Date : Create in 2019-05-13
 * @Description :  GccResult查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccResultQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String applyYear;
    private String projectName;
    private Long projectId;
    private String categoryName;
    private Long categoryId;
    private Long userId;
    private String userName;
    private String applyUnit;
    private LocalDateTime applyDate;
    private String status;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;

}
