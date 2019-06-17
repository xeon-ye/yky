package com.deloitte.platform.api.fssc.bpm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.bpm.param.ProcessQueryForm;
import com.deloitte.platform.api.fssc.bpm.vo.ProcessStartForm;
import com.deloitte.platform.api.fssc.bpm.vo.ProcessVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
 * @Description :  Process控制器接口
 * @Modified :
 */
public interface ProcessClient {

    public static final String path="/bpm/process";


    /**
     *  POST---新增
     * @param processStartForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProcessStartForm processStartForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, processStartForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ProcessStartForm processStartForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProcessVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   processQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProcessVo>> list(@Valid @RequestBody ProcessQueryForm processQueryForm);


    /**
     *  POST----复杂查询
     * @param  processQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProcessVo>> search(@Valid @RequestBody ProcessQueryForm processQueryForm);
}
