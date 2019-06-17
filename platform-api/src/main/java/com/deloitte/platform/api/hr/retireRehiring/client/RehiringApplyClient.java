package com.deloitte.platform.api.hr.retireRehiring.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.retireRehiring.param.RehiringApplyQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.param.RehiringQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RecordVo;
import com.deloitte.platform.api.hr.retireRehiring.vo.RehiringApplyForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RehiringApplyVo;
import com.deloitte.platform.api.hr.retireRehiring.vo.RetireApplyProcessForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :  RetireRehiringApply控制器接口
 * @Modified :
 */
public interface RehiringApplyClient {

    public static final String path="/hr/retire-rehiring-apply";


    /**
     *  POST---新增
     * @param retireRehiringApplyForm
     * @return
     */
    @PostMapping(value = path)
    Result addOrUpdate(@Valid @ModelAttribute RehiringApplyForm retireRehiringApplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, retireRehiringApplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RehiringApplyForm retireRehiringApplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<RehiringApplyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   retireRehiringApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list")
    Result<List<RehiringApplyVo>> list(@Valid @RequestBody RehiringApplyQueryForm retireRehiringApplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  retireRehiringApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page")
    Result<IPage<RehiringApplyVo>> search(@Valid @RequestBody RehiringApplyQueryForm retireRehiringApplyQueryForm);

    @ApiOperation(value = "列表查询退休返聘查询（人力资源处）", notes = "列表查询退休返聘查询（人力资源处）")
    @PostMapping(value = path+"/getPersonList")
    Result<List<RecordVo>> getPersonList(@Valid @RequestBody @ApiParam(name = "retireRehiringApplyQueryForm", value = "RetireRehiringApply查询参数", required = true) RehiringQueryForm queryForm);

    @PostMapping(value = path + "/approveProcess")
    Result approveProcess(@Valid @RequestBody @ApiParam(name = "RemindProcessForm", value = "流程提交参数", required = true) RetireApplyProcessForm retireApplyProcessForm);

    @ApiOperation(value = "导出退休返聘（人力资源处）", notes = "导出退休返聘（人力资源处）")
    @GetMapping(value = path+"/exportPersonList")
    void exportPersonList(@Valid @ModelAttribute RehiringQueryForm queryForm, HttpServletRequest request, HttpServletResponse response);
}
