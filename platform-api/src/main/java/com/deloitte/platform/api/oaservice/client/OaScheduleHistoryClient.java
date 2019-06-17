package com.deloitte.platform.api.oaservice.client;

import com.deloitte.platform.api.oaservice.param.OaScheduleHistoryQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleHistoryForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleHistoryVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description :  OaScheduleHistory控制器接口
 * @Modified :
 */
public interface OaScheduleHistoryClient {

    public static final String path="/oaservice/scheduleHistory";


    /**
     *  POST---新增
     * @param oaScheduleHistoryForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaScheduleHistoryForm oaScheduleHistoryForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") String id);

    /**
     *  Patch----部分更新
     * @param  id, oaScheduleHistoryForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") String id, @Valid @RequestBody OaScheduleHistoryForm oaScheduleHistoryForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaScheduleHistoryVo> get(@PathVariable(value="id") String id);


    /**
     *  POST----列表查询
     * @param   oaScheduleHistoryQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaScheduleHistoryVo>> list(@Valid @RequestBody OaScheduleHistoryQueryForm oaScheduleHistoryQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaScheduleHistoryQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaScheduleHistoryVo>> search(@Valid @RequestBody OaScheduleHistoryQueryForm oaScheduleHistoryQueryForm);
}
