package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckExcellentRatioQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckExcellentRatioForm;
import com.deloitte.platform.api.hr.check.vo.CheckExcellentRatioInfoVo;
import com.deloitte.platform.api.hr.check.vo.CheckExcellentRatioVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckExcellentRatio控制器接口
 * @Modified :
 */
public interface CheckExcellentRatioClient {

    public static final String path="/hr/check-excellent-ratio";


    /**
     *  POST---新增
     * @param checkExcellentRatioForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckExcellentRatioForm checkExcellentRatioForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkExcellentRatioForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckExcellentRatioForm checkExcellentRatioForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckExcellentRatioVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkExcellentRatioQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckExcellentRatioVo>> list(@Valid @RequestBody CheckExcellentRatioQueryForm checkExcellentRatioQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkExcellentRatioQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckExcellentRatioVo>> search(@Valid @RequestBody CheckExcellentRatioQueryForm checkExcellentRatioQueryForm);

    /**
     *  POST----复杂查询优秀设置详情
     * @param  checkExcellentRatioQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/getCheckExellentRatioInfo")
    Result<IPage<CheckExcellentRatioInfoVo>> getCheckExellentRatioInfo(@Valid @RequestBody CheckExcellentRatioQueryForm checkExcellentRatioQueryForm);

    /**
     *  Patch----部分更新
     * @param   ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);
}
