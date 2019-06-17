package com.deloitte.platform.api.hr.vacation.client;

import com.deloitte.platform.api.hr.vacation.param.EliminateleaveInfoQueryForm;
import com.deloitte.platform.api.hr.vacation.vo.EliminateleaveInfoForm;
import com.deloitte.platform.api.hr.vacation.vo.EliminateleaveInfoVo;
import com.deloitte.platform.api.hr.vacation.vo.EliminateleaveVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-13
 * @Description :  EliminateleaveInfo控制器接口
 * @Modified :
 */
public interface EliminateleaveInfoClient {

    public static final String path="/hr/eliminateleave-info";


    /**
     *  POST---新增
     * @param eliminateleaveInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EliminateleaveInfoForm eliminateleaveInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, eliminateleaveInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EliminateleaveInfoForm eliminateleaveInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EliminateleaveInfoVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   eliminateleaveInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<EliminateleaveVo> list(@Valid @RequestBody EliminateleaveInfoQueryForm eliminateleaveInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  eliminateleaveInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EliminateleaveInfoVo>> search(@Valid @RequestBody EliminateleaveInfoQueryForm eliminateleaveInfoQueryForm);
}
