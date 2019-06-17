package com.deloitte.platform.api.oaservice.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.param.OaScheduleShareConfigQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleShareConfigForm;
import com.deloitte.platform.api.oaservice.vo.OaScheduleShareConfigVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-23
 * @Description :  OaScheduleShareConfig控制器接口
 * @Modified :
 */
public interface OaScheduleShareConfigClient {

    public static final String path="/oaservice/schedule/shareConfig";


    /**
     *  POST---批量保存
     * @param oaScheduleShareConfigForm
     * @return
     */
    @PostMapping(value = path+"/save")
    Result save(@Valid @RequestBody OaScheduleShareConfigForm[] oaScheduleShareConfigForm);
    /**
     *  POST---新增
     * @param oaScheduleShareConfigForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaScheduleShareConfigForm oaScheduleShareConfigForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaScheduleShareConfigForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaScheduleShareConfigForm oaScheduleShareConfigForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaScheduleShareConfigVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaScheduleShareConfigQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaScheduleShareConfigVo>> list(@Valid @RequestBody OaScheduleShareConfigQueryForm oaScheduleShareConfigQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaScheduleShareConfigQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaScheduleShareConfigVo>> search(@Valid @RequestBody OaScheduleShareConfigQueryForm oaScheduleShareConfigQueryForm);
}
