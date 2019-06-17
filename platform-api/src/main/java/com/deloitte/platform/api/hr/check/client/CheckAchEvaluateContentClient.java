package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckAchEvaluateContentQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateContentBatchForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateContentForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateContentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :  CheckAchEvaluateContent控制器接口
 * @Modified :
 */
public interface CheckAchEvaluateContentClient {

    public static final String path="/hr/check-ach-evaluate-content";


    /**
     *  POST---新增
     * @param checkAchEvaluateContentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckAchEvaluateContentForm checkAchEvaluateContentForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkAchEvaluateContentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckAchEvaluateContentForm checkAchEvaluateContentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckAchEvaluateContentVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkAchEvaluateContentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckAchEvaluateContentVo>> list(@Valid @RequestBody CheckAchEvaluateContentQueryForm checkAchEvaluateContentQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkAchEvaluateContentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckAchEvaluateContentVo>> search(@Valid @RequestBody CheckAchEvaluateContentQueryForm checkAchEvaluateContentQueryForm);

    /**
     * 批量保存
     * @param checkAchEvaluateContentBatchForm
     * @return
     */
    @PostMapping(value = path+"/batchSave")
    Result batchSave(@Valid @RequestBody CheckAchEvaluateContentBatchForm checkAchEvaluateContentBatchForm);
}
