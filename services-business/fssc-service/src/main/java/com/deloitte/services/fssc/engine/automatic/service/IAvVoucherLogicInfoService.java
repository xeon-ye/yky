package com.deloitte.services.fssc.engine.automatic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.engine.automatic.param.AvVoucherLogicInfoQueryForm;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountLogicHead;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountLogicLine;
import com.deloitte.services.fssc.engine.automatic.entity.AvVoucherLogicInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description : AvVoucherLogicInfo服务类接口
 * @Modified :
 */
public interface IAvVoucherLogicInfoService extends IService<AvVoucherLogicInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AvVoucherLogicInfo>
     */
    IPage<AvVoucherLogicInfo> selectPage(AvVoucherLogicInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AvVoucherLogicInfo>
     */
    List<AvVoucherLogicInfo> selectList(AvVoucherLogicInfoQueryForm queryForm);

    /**
     * 查询账薄下关联的账薄单据（1->n）
     * @param ledgerId
     * @return
     */
    List<BaseDocumentType> getDocumentTypeList(Long ledgerId);

    /**
     * 根据LogidId获取得到单据下的凭证头信息
     * @param logicId
     * @return
     */
    List<AvAccountLogicHead> getLogicHeadListByLogicId(Long logicId);

    /**
     * 根据LogidId获取得到单据下的凭证行信息
     * @param logicId
     * @return
     */
    List<AvAccountLogicLine>getLogicLineListByLogicId(Long logicId);

    /**
     * 主要运用于会计引擎里面业务单据时获取到相应的业务配置信息。
     * @param type
     * @param unitCode
     * @return
     */
    List<AvVoucherLogicInfo> getLedgerInfo(String type,String unitCode);
}
