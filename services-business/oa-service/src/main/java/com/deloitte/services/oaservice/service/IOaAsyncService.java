package com.deloitte.services.oaservice.service;

import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.vo.OaSendMsgFrom;
import com.deloitte.platform.api.oaservice.vo.OaSendMsgReceiveFrom;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.oaservice.entity.OaMssSendInfo;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public interface IOaAsyncService {

    /**
     * 执行异步任务
     */
    void sendMsgAsync(OaSendMsgFrom form,List<UserVo> userList);

    void sendMsg(OaMssSendInfo oaMssSendInfo,String content);

    void sendMail(String to,String title,String content);

    void monitoring(String requestId, int total, List<Future> futures);

    Result<List<UserVo>> getDiyOrgUsers(OaSendMsgReceiveFrom oaSendMsgReceiveFrom);

}
