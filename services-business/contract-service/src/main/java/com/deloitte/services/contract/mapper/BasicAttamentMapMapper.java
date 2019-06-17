package com.deloitte.services.contract.mapper;

import com.deloitte.services.contract.entity.BasicAttamentMap;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 合同文件关系表 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-29
 */
public interface BasicAttamentMapMapper extends BaseMapper<BasicAttamentMap> {

    boolean deleteByContractIdAndFileType(String contractId, String fileType);

    List<BasicAttamentMap> selectByAttamentId(String attamentId);

    List<BasicAttamentMap> selectByContractId(String contractId);
}
