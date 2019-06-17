package com.deloitte.platform.api.oaservice.notice.client;

import com.deloitte.platform.api.oaservice.notice.param.OaInfochangeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaInfochangeForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaInfochangeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :  OaInfochange控制器接口
 * @Modified :
 */
public interface OaInfochangeClient {

    public static final String path="/oaservice/oa-infochange";


    /**
     *  POST---新增
     * @param oaInfochangeForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaInfochangeForm oaInfochangeForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaInfochangeForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaInfochangeForm oaInfochangeForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaInfochangeVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaInfochangeQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaInfochangeVo>> list(@Valid @RequestBody OaInfochangeQueryForm oaInfochangeQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaInfochangeQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaInfochangeVo>> search(@Valid @RequestBody OaInfochangeQueryForm oaInfochangeQueryForm);

    /**
     * GET----首页数据
     * @param num
     * @return
     */
    @GetMapping(value = path+"/list/home")
    Result<List<OaInfochangeVo>> home(@RequestParam(name = "num", defaultValue = "3", required = false) Integer num);

}
