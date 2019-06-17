package com.deloitte.platform.api.oaservice.notice.client;

import com.deloitte.platform.api.oaservice.notice.param.OaCalenderQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaCalenderForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaCalenderVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :  OaCalender控制器接口
 * @Modified :
 */
public interface OaCalenderClient {

    public static final String path="/oaservice/oa-calender";


    /**
     *  POST---新增
     * @param oaCalenderForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaCalenderForm oaCalenderForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaCalenderForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaCalenderForm oaCalenderForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaCalenderVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaCalenderQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaCalenderVo>> list(@Valid @RequestBody OaCalenderQueryForm oaCalenderQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaCalenderQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaCalenderVo>> search(@Valid @RequestBody OaCalenderQueryForm oaCalenderQueryForm);

    /**
     * GET----首页数据
     * @param num
     * @return
     */
    @GetMapping(value = path+"/list/home")
    Result<List<OaCalenderVo>> home(@RequestParam(name = "num", defaultValue = "3", required = false) Integer num);

}
