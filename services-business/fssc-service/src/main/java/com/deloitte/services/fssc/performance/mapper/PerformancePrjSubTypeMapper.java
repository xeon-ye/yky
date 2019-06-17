package com.deloitte.services.fssc.performance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.performance.param.PerformancePrjSubTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjSubTypeVo;
import com.deloitte.services.fssc.performance.entity.PerformancePrjSubType;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 项目小类 Mapper 接口
 * </p>
 *
 * @author jaws
 * @since 2019-04-03
 */
public interface PerformancePrjSubTypeMapper extends BaseMapper<PerformancePrjSubType> {

    /**
     * 分页查询
     * @param page
     * @param queryForm
     * @return
     */
    List<PerformancePrjSubTypeVo> selectVoPage(@Param("page")IPage page,@Param("queryForm")
            PerformancePrjSubTypeQueryForm queryForm);

}
