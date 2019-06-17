package com.deloitte.services.srpmp.outline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Apeng
 * @since 2019-02-22
 */
public interface SrpmsOutlineBaseMapper extends BaseMapper<SrpmsOutlineBase> {

    @Select("SELECT t1.id FROM SRPMS_OUTLINE_BASE t1\n" +
            "       WHERE t1.orgId = #{orgId},\n" +
            "       AND t1.year = #{year},\n" +
            "       AND t1.month = #{month}")
    long getSrpmsOutlineBaseQuery(long orgId, String year, String month);

    /**
     * 新增题录基础信息表dao层接口
     *
     * @param srpmsOutlineBase
     * @return
     */
    int insertSrpmsOutlineBase(SrpmsOutlineBase srpmsOutlineBase);
}
