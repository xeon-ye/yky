package com.deloitte.services.fssc.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeSubTypeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseIncomeSubTypeVo;
import com.deloitte.services.fssc.base.entity.BaseIncomeSubType;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 收入小类 Mapper 接口
 * </p>
 *
 * @author jaws
 * @since 2019-02-21
 */
@Mapper
public interface BaseIncomeSubTypeMapper extends BaseMapper<BaseIncomeSubType> {

    /**
     * 查询单条
     * @param id
     * @return
     */
    BaseIncomeSubTypeVo selectVo(@Param("id") Long id);
    /**
     * 分页查询
     * @param page
     * @param queryForm
     * @return
     */
    List<BaseIncomeSubTypeVo> selectVoPage(@Param("page")IPage page,@Param("queryForm") BaseIncomeSubTypeQueryForm queryForm);

}
