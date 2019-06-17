package com.deloitte.services.dss.scientific.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.dss.scientific.vo.PersonnelVo;
import com.deloitte.platform.api.dss.scientific.vo.ResultColumnVo;
import com.deloitte.platform.api.dss.scientific.vo.ResultVo;

import java.util.List;

/**科研成果数据统计
 * @Author : hmz
 *@Date : Create in 2019-03-01
 */

public interface IResultService extends IService {
    /**
     * 各类科研成果总数
     */
    List<ResultVo> queryResult();

    /**
     * 科研成果数按依托单位统计
     * @return
     */
    List<ResultColumnVo> queryResultColumn();
}
