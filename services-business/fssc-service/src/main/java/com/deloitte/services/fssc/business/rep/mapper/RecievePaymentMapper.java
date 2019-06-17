package com.deloitte.services.fssc.business.rep.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.fssc.rep.param.RecieveLineMsgQueryParam;
import com.deloitte.platform.api.fssc.rep.vo.RecieveLineMsgVo;
import com.deloitte.platform.api.fssc.trade.vo.BusinessRelateDocument;
import com.deloitte.services.fssc.business.rep.entity.RecievePayment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 收款单头表 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-04-10
 */
public interface RecievePaymentMapper extends BaseMapper<RecievePayment> {

    List<RecieveLineMsgVo> findRecieveLines(@Param("param") RecieveLineMsgQueryParam param);

    List<RecieveLineMsgVo> findRecieveLineShouRuShangjiao(@Param("param") RecieveLineMsgQueryParam param);

    List<BusinessRelateDocument> findBySerialNum(@Param("serialNumList") List<String> serialNumList);
}
