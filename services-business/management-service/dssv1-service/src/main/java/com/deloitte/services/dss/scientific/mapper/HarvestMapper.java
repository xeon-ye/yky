package com.deloitte.services.dss.scientific.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.platform.api.dss.scientific.vo.HarvestLineVo;
import com.deloitte.platform.api.dss.scientific.vo.HarvestPaperVo;
import com.deloitte.platform.api.dss.scientific.vo.HarvestResultPatentVo;

import java.util.List;
import java.util.Map;

/**
 * 科研成果主菜单 mapper 接口
 */
public interface HarvestMapper {

    /**
     * 查询专利成果转化数据
     * @param map
     * @return
     */
    List<HarvestResultPatentVo>  queryDeptPatentResult(Map map);


    /**
     * 查询论文数量
     * @param map
     * @return
     */
    List<HarvestPaperVo> queryDeptPaper(Map map);


    /**
     * 查询科研成果折线数据
     * @param map
     * @return
     */
    List<HarvestLineVo> queryBrokenLineData(Map map);
}
