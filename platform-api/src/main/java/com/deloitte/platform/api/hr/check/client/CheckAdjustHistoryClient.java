package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckAdjustHistoryQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAdjustHistoryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAdjustHistoryInfoVo;
import com.deloitte.platform.api.hr.check.vo.CheckAdjustHistoryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckAdjustHistory控制器接口
 * @Modified :
 */
public interface CheckAdjustHistoryClient {

    public static final String path="/hr/check-adjust-history";


    /**
     *  POST---新增
     * @param checkAdjustHistoryForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckAdjustHistoryForm checkAdjustHistoryForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkAdjustHistoryForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckAdjustHistoryForm checkAdjustHistoryForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckAdjustHistoryVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkAdjustHistoryQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckAdjustHistoryVo>> list(@Valid @RequestBody CheckAdjustHistoryQueryForm checkAdjustHistoryQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkAdjustHistoryQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckAdjustHistoryVo>> search(@Valid @RequestBody CheckAdjustHistoryQueryForm checkAdjustHistoryQueryForm);

    /**
     * 查询调整历史详情
     * @param queryForm
     * @return
     */
    @PostMapping(value = path+"/getCheckAdjustHistoryInfo")
    Result<List<CheckAdjustHistoryInfoVo>> getCheckAdjustHistoryInfo(@Valid @RequestBody CheckAdjustHistoryQueryForm queryForm);

    /**
     * 保存历史调整
     * @param checkAdjustHistoryQueryFormList
     * @return
     */
    @PostMapping(value = path+"/batchSave")
    Result  batchSave(@Valid @RequestBody List<CheckAdjustHistoryQueryForm> checkAdjustHistoryQueryFormList);
}
