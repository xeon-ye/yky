package com.deloitte.services.fssc.system.unit.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.unit.param.UnitInfoQueryForm;
import com.deloitte.platform.api.fssc.unit.vo.UnitBankBaseInfoVo;
import com.deloitte.platform.api.fssc.unit.vo.UnitInfoVo;
import com.deloitte.services.fssc.system.unit.entity.UnitInfo;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description : UnitInfo服务类接口
 * @Modified :
 */
public interface IUnitInfoService extends IService<UnitInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<UnitInfo>
     */
    IPage<UnitInfoVo> selectPage(UnitInfoQueryForm queryForm);


    UnitBankBaseInfoVo unitBankBaseUnitType(Long id);
}
