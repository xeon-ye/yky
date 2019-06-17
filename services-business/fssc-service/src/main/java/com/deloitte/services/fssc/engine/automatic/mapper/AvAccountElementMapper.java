package com.deloitte.services.fssc.engine.automatic.mapper;

import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvChartOfAccount;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 核算要素信息 Mapper 接口
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
public interface AvAccountElementMapper extends BaseMapper<AvAccountElement> {
    /**
     * 用于查询核算要素是否被使用
     * @param elementId
     * @return
     */
    List<AvAccountElement> whetherEnabledById(Long elementId);

    /**
     * 用于查询核算要素被分配到那些账薄
     * @param form
     * @return
     */
    List<AvChartOfAccount> elementReleationLedgerList (@Param("form")AvAccountElementQueryForm form);

    /**
     * 用于查询核算要素被分配到那些账薄
     * @param form
     * @return
     */
    Integer elementReleationLedgerListCount (@Param("form")AvAccountElementQueryForm form);

    /**
     * 数据来源取除相应信息
     * @param table
     * @param conditions
     * @return
     */
    List<AvBaseElement> sourceFromObtain(@Param("table")String table,@Param("conditions")String conditions );

    /**
     * 获取核算要素值
     * @param form
     * @returnelementReleationLedgerList
     */
    List<AvAccountElement> queryPageListByParam(@Param("form")AvAccountElementQueryForm form);

    Integer queryPageListByParamCount(@Param("form")AvAccountElementQueryForm form);

}
