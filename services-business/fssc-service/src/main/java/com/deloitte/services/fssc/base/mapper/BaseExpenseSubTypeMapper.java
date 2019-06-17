package com.deloitte.services.fssc.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseExpenseSubTypeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseExpenseSubTypeVo;
import com.deloitte.services.fssc.base.entity.BaseExpenseSubType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 支出小类 Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2019-03-02
 */
public interface BaseExpenseSubTypeMapper extends BaseMapper<BaseExpenseSubType> {

    /**
     * 查询单条
     * @param id
     * @return
     */
    BaseExpenseSubTypeVo selectVo(@Param("id") Long id);

    /**
     * 分页查询
     * @param page
     * @param queryForm
     * @return
     */
    List<BaseExpenseSubTypeVo> selectVoPage(IPage page, @Param("queryForm") BaseExpenseSubTypeQueryForm queryForm);

    /**
     * 根据 大类ID查询关联的小类
     * @param expenseMainTypeIdList
     * @return
     */
    Long countByExpenseMainTypeIds(@Param("expenseMainTypeIdList")  List<Long> expenseMainTypeIdList);

}
