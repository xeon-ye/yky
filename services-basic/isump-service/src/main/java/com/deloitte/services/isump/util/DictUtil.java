package com.deloitte.services.isump.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.services.isump.entity.Dict;
import com.deloitte.services.isump.mapper.DictMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DictUtil {
    @Autowired
    private static DictMapper dictMapper;

    public static Map<String, Map<String,String>> DICT = new HashMap<>();

    static {
        dictMapper = SpringUtils.getBean(DictMapper.class);
        //initDict();
    }
    /**
     * 根据code查询字典列表
     * @param code
     * @return
     */
    public static Map<String,String> getDict(String code){
        //在缓存中获取字典表
        Map<String,String> map = DICT.get(code);
        if(map == null){
            map = new HashMap<>();
            List<Dict> list = dictMapper.selectListByParentCode(code);
            for (Dict dict : list) {
                map.put(dict.getCode(),dict.getName());
            }
            DICT.put(code,map);
        }
        return map;
    }

    /**
     * 重新设置单个缓存
     * @param code
     */
    public static void setDict(String code){
        if(StringUtils.isNotEmpty(code) && !"0000".equals(code)){
            List<Dict> list = dictMapper.selectListByParentCode(code);
            Map<String,String> map = new HashMap<>();
            for (Dict dict:list) {
                map.put(dict.getCode(),dict.getName());
            }
            DICT.put(code,map);
        }

    }


    public static void initDict(){
        //将字典类型存储到缓存中
        List<Dict> list = dictMapper.selectListByParentCode("0000");
        for (Dict dict:list) {
            DICT.put(dict.getCode(),new HashMap<>());
        }
        //将每一个字典值保存到对应字典类型的map中
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc(Dict.SORT);
        List<Dict> dicts = dictMapper.selectList(wrapper);
        for (Dict dict:dicts) {
            if(StringUtils.isNotEmpty(dict.getParentCode())){
                Map<String,String> map = DICT.get(dict.getParentCode());
                if(map != null){
                    map.put(dict.getCode(),dict.getName());
                }
            }

        }
    }
}
