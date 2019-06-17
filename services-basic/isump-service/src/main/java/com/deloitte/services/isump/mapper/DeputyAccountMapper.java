package com.deloitte.services.isump.mapper;

import com.deloitte.services.isump.entity.DeputyAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 副账号 Mapper 接口
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
public interface DeputyAccountMapper extends BaseMapper<DeputyAccount> {

    /**
     * 删除副账号
     * @param userId
     * @return
     */
    int delByUserId(@Param("userId") String userId);

    /**
     * 根据用户ID查询副账号列表
     * @param userId
     * @return
     */
    List<DeputyAccount> getByUserId(@Param("userId") String userId);
}
