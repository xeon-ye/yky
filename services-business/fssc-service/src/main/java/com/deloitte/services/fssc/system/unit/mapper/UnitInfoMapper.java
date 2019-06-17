package com.deloitte.services.fssc.system.unit.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.unit.param.UnitInfoQueryForm;
import com.deloitte.platform.api.fssc.unit.vo.UnitBankBaseInfoVo;
import com.deloitte.platform.api.fssc.unit.vo.UnitInfoVo;
import com.deloitte.services.fssc.system.unit.entity.UnitInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 单位表 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-02-20
 */
public interface UnitInfoMapper extends BaseMapper<UnitInfo> {

    List<UnitInfoVo> selectByPageConditions(IPage page,@Param("form") UnitInfoQueryForm form);

    /**
     * 查询单条
     * @param id
     * @return
     */
    UnitBankBaseInfoVo selectVo(@Param("id") Long  id );


    Long getSequence(@Param("sequenceName") String sequenceName);

    void createSequenceName(@Param("sequenceName") String sequenceName);

    void resetSequenceName(@Param("sequenceName") String sequenceName, @Param("value") Long value);
}
