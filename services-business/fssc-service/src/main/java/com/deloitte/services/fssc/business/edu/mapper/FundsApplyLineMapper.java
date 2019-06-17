package com.deloitte.services.fssc.business.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.edu.param.FundsApplyLineQueryForm;
import com.deloitte.platform.api.fssc.edu.vo.FundsApplyLineVo;
import com.deloitte.services.fssc.business.edu.entity.FundsApplyLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 教育经费细化申请单行 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-04-29
 */
public interface FundsApplyLineMapper extends BaseMapper<FundsApplyLine> {

    List<FundsApplyLineVo> selectLineVo(@Param("unitId") Long unitId, @Param("schoolId") Long schoolId,
                                        @Param("recieveUserId") Long recieveUserId,
                                        @Param("documentNum") String documentNum,
                                        @Param("page") IPage page);

}
