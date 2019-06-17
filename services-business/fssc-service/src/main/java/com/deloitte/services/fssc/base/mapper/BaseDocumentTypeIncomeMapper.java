package com.deloitte.services.fssc.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeIncomeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeIncomeVo;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypeIncome;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p> 单据类型-收入大类关系 Mapper 接口 </p>
 *
 * @author jaws
 * @since 2019-03-18
 */
public interface BaseDocumentTypeIncomeMapper extends BaseMapper<BaseDocumentTypeIncome> {

    /**
     * 关联查询
     */
    List<BaseDocumentTypeIncomeVo> selectVoPage(@Param("page") IPage page,
            @Param("queryForm") BaseDocumentTypeIncomeQueryForm queryForm);

    /**
     * 列表关联查询
     * @param queryForm
     * @return
     */
    List<BaseDocumentTypeIncomeVo> listVo(@Param("queryForm") BaseDocumentTypeIncomeQueryForm queryForm);

}
