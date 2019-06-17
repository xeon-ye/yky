package com.deloitte.platform.api.hr.check.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckTableTypeQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckTableTypeForm;
import com.deloitte.platform.api.hr.check.vo.CheckTableTypeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckTableType控制器接口
 * @Modified :
 */
public interface CheckTableTypeClient {

    public static final String path="/hr/check-table-type";


    /**
     *  POST---新增
     * @param checkTableTypeForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckTableTypeForm checkTableTypeForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkTableTypeForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckTableTypeForm checkTableTypeForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckTableTypeVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkTableTypeQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckTableTypeVo>> list(@Valid @RequestBody CheckTableTypeQueryForm checkTableTypeQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkTableTypeQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckTableTypeVo>> search(@Valid @RequestBody CheckTableTypeQueryForm checkTableTypeQueryForm);

    /**
     *  Patch----部分更新
     * @param   ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);
}
