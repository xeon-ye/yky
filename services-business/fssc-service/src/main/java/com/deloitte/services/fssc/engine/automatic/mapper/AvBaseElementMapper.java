package com.deloitte.services.fssc.engine.automatic.mapper;

import com.deloitte.platform.api.fssc.engine.automatic.vo.AvBaseElementVo;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * COA_凭证类别_币种 三种类型基础数据 Mapper 接口
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
public interface AvBaseElementMapper extends BaseMapper<AvBaseElement> {
    /**
     *
     * @param end
     * @param start
     * @param elementId
     * @return
     */
    List<AvBaseElement> baseValueListByElementId(@Param("end")int end,@Param("start")int start,@Param("elementId")Long elementId);

    /**
     *
     * @param elementId
     * @return
     */

    Integer baseValueCountByElementId(@Param("elementId")Long elementId);

    /**
     *
     * @param type//公司编码
     * @param ledgerId
     * @return
     */
    List<AvBaseElement> selectBaseElementForAccount(@Param("type")String type,@Param("ledgerId")Long ledgerId);

    /**
     * 获取凭证类别
     * @return
     */
    List<AvBaseElement> selectJeCategory();

    /**
     * 手工录入凭证，根据账薄和序号，获取到相应的COA值
     * @param ledgerId
     * @return
     */
    List<AvBaseElementVo> selectBaseElementByNum(@Param("ledgerId")Long ledgerId);

    /**
     * 通过账薄和编码得到中文描述
     * @param code
     * @param ledgerId
     * @return
     */

    List<AvBaseElement> selectBaseElementByCode(@Param("code")String code,@Param("ledgerId")Long ledgerId,@Param("segmentNum")Long segmentNum);
}
