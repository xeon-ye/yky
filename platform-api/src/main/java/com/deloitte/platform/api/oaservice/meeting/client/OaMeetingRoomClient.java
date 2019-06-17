package com.deloitte.platform.api.oaservice.meeting.client;

import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingRoomQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingRoomForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingRoomVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :  OaMeetingRoom控制器接口
 * @Modified :
 */
public interface OaMeetingRoomClient {

    public static final String path="/oaservice/meeting/room";


    /**
     *  POST---新增
     * @param oaMeetingRoomForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaMeetingRoomForm oaMeetingRoomForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaMeetingRoomForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaMeetingRoomForm oaMeetingRoomForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaMeetingRoomVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaMeetingRoomQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaMeetingRoomVo>> list(@Valid @RequestBody OaMeetingRoomQueryForm oaMeetingRoomQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaMeetingRoomQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaMeetingRoomVo>> search(@Valid @RequestBody OaMeetingRoomQueryForm oaMeetingRoomQueryForm);
}
