package com.deloitte.platform.api.hr.staffAllotment.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.staffAllotment.param.HrEmployeeBaseQueryForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrEmployeeBaseForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrEmployeeBaseVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-03
 * @Description :  HrEmployeeBase控制器接口
 * @Modified :
 */
public interface HrEmployeeBaseClient {

    public static final String path="/staffAllotment/hr-employee-base";


    /**
     *  POST---新增
     * @param hrEmployeeBaseForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrEmployeeBaseForm hrEmployeeBaseForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrEmployeeBaseForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrEmployeeBaseForm hrEmployeeBaseForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrEmployeeBaseVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrEmployeeBaseQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrEmployeeBaseVo>> list(@Valid @RequestBody HrEmployeeBaseQueryForm hrEmployeeBaseQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrEmployeeBaseQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrEmployeeBaseVo>> search(@Valid @RequestBody HrEmployeeBaseQueryForm hrEmployeeBaseQueryForm);
}
