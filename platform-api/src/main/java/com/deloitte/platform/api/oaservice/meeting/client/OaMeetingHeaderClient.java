package com.deloitte.platform.api.oaservice.meeting.client;

import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingHeaderQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingHeaderForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingHeaderVo;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :  OaMeetingHeader控制器接口
 * @Modified :
 */
public interface OaMeetingHeaderClient {

    public static final String path="/oaservice/meeting";


    /**
     *  POST---新增
     * @param oaMeetingForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaMeetingForm oaMeetingForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, OaMeetingForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaMeetingForm oaMeetingForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaMeetingVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaMeetingHeaderQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaMeetingVo>> list(@Valid @RequestBody OaMeetingHeaderQueryForm oaMeetingHeaderQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaMeetingHeaderQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaMeetingVo>> search(@Valid @RequestBody OaMeetingHeaderQueryForm oaMeetingHeaderQueryForm);
}
