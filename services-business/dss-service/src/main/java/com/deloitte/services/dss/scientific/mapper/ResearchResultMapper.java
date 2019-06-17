package com.deloitte.services.dss.scientific.mapper;

import com.deloitte.platform.api.dss.scientific.vo.ResultVo;

import java.util.List;

public interface ResearchResultMapper {
//    科研成果接口
    List<ResultVo> queryResearch(String string);
}
