package com.deloitte.services.fssc.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeExpenseQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeExpenseVo;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypeExpense;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p> 单据类型-支出大类关系 Mapper 接口 </p>
 *
 * @author jaws
 * @since 2019-03-18
 */
public interface BaseDocumentTypeExpenseMapper extends BaseMapper<BaseDocumentTypeExpense> {

    /**
     *  关联查询
     * @param page
     * @param queryForm
     * @return
     */
    List<BaseDocumentTypeExpenseVo> selectVoPage(@Param("page") IPage page,
            @Param("queryForm") BaseDocumentTypeExpenseQueryForm queryForm);

    /**
     * 列表关联查询
     * @param queryForm
     * @return
     */
    List<BaseDocumentTypeExpenseVo> listVo(@Param("queryForm") BaseDocumentTypeExpenseQueryForm queryForm);
}
