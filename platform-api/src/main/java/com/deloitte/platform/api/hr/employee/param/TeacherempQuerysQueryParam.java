package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description :  TeacherempQuerys查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherempQuerysQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String houseSelling;
    private String houseSellingAddres;
    private String discountNumber;
    private LocalDateTime openingTime;
    private String remarks;
    private String createTime;
    private LocalDateTime careteBy;
    private LocalDateTime updateBy;
    private String updateTime;
    private String company;

}
