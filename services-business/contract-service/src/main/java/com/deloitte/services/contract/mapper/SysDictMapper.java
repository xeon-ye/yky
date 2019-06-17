package com.deloitte.services.contract.mapper;

import com.deloitte.platform.api.contract.vo.SysDictVo;
import com.deloitte.services.contract.entity.SysDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 合同管理字典表 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
public interface SysDictMapper extends BaseMapper<SysDict> {

    List<SysDictVo> getSysDictVoByType(String type);

    BigDecimal getContractTypeCount(String type);

    int deleteContractType(String id);
}
