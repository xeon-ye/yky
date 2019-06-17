package com.deloitte.platform.api.oaservice.notice.client;

import com.deloitte.platform.api.oaservice.notice.param.OaMeetingRecordQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingRecordForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingRecordVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :  OaMeetingRecord控制器接口
 * @Modified :
 */
public interface OaMeetingRecordClient {

    public static final String path="/oaservice/oa-meeting-record";


    /**
     *  POST---新增
     * @param oaMeetingRecordForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaMeetingRecordForm oaMeetingRecordForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaMeetingRecordForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaMeetingRecordForm oaMeetingRecordForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaMeetingRecordVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaMeetingRecordQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaMeetingRecordVo>> list(@Valid @RequestBody OaMeetingRecordQueryForm oaMeetingRecordQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaMeetingRecordQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaMeetingRecordVo>> search(@Valid @RequestBody OaMeetingRecordQueryForm oaMeetingRecordQueryForm);

    /**
     * GET----首页数据
     * @param num
     * @return
     */
    @GetMapping(value = path+"/list/home")
    Result<List<OaMeetingRecordVo>> home(@RequestParam(name = "num", defaultValue = "3", required = false) Integer num);

}
