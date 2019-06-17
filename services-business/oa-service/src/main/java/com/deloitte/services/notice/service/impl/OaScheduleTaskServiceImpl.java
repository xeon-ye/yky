package com.deloitte.services.notice.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.isump.OrganizationClient;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.entity.OaDzggInterfaceTempAttach;
import com.deloitte.services.attachment.mapper.OaAttachmentMapper;
import com.deloitte.services.attachment.mapper.OaDzggInterfaceTempAttachMapper;
import com.deloitte.services.notice.entity.OaDzggInterfaceTemp;
import com.deloitte.services.notice.entity.OaNotice;
import com.deloitte.services.notice.mapper.OaDzggInterfaceTempMapper;
import com.deloitte.services.notice.mapper.OaNoticeMapper;
import com.deloitte.services.notice.service.IOaScheduleTaskService;
import com.deloitte.services.oa.exception.OaErrorType;
import com.deloitte.services.rulesystem.entity.OaRuleSystem;
import com.deloitte.services.rulesystem.mapper.OaRuleSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * 定时任务
 */
@Service
@Transactional
public class OaScheduleTaskServiceImpl implements IOaScheduleTaskService {

    @Autowired
    private OaNoticeMapper noticeMapper;

    @Autowired
    private OaRuleSystemMapper ruleSystemMapper;

    @Autowired
    private OaAttachmentMapper attachmentMapper;

    @Autowired
    private OaDzggInterfaceTempMapper dzggInterfaceTempMapper;

    @Autowired
    private OaDzggInterfaceTempAttachMapper oaDzggInterfaceTempAttachMapper;

    @Autowired
    private UserFeignService userFeignService;

    @Override
    public void parseNoticeTemp2Db() {
        OaDzggInterfaceTemp dzggInterfaceTemp = new OaDzggInterfaceTemp();
        dzggInterfaceTemp.setIsRead(0);
        QueryWrapper<OaDzggInterfaceTemp> wrapper = new QueryWrapper<>(dzggInterfaceTemp);
        List<OaDzggInterfaceTemp> list = dzggInterfaceTempMapper.selectList(wrapper);

        list.forEach(interfaceTemp -> {
            if("通知公告".equals(interfaceTemp.getTypeName())){ // 通知公告
                OaNotice oaNotice = new OaNotice();
                oaNotice.setNoticeTypeName("通知公告");
                oaNotice.setNoticeSortName(interfaceTemp.getSortName());
                oaNotice.setApplyOrgCode(interfaceTemp.getApplyOrgCode());
                oaNotice.setApplyOrgName(interfaceTemp.getApplyOrgCode());
                // 用户信息
                oaNotice.setApplyUserName(interfaceTemp.getApplyUserName());
                Map<String, Object> user = getUserByEmpno(interfaceTemp.getApplyUserEmpno());
                oaNotice.setApplyUserId(String.valueOf(user.get("id")));
                oaNotice.setApplyDeptCode(String.valueOf(user.get("dept")));
                oaNotice.setApplyDatetime(interfaceTemp.getApplyDate());
                oaNotice.setTitle(interfaceTemp.getNoticeTitle());
                oaNotice.setNoticeContent(interfaceTemp.getNoticeContent());
                noticeMapper.insert(oaNotice); //插入到公告表
                // 更新附件
                OaDzggInterfaceTempAttach oaDzggInterfaceTempAttach = new OaDzggInterfaceTempAttach();
                oaDzggInterfaceTempAttach.setRequestId(interfaceTemp.getRequestId());
                Wrapper<OaDzggInterfaceTempAttach> queryWrapper = new QueryWrapper<>(oaDzggInterfaceTempAttach);
                List<OaDzggInterfaceTempAttach> files = oaDzggInterfaceTempAttachMapper.selectList(queryWrapper);
                files.forEach(atta -> {
                    OaAttachment attachment = new OaAttachment();
                    attachment.setBusicessId(String.valueOf(oaNotice.getId()));
                    attachment.setAttachUrl(atta.getFileAddress());
                    attachment.setAttachName(atta.getFileName());
                    attachmentMapper.insert(attachment);
                });

            }
            if("规则制度".equals(interfaceTemp.getTypeName())){ //规则制度
                OaRuleSystem ruleSystem = new OaRuleSystem();
                ruleSystem.setRuleSortName(interfaceTemp.getSortName());

                ruleSystem.setApplyOrgCode(interfaceTemp.getApplyOrgCode());
                ruleSystem.setApplyOrgName(interfaceTemp.getApplyOrgCode());
                // 用户信息
                ruleSystem.setApplyUserName(interfaceTemp.getApplyUserName());
                Map<String, Object> user = getUserByEmpno(interfaceTemp.getApplyUserEmpno());
                ruleSystem.setApplyUserId(String.valueOf(user.get("id")));
                ruleSystem.setApplyDeptCode(String.valueOf(user.get("dept")));
                ruleSystem.setApplyDatetime(interfaceTemp.getApplyDate());
                ruleSystem.setTitle(interfaceTemp.getNoticeTitle());
                ruleSystem.setRuleContent(interfaceTemp.getNoticeContent());
                ruleSystemMapper.insert(ruleSystem);

                //更新附件
                OaDzggInterfaceTempAttach oaDzggInterfaceTempAttach = new OaDzggInterfaceTempAttach();
                oaDzggInterfaceTempAttach.setRequestId(interfaceTemp.getRequestId());
                QueryWrapper<OaDzggInterfaceTempAttach> queryWrapper = new QueryWrapper<>(oaDzggInterfaceTempAttach);
                List<OaDzggInterfaceTempAttach> files = oaDzggInterfaceTempAttachMapper.selectList(queryWrapper);
                files.forEach(atta -> {
                    OaAttachment oaAttachment = new OaAttachment();
                    oaAttachment.setBusicessId(String.valueOf(ruleSystem.getId()));
                    oaAttachment.setAttachName(atta.getFileName());
                    oaAttachment.setAttachUrl(atta.getFileAddress());
                    attachmentMapper.insert(oaAttachment);
                });

            }

            // 更新临时表数据状态
            interfaceTemp.setIsRead(1);
            dzggInterfaceTempMapper.updateById(interfaceTemp);
        });
    }

    private Map<String, Object> getUserByEmpno(String empNo) {
        Result result = userFeignService.getByEmpNo(empNo);
        if(result.isSuccess()){
            return (Map<String, Object>) result.getData();
        }

        throw new ServiceException(OaErrorType.USERINFO_ERROR, result.getMesg());
    }

}
