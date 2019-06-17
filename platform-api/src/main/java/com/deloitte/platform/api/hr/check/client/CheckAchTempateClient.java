package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckAchTempateQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchTempateForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchTempateInfoVo;
import com.deloitte.platform.api.hr.check.vo.CheckAchTempateVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckAchTempate控制器接口
 * @Modified :
 */
public interface CheckAchTempateClient {

    public static final String path="/hr/check-ach-tempate";


    /**
     *  POST---新增
     * @param checkAchTempateForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckAchTempateForm checkAchTempateForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkAchTempateForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckAchTempateForm checkAchTempateForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckAchTempateVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkAchTempateQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckAchTempateVo>> list(@Valid @RequestBody CheckAchTempateQueryForm checkAchTempateQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkAchTempateQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckAchTempateVo>> search(@Valid @RequestBody CheckAchTempateQueryForm checkAchTempateQueryForm);

    /**
     *  获取业绩考核模板相关信息
     * @param  checkAchTempateQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/getCheckAchTempate")
    Result<IPage<CheckAchTempateInfoVo>> getCheckAchTempate(@Valid @RequestBody CheckAchTempateQueryForm checkAchTempateQueryForm);



    /**
     *  Patch----批量删除
     * @param   ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);
}
