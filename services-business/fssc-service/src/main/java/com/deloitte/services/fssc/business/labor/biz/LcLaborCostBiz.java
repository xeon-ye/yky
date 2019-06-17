package com.deloitte.services.fssc.business.labor.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.labor.param.LcLaborCostLineQueryForm;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostForm;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostLineChinaAndForeignVo;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostVo;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCost;

public interface LcLaborCostBiz {

    LcLaborCost saveOrUpdateLaborCost(LcLaborCostForm lcLaborCostForm);

    LcLaborCostVo getById(Long id);

    void deleteById(Long id);

    IPage<LcLaborCostLineChinaAndForeignVo> findLineDetail(LcLaborCostLineQueryForm queryForm);

    void doTax(Long id);
}
