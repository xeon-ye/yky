package com.deloitte.platform.api.hr.vacation.client;

import com.deloitte.platform.api.hr.vacation.param.EliminateleaveQueryForm;
import com.deloitte.platform.api.hr.vacation.vo.EliminateleaveForm;
import com.deloitte.platform.api.hr.vacation.vo.EliminateleaveVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-01
 * @Description :  Eliminateleave控制器接口
 * @Modified :
 */
public interface EliminateleaveClient {

    public static final String path="/hr/eliminateleave";


    /**
     *  POST---新增
     * @param eliminateleaveForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EliminateleaveForm eliminateleaveForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, eliminateleaveForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EliminateleaveForm eliminateleaveForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EliminateleaveVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   eliminateleaveQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EliminateleaveVo>> list(@Valid @RequestBody EliminateleaveQueryForm eliminateleaveQueryForm);


    /**
     *  POST----复杂查询
     * @param  eliminateleaveQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EliminateleaveVo>> search(@Valid @RequestBody EliminateleaveQueryForm eliminateleaveQueryForm);






}
