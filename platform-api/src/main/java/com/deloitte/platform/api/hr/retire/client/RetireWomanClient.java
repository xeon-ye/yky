package com.deloitte.platform.api.hr.retire.client;

import com.deloitte.platform.api.hr.retire.param.RetireWomanQueryForm;
import com.deloitte.platform.api.hr.retire.vo.RetireWomanForm;
import com.deloitte.platform.api.hr.retire.vo.RetireWomanVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-11
 * @Description :  RetireWoman控制器接口
 * @Modified :
 */
public interface RetireWomanClient {

    public static final String path="/hr/retire-woman";


    /**
     *  POST---新增
     * @param retireWomanForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute RetireWomanForm retireWomanForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, retireWomanForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RetireWomanForm retireWomanForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<RetireWomanVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   retireWomanQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<RetireWomanVo>> list(@Valid @RequestBody RetireWomanQueryForm retireWomanQueryForm);


    /**
     *  POST----复杂查询
     * @param  retireWomanQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<RetireWomanVo>> search(@Valid @RequestBody RetireWomanQueryForm retireWomanQueryForm);
}
