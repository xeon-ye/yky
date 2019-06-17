package com.deloitte.services.noticeper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.noticeper.entity.OaNoticePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 公告规章制度权限 Mapper 接口
 * </p>
 *
 * @author jianghaixun
 * @since 2019-05-29
 */
public interface OaNoticePermissionMapper extends BaseMapper<OaNoticePermission> {

    void deletePermissionByObjId(@Param(value = "objId") String objId);

    List<OaNoticePermission> getPermissionsByObjId(@Param(value = "objId") String objId);

}
