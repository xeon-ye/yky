package com.deloitte.services.dss.scientific.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.scientific.vo.DeptFundVo;
import com.deloitte.platform.api.dss.scientific.vo.HarvestLineVo;
import com.deloitte.platform.api.dss.scientific.vo.HarvestPaperVo;
import com.deloitte.platform.api.dss.scientific.vo.HarvestResultPatentVo;

import java.util.List;
import java.util.Map;

/**
 * 单位预算执行情况接口
 */
public interface IHarvestService extends IService {

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
