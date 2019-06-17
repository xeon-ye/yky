package com.deloitte.services.fssc.system.dic.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.dic.param.DicValueQueryForm;
import com.deloitte.services.fssc.system.dic.entity.DicValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典值表 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-02-20
 */
public interface DicValueMapper extends BaseMapper<DicValue> {

    List<DicValue> selectByPageConditions(IPage page, @Param("form") DicValueQueryForm form);

    int deleteByEnumType(List<String> idList);

    int count(Map<String, Object> map);

    List<DicValue> findByPage(Map<String, Object> pageMap);

    int deleteById(List<Long> idList);

    int countBySysEnumValue(DicValue record);

}
