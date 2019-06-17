package com.deloitte.platform.api.fssc.report.client;

import com.deloitte.platform.api.fssc.report.param.ReportMergeRelationQueryForm;
import com.deloitte.platform.api.fssc.report.vo.ReportMergeRelationForm;
import com.deloitte.platform.api.fssc.report.vo.ReportMergeRelationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :  ReportMergeRelation控制器接口
 * @Modified :
 */
public interface ReportMergeRelationClient {

    public static final String path="/fssc/report-merge-relation";


    /**
     *  POST---新增
     * @param reportMergeRelationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ReportMergeRelationForm reportMergeRelationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, reportMergeRelationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ReportMergeRelationForm reportMergeRelationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ReportMergeRelationVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   reportMergeRelationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ReportMergeRelationVo>> list(@Valid @RequestBody ReportMergeRelationQueryForm reportMergeRelationQueryForm);


    /**
     *  POST----复杂查询
     * @param  reportMergeRelationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ReportMergeRelationVo>> search(@Valid @RequestBody ReportMergeRelationQueryForm reportMergeRelationQueryForm);
}
