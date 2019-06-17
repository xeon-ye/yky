package com.deloitte.platform.api.oaservice.notice.client;

import com.deloitte.platform.api.oaservice.notice.param.OaMeetingArrgeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingArrgeForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingArrgeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :  OaMeetingArrge控制器接口
 * @Modified :
 */
public interface OaMeetingArrgeClient {

    public static final String path="/oaservice/oa-meeting-arrge";


    /**
     *  POST---新增
     * @param oaMeetingArrgeForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaMeetingArrgeForm oaMeetingArrgeForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaMeetingArrgeForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaMeetingArrgeForm oaMeetingArrgeForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaMeetingArrgeVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaMeetingArrgeQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaMeetingArrgeVo>> list(@Valid @RequestBody OaMeetingArrgeQueryForm oaMeetingArrgeQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaMeetingArrgeQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaMeetingArrgeVo>> search(@Valid @RequestBody OaMeetingArrgeQueryForm oaMeetingArrgeQueryForm);

    /**
     * GET----首页数据
     * @param num
     * @return
     */
    @GetMapping(value = path+"/list/home")
    Result<List<OaMeetingArrgeVo>> home(@RequestParam(name = "num", defaultValue = "3", required = false) Integer num);

}
