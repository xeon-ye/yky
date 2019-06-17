package com.deloitte.services.fssc.engine.automatic.mapper;

import com.deloitte.services.fssc.engine.automatic.entity.AvVoucherLogicInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 凭证逻辑详细页面记录颗粒度到达每个账薄下面的对应的单据类型 Mapper 接口
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
public interface AvVoucherLogicInfoMapper extends BaseMapper<AvVoucherLogicInfo> {
    /**
     * 主要运用于会计引擎里面业务单据时获取到相应的业务配置信息。
     * @param type
     * @param code
     * @return
     */
      List<AvVoucherLogicInfo> getLedgerInfo(@Param("type")String type,@Param("unitCode")String unitCode);
}
