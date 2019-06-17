package com.deloitte.services.contract.mapper;

import com.deloitte.services.contract.entity.SysContractNo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 合同编号配置 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
public interface SysContractNoMapper extends BaseMapper<SysContractNo> {

    /**
     * 获取合同编号
     * @param comp 公司code
     * @param dep 部门code
     * @param year 年份
     * @return
     */
    List<SysContractNo> getContractNo(String comp, String dep, String year);

    /**
     * 对应合同流水号 +1
     * @param comp 公司code
     * @param dep 部门code
     * @param year 年份
     * @param newNo 流水号
     * @return
     */
    boolean UpdateContractNo(String comp, String dep, String year, String newNo);
}
