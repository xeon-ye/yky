package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccConferenceReportQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccConferenceReportForm;
import com.deloitte.platform.api.hr.gcc.vo.GccConferenceReportVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccConferenceReport控制器接口
 * @Modified :
 */
public interface GccConferenceReportClient {

    public static final String path="/hr/gcc-conference-report";


    /**
     *  POST---新增
     * @param gccConferenceReportForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccConferenceReportForm gccConferenceReportForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccConferenceReportForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccConferenceReportForm gccConferenceReportForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccConferenceReportVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccConferenceReportQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccConferenceReportVo>> list(@Valid @RequestBody GccConferenceReportQueryForm gccConferenceReportQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccConferenceReportQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccConferenceReportVo>> search(@Valid @RequestBody GccConferenceReportQueryForm gccConferenceReportQueryForm);

    /**
     *  POST---批量新增或更新
     * @param gccConferenceReportForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccConferenceReportForm> gccConferenceReportForms);

    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);
}
