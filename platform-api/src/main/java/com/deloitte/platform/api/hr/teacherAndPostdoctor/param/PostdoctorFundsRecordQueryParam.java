package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  PostdoctorFundsRecord查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostdoctorFundsRecordQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long postdoctorFundsId;
    private String type;
    private String remark;
    private Long useFunds;
    private LocalDateTime useTime;
    private Long surplusFunds;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createBy;
    private Long updateBy;
    private String orgCode;

}
