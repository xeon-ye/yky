package com.deloitte.platform.api.hr.retire.client;

import com.deloitte.platform.api.hr.retire.param.RetireOrdinaryinfoQueryForm;
import com.deloitte.platform.api.hr.retire.vo.RetireOrdinaryinfoForm;
import com.deloitte.platform.api.hr.retire.vo.RetireOrdinaryinfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-07
 * @Description :  RetireOrdinaryinfo控制器接口
 * @Modified :
 */
public interface RetireOrdinaryinfoClient {

    public static final String path="/hr/retire-ordinaryinfo";


    /**
     *  POST---新增
     * @param retireOrdinaryinfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute RetireOrdinaryinfoForm retireOrdinaryinfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, retireOrdinaryinfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RetireOrdinaryinfoForm retireOrdinaryinfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<RetireOrdinaryinfoVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   retireOrdinaryinfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<RetireOrdinaryinfoVo>> list(@Valid @RequestBody RetireOrdinaryinfoQueryForm retireOrdinaryinfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  retireOrdinaryinfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<RetireOrdinaryinfoVo>> search(@Valid @RequestBody RetireOrdinaryinfoQueryForm retireOrdinaryinfoQueryForm);
}
