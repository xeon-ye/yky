package com.deloitte.services.portal.mapper;

import com.deloitte.services.portal.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 通知公告表 Mapper 接口
 * </p>
 *
 * @author yidaojun
 * @since 2019-04-03
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    List<Notice> getHomeList(int num);

}
