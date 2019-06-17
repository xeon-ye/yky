package com.deloitte.platform.api.fssc.report.client;

import com.deloitte.platform.api.fssc.report.param.ReportTotalQueryQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportTotalQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportTotalQueryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :  ReportTotalQuery控制器接口
 * @Modified :
 */
public interface ReportTotalQueryClient {

   String path="/fssc/report-total-query";

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ReportTotalQueryVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   reportTotalQueryQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ReportTotalQueryVo>> list(@Valid @RequestBody ReportTotalQueryQueryForm reportTotalQueryQueryForm);


    /**
     *  POST----复杂查询
     * @param  reportTotalQueryQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ReportTotalQueryVo>> search(@Valid @RequestBody ReportTotalQueryQueryForm reportTotalQueryQueryForm);
}
