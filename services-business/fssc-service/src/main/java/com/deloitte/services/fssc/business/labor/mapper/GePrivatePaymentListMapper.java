package com.deloitte.services.fssc.business.labor.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.labor.param.LcLaborCostLineQueryForm;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostLineChinaAndForeignVo;
import com.deloitte.services.fssc.business.labor.entity.GePrivatePaymentList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 对私付款清单 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-03-25
 */
public interface GePrivatePaymentListMapper extends BaseMapper<GePrivatePaymentList> {

    List<LcLaborCostLineChinaAndForeignVo> findPrivatePayList(IPage<LcLaborCostLineChinaAndForeignVo> page
            , @Param("queryForm") LcLaborCostLineQueryForm queryForm);
}
