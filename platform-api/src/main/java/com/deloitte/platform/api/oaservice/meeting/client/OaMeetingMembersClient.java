package com.deloitte.platform.api.oaservice.meeting.client;

import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingMembersQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingMembersForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingMembersVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :  OaMeetingMembers控制器接口
 * @Modified :
 */
public interface OaMeetingMembersClient {

    public static final String path="/oaservice/meeting/members";


    /**
     *  POST---新增
     * @param oaMeetingMembersForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaMeetingMembersForm oaMeetingMembersForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaMeetingMembersForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaMeetingMembersForm oaMeetingMembersForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaMeetingMembersVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaMeetingMembersQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaMeetingMembersVo>> list(@Valid @RequestBody OaMeetingMembersQueryForm oaMeetingMembersQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaMeetingMembersQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaMeetingMembersVo>> search(@Valid @RequestBody OaMeetingMembersQueryForm oaMeetingMembersQueryForm);
}
