package com.deloitte.platform.api.srpmp.project.base;

import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author:LIJUN
 * Date:17/04/2019
 * Description:
 */
public interface SrpmsMsgSyncClient {

    String path = "/srpmp/project/base/msg/sync";

    /**
     * 重发同步失败消息
     * @return
     */
    @GetMapping(value = path+"/resendFailMsg/{msgId}")
    Result resendFailMsg(@PathVariable(value = "msgId") Long msgId);

    @GetMapping(value = path+"/{projectId}/{msgType}")
    Result sendMsg(@PathVariable(value = "projectId") Long projectId, @PathVariable(value = "msgType") Long msgType);
}
