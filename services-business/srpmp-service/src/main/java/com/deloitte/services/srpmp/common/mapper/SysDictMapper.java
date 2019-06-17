package com.deloitte.services.srpmp.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.srpmp.common.entity.SysDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     *  查询角色信息列表
     *
     * @param distCode 查询参数
     * @return 角色列表
     */
    @Select("select ID ,  " +
            "DICT_CODE ,  " +
            "DICT_VALUE ,  " +
            "DICT_PARENT ,  " +
            "ACTIVE_DATE ,  " +
            "EXPIRED_DATE ,  " +
            "IS_EXPIRED ,  " +
            "LEVEl " +
            "from　SYS_DICT  " +
            " WHERE IS_EXPIRED = 0  " +
            " start with DICT_CODE = #{dictCode} connect by  prior id =  DICT_PARENT")
    List<Map> selectByCode(String distCode);

    List<Map> selectSysDict(long distCode);


    List<SysDict> selectSysDictByCategory(@Param("sysDictCategory") String sysDictCategory);


    @Select("SELECT dict_code " +
            "  FROM sys_dict " +
            " WHERE dict_value = #{dictValue} " +
            " START WITH dict_code = #{dictCategory} " +
            "CONNECT BY PRIOR id = dict_parent" )
    String selectCodeByValue(@Param("dictCategory") String dictCategory, @Param("dictValue") String dictValue);


    @Select("SELECT dict_code " +
            "  FROM sys_dict " +
            " WHERE dict_value like #{dictValue} " +
            " START WITH dict_code = #{dictCategory} " +
            "CONNECT BY PRIOR id = dict_parent" )
    String selectCodeByLikeValue(@Param("dictCategory") String dictCategory, @Param("dictValue") String dictValue);


    @Select("SELECT dict_value " +
            "  FROM sys_dict " +
            " WHERE dict_code = #{dictCode} " +
            " START WITH dict_code = #{dictCategory} " +
            "CONNECT BY PRIOR id = dict_parent" )
    String selectValueByCode(@Param("dictCategory")String dictCategory, @Param("dictCode") String dictCode);

    @Select("SELECT DICT_CODE, DICT_VALUE " +
            " FROM sys_dict " +
            " WHERE DICT_PARENT > 0 AND IS_EXPIRED = 0" +
            " START WITH dict_code = #{dictCode} " +
            " CONNECT BY PRIOR id = dict_parent")
    List<SysDict> selectByCodes(@Param("dictCode") String dictCode);

    /**
     * 查询树形的下拉
     *
     * @param dictCode
     * @return
     */
    List<SysDict> getSysDictTreeByCode(@Param("dictCode") String dictCode);

    List<SysDict> getSysDictTanLongByCode(@Param("dictCode") String dictCode);

    List<SysDict> getSysDictSelectByCodes(List<String> list);

    @Select("SELECT ID, DICT_CODE, DICT_VALUE, DICT_PARENT, IS_EXPIRED, LEVEl " +
            " FROM SYS_DICT " +
            " START WITH DICT_PARENT = #{id} " +
            " CONNECT BY PRIOR id = DICT_PARENT")
    List<Map> querySysDictChild(@Param("id") String id);

    @Select("SELECT MAX(to_number(DICT_CODE)) dictCode FROM SYS_DICT WHERE DICT_PARENT = #{id}")
    String getSysDictMaxDictCode(@Param("id") Long id);

    @Select("SELECT COUNT(ID) FROM SYS_DICT " +
            " WHERE DICT_PARENT = #{id} " +
            " AND DICT_VALUE = #{dictValue}")
    int getDictValueExists(@Param("id") Long id, @Param("dictValue") String dictValue);

}
