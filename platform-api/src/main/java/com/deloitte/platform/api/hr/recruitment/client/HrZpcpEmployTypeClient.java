package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpEmployTypeQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpEmployTypeForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpEmployTypeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :  HrZpcpEmployType控制器接口
 * @Modified :
 */
public interface HrZpcpEmployTypeClient {

    public static final String path="/hr/recruitment/hr-zpcp-employ-type";


    /**
     *  POST---新增
     * @param hrZpcpEmployTypeForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrZpcpEmployTypeForm hrZpcpEmployTypeForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrZpcpEmployTypeForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrZpcpEmployTypeForm hrZpcpEmployTypeForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrZpcpEmployTypeVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrZpcpEmployTypeQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrZpcpEmployTypeVo>> list(@Valid @RequestBody HrZpcpEmployTypeQueryForm hrZpcpEmployTypeQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrZpcpEmployTypeQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrZpcpEmployTypeVo>> search(@Valid @RequestBody HrZpcpEmployTypeQueryForm hrZpcpEmployTypeQueryForm);

    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);
}
