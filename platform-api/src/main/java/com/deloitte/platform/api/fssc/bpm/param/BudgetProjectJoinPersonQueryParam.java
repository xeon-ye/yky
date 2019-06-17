package com.deloitte.platform.api.fssc.bpm.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jaws
 * @Date : Create in 2019-05-18
 * @Description :  BudgetProjectJoinPerson查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetProjectJoinPersonQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long projectId;
    private String projectCode;
    private Long personId;
    private String personName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long orgId;
    private String orgPath;
    private String validFlag;

}
