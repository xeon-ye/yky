package com.deloitte.platform.api.oaservice.applycenter.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.applycenter.param.SendProcessTaskQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskByBpmForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-09
 * @Description :  SendProcessTask控制器接口
 * @Modified :
 */
public interface SendProcessTaskClient {

    public static final String path = "/oa/send-process-task";


    /**
     * POST---新增
     *
     * @param sendProcessTaskForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SendProcessTaskForm sendProcessTaskForm);

    /**
     * 内部系统通过BPM生成待阅信息
     *
     * @param sendProcessTaskByBpmForm
     * @return
     */
    @PostMapping(value = path + "/bpm")
    Result addByBpm(@Valid @RequestBody @ApiParam(name = "sendProcessTaskByBpmForm", value = "通过BPM生成待阅信息", required = true) SendProcessTaskByBpmForm sendProcessTaskByBpmForm);

    /**
     * Delete---删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = path + "/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     * Patch----部分更新
     *
     * @param id, sendProcessTaskForm
     * @return
     */
    @PatchMapping(value = path + "/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody SendProcessTaskForm sendProcessTaskForm);

    @GetMapping(value = path)
    Result updateById(@RequestParam(name="id")long id);
    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<SendProcessTaskVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     *
     * @param sendProcessTaskQueryForm
     * @return
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<SendProcessTaskVo>> list(@Valid @RequestBody SendProcessTaskQueryForm sendProcessTaskQueryForm);


    /**
     * POST----复杂查询
     *
     * @param sendProcessTaskQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<GdcPage<SendProcessTaskVo>> search(@Valid @RequestBody SendProcessTaskQueryForm sendProcessTaskQueryForm);

    /**
     * POST----复杂查询
     *
     * @param sendProcessTaskQueryForm
     * @return
     */
    @PostMapping(value = path + "/syncData")
    Result syncData(@Valid @RequestBody SendProcessTaskQueryForm sendProcessTaskQueryForm);


    /**
     * POST----复杂查询
     *
     * @param sendProcessTaskQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditionsByFrom")
    Result<GdcPage<SendProcessTaskVo>> searchByFrom(@Valid @RequestBody SendProcessTaskQueryForm sendProcessTaskQueryForm);

}
