package com.deloitte.services.srpmp.outline.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pengchao
 * @since 2019-02-14
 */
public interface SrpmsOutlineBasicMapper {
    
    List<Map> selectListByBaseId(long baseId);

   int  deleteById(long baseId);
}
