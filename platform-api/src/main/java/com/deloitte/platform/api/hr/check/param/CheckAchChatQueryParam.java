package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :  CheckAchChat查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAchChatQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String checkResultId;
    private String chatName;
    private String chatStatus;
    private String checkResultConfirm;
    private String isAgree;
    private String achAppeal;
    private String fileId;
    private String appealHandleOpinion;
    private String appeaResult;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
