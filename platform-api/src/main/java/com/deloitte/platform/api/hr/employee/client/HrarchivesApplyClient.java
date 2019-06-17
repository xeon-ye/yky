package com.deloitte.platform.api.hr.employee.client;

import com.deloitte.platform.api.hr.employee.param.HrarchivesApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.HrarchivesApplyForm;
import com.deloitte.platform.api.hr.employee.vo.HrarchivesApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-21
 * @Description :  HrarchivesApply控制器接口
 * @Modified :
 */
public interface HrarchivesApplyClient {

    public static final String path="/hr/hrarchives-apply";


    /**
     *  POST---新增
     * @param hrarchivesApplyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrarchivesApplyForm hrarchivesApplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrarchivesApplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrarchivesApplyForm hrarchivesApplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrarchivesApplyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrarchivesApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrarchivesApplyVo>> list(@Valid @RequestBody HrarchivesApplyQueryForm hrarchivesApplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrarchivesApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrarchivesApplyVo>> search(@Valid @RequestBody HrarchivesApplyQueryForm hrarchivesApplyQueryForm);
}
