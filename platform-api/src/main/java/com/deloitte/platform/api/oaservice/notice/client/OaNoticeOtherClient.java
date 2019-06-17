package com.deloitte.platform.api.oaservice.notice.client;

import com.deloitte.platform.api.oaservice.notice.param.OaNoticeOtherQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeOtherForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeOtherVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :  OaNoticeOther控制器接口
 * @Modified :
 */
public interface OaNoticeOtherClient {

    public static final String path="/oaservice/oa-noticeother";

    /**
     *  POST---新增
     * @param oaNoticeOtherForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaNoticeOtherForm oaNoticeOtherForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaNoticeOtherForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaNoticeOtherForm oaNoticeOtherForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaNoticeOtherVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaNoticeOtherQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaNoticeOtherVo>> list(@Valid @RequestBody OaNoticeOtherQueryForm oaNoticeOtherQueryForm);


    /**
     *  POST----复杂查询
     *  门户数据查询
     * @param  oaNoticeOtherQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaNoticeOtherVo>> search(@Valid @RequestBody OaNoticeOtherQueryForm oaNoticeOtherQueryForm, String token);

    /**
     *  POST----复杂查询
     * @param  oaNoticeOtherQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/permission/conditions")
    Result<IPage<OaNoticeOtherVo>> searchWithPermission(@Valid @RequestBody OaNoticeOtherQueryForm oaNoticeOtherQueryForm,
                                                        String token);

    /**
     * GET----首页数据
     * @param num
     * @return
     */
    @GetMapping(value = path+"/list/home")
    Result<List<OaNoticeOtherVo>> home(@RequestParam(name = "num", defaultValue = "3", required = false) Integer num, @RequestParam(name = "typeCode", required = true) String typeCode, String token);

}
