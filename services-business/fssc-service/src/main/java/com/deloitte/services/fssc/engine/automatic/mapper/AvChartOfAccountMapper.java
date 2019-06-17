package com.deloitte.services.fssc.engine.automatic.mapper;

import com.deloitte.platform.api.fssc.engine.automatic.param.AvUtilQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvChartOfAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 记录账薄信息 Mapper 接口
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
public interface AvChartOfAccountMapper extends BaseMapper<AvChartOfAccount> {

    List<AvBaseElement> getUtilList(@Param("form")AvUtilQueryForm form);
    //获取账薄下单位的总个数
    Integer getUtilListCount(@Param("form")AvUtilQueryForm form);
    //获取到账薄下面的COA结构
    List<AvAccountElement> getAccountFrameList(@Param("ledgerId")Long ledgerId);

}
