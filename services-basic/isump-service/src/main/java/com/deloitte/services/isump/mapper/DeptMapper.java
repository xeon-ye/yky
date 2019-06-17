package com.deloitte.services.isump.mapper;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.services.isump.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 项目单位信息表 Mapper 接口
 * </p>
 *
 * @author jianglong
 * @since 2019-03-01
 */
public interface DeptMapper extends BaseMapper<Dept> {

    DeptVo getById(@Param("id") String id);

    DeptVo getByCode(String code);

    DeptVo getByName(String name);

    Dept getByName2(String name);

    /**
     * 删除数据
     * @param id rowid
     * @return
     */
    int delById(String id);

    /**
     * 更新状态
     * @param state 状态
     * @param id ID
     * @return
     */
    int updateState(@Param("state") String state,@Param("id") String id);

}
