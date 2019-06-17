package com.deloitte.platform.api.hr.retire.client;

import com.deloitte.platform.api.hr.retire.param.RetireOrdinaryQueryForm;
import com.deloitte.platform.api.hr.retire.vo.RetireOrdinaryForm;
import com.deloitte.platform.api.hr.retire.vo.RetireOrdinaryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-07
 * @Description :  RetireOrdinary控制器接口
 * @Modified :
 */
public interface RetireOrdinaryClient {

    public static final String path="/hr/retire-ordinary";


    /**
     *  POST---新增
     * @param retireOrdinaryForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute RetireOrdinaryForm retireOrdinaryForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, retireOrdinaryForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RetireOrdinaryForm retireOrdinaryForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<RetireOrdinaryVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   retireOrdinaryQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<RetireOrdinaryVo>> list(@Valid @RequestBody RetireOrdinaryQueryForm retireOrdinaryQueryForm);


    /**
     *  POST----复杂查询
     * @param  retireOrdinaryQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<RetireOrdinaryVo>> search(@Valid @RequestBody RetireOrdinaryQueryForm retireOrdinaryQueryForm);
}
