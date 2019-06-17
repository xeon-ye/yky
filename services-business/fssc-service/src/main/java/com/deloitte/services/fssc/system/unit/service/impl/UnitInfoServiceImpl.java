package com.deloitte.services.fssc.system.unit.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.unit.param.UnitInfoQueryForm;
import com.deloitte.platform.api.fssc.unit.vo.UnitBankBaseInfoVo;
import com.deloitte.platform.api.fssc.unit.vo.UnitInfoVo;
import com.deloitte.services.fssc.system.unit.entity.UnitInfo;
import com.deloitte.services.fssc.system.unit.mapper.UnitInfoMapper;
import com.deloitte.services.fssc.system.unit.service.IUnitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description :  UnitInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class UnitInfoServiceImpl extends ServiceImpl<UnitInfoMapper, UnitInfo> implements IUnitInfoService {


    @Autowired
    private UnitInfoMapper unitInfoMapper;

    @Override
    public IPage<UnitInfoVo> selectPage(UnitInfoQueryForm queryForm) {
        Page<UnitInfoVo> page = new Page(queryForm.getCurrentPage(), queryForm.getPageSize());
        List<UnitInfoVo> unitInfoVos =
                unitInfoMapper.selectByPageConditions(page, queryForm);
        page.setRecords(unitInfoVos);
        return page;
    }


    @Override
    public UnitBankBaseInfoVo unitBankBaseUnitType(Long id) {
        return unitInfoMapper.selectVo(id);
    }
}

