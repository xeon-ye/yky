package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpTechRewardQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTechRewardForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTechRewardVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :  ZpcpTechReward控制器接口
 * @Modified :
 */
public interface ZpcpTechRewardClient {

    public static final String path="/hr/zpcp-tech-reward";


    /**
     *  POST---新增
     * @param zpcpTechRewardForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpTechRewardForm zpcpTechRewardForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpTechRewardForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpTechRewardForm zpcpTechRewardForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpTechRewardVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpTechRewardQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpTechRewardVo>> list(@Valid @RequestBody ZpcpTechRewardQueryForm zpcpTechRewardQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpTechRewardQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpTechRewardVo>> search(@Valid @RequestBody ZpcpTechRewardQueryForm zpcpTechRewardQueryForm);

    /**
     *  POST---批量新增或更新
     * @param techRewardForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<ZpcpTechRewardForm> techRewardForms);


    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);
}
