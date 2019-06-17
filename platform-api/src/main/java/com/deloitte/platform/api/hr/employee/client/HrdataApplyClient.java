package com.deloitte.platform.api.hr.employee.client;

import com.deloitte.platform.api.hr.employee.param.HrdataApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.HrdataApplyForm;
import com.deloitte.platform.api.hr.employee.vo.HrdataApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-22
 * @Description :  HrdataApply控制器接口
 * @Modified :
 */
public interface HrdataApplyClient {

    public static final String path="/hr/hrdata-apply";


    /**
     *  POST---新增
     * @param hrdataApplyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrdataApplyForm hrdataApplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrdataApplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrdataApplyForm hrdataApplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrdataApplyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrdataApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrdataApplyVo>> list(@Valid @RequestBody HrdataApplyQueryForm hrdataApplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrdataApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrdataApplyVo>> search(@Valid @RequestBody HrdataApplyQueryForm hrdataApplyQueryForm);
}
