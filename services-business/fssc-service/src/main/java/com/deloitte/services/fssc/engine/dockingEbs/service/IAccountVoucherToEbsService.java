package com.deloitte.services.fssc.engine.dockingEbs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.engine.manual.vo.EbsReturnStatusVo;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;

import java.time.LocalDateTime;
import java.util.List;

public interface IAccountVoucherToEbsService{
    /**
     * EBS入总账之前COA段做交叉验证
     * @param url
     * @param jsonRootBean
     * @return
     */
    EbsReturnStatusVo CrossValidationRuleSendToEsb(String url, List<Long> jsonRootBean);

    /**
     * 传送会计凭证到BES系统
     * @param url
     * @param jeHeaderIds
     */
    EbsReturnStatusVo sendAccountVoucherSendToEbs(String url,List<Long> jeHeaderIds);

    /**
     * 生成预制凭证数据
     * @param documentType 该单据的类型
     * @param documentId 该单据的Id
     */
    String generatePrefabricatedCredentials(String documentType,Long documentId);

    /**
     * 发送项目信息到Ebs系统
     * @param code
     */
    void  sendProjectInfoToEbs(String code,String projectName);

    /**
     * 冲销业务单据
     * @param documentType
     * @param documentId
     */
    void reverseAccountForBusiness(String documentType, Long documentId, LocalDateTime  defaultEffectiveDate);
}
