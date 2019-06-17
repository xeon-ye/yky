package com.deloitte.services.fssc.system.dic.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.dic.param.DicQueryForm;
import com.deloitte.platform.api.fssc.dic.vo.DicVo;
import com.deloitte.services.fssc.system.dic.entity.Dic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典主表 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-02-20
 */
public interface DicMapper extends BaseMapper<Dic> {

    int count(Map<String, Object> map);

    List<Dic> findByPage(Map<String, Object> pageMap);

   // int deleteById(List<Long> idList);

    int deleteByEnumType(List<String> idList);

    int countBySysEnumType(Dic record);

    void insertt(Dic record);

    List<Dic> selectByPageConditions(IPage page, @Param("form") DicQueryForm form);

}
