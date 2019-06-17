package com.deloitte.services.dss.scientific.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.dss.scientific.vo.ResultColumnVo;
import com.deloitte.platform.api.dss.scientific.vo.ResultVo;


import java.util.List;


//科研成果接口
public interface ResultMapper  {
    //
    List<ResultVo> queryResult();
    List<ResultColumnVo> queryResultColumn();
}
