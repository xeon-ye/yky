package com.deloitte.services.fssc.business.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.fssc.pay.param.PyAllDocumentQueryForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOverListVo;
import com.deloitte.services.fssc.business.pay.entity.PyPamentOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 付款单头表 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-04-22
 */
public interface PyPamentOrderInfoMapper extends BaseMapper<PyPamentOrderInfo> {
    List<PyPamentOverListVo> findDocumentAll(@Param("form") PyAllDocumentQueryForm form);

    List<PyPamentOverListVo> findPrivateAll(@Param("form") PyAllDocumentQueryForm form);

    List<PyPamentOverListVo> findBussinessCardAll (@Param("form") PyAllDocumentQueryForm form);

    Integer geExpenseAllDocument (@Param("form") PyPamentOrderInfoVo form);
    Integer tavelAllDocument (@Param("form") PyPamentOrderInfoVo form);
    Integer advanceAllDocument (@Param("form") PyPamentOrderInfoVo form);
    Integer borrowAllDocument (@Param("form") PyPamentOrderInfoVo form);
    Integer laborAllDocument (@Param("form") PyPamentOrderInfoVo form);
    Integer contractAllDocument (@Param("form") PyPamentOrderInfoVo form);

}
