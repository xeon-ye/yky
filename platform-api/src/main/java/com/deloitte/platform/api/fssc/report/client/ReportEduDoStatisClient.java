package com.deloitte.platform.api.fssc.report.client;

import com.deloitte.platform.api.fssc.report.param.ReportEduDoStatisQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportEduDoStatisForm;
import com.deloitte.platform.api.fssc.report.vo.ReportEduDoStatisVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description :  ReportEduDoStatis控制器接口
 * @Modified :
 */
public interface ReportEduDoStatisClient {

   String path="/fssc/report-edu-do-statis";


    /**
     *  POST---新增
     * @param reportEduDoStatisForm
     * @return
     */
    @PostMapping(value = path + "/addOrUpdate")
    Result addOrUpdate(@Valid @ModelAttribute ReportEduDoStatisForm reportEduDoStatisForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ReportEduDoStatisVo> get(@PathVariable(value="id") long id);

}
