package com.deloitte.services.fssc.system.bank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.bank.param.BankUnitInfoQueryForm;
import com.deloitte.platform.api.fssc.bank.vo.BankUnitInfoVo;
import com.deloitte.services.fssc.system.bank.entity.BankUnitInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 银行信息主表 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-02-25
 */
public interface BankUnitInfoMapper extends BaseMapper<BankUnitInfo> {

    List<BankUnitInfoVo> selectByPageConditions(Page<BankUnitInfoVo> page,
                                                @Param("form") BankUnitInfoQueryForm queryForm);

    List<BankUnitInfoVo> selectByConditions(@Param("form") BankUnitInfoQueryForm queryForm);
}
