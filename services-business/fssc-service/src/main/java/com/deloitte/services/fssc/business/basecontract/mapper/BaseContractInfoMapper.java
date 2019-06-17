package com.deloitte.services.fssc.business.basecontract.mapper;

import com.deloitte.platform.api.fssc.basecontract.vo.BaseContractInfoForm;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 合同信息头表 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-04-17
 */
public interface BaseContractInfoMapper extends BaseMapper<BaseContractInfo> {

    void updateContract(@Param("form") BaseContractInfoForm form);
}
