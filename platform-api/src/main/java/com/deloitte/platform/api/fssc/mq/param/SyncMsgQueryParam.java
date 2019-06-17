package com.deloitte.platform.api.fssc.mq.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : jaws
 * @Date : Create in 2019-05-29
 * @Description :  SyncMsg查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyncMsgQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long relationId;
    private String msgType;
    private String msgBody;
    private String msgStatus;
    private String msgErrorInfo;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
