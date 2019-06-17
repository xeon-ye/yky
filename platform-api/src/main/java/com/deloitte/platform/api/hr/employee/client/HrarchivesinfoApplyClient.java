package com.deloitte.platform.api.hr.employee.client;

import com.deloitte.platform.api.hr.employee.param.HrarchivesinfoApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.HrarchivesinfoApplyForm;
import com.deloitte.platform.api.hr.employee.vo.HrarchivesinfoApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-20
 * @Description :  HrarchivesinfoApply控制器接口
 * @Modified :
 */
public interface HrarchivesinfoApplyClient {

    public static final String path="/hr/hrarchivesinfo-apply";


    /**
     *  POST---新增
     * @param hrarchivesinfoApplyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrarchivesinfoApplyForm hrarchivesinfoApplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrarchivesinfoApplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody HrarchivesinfoApplyForm hrarchivesinfoApplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrarchivesinfoApplyVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   hrarchivesinfoApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrarchivesinfoApplyVo>> list(@Valid @RequestBody HrarchivesinfoApplyQueryForm hrarchivesinfoApplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrarchivesinfoApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrarchivesinfoApplyVo>> search(@Valid @RequestBody HrarchivesinfoApplyQueryForm hrarchivesinfoApplyQueryForm);
}
