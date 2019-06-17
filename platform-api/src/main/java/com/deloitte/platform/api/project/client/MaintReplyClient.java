package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.MaintReplyQueryForm;
import com.deloitte.platform.api.project.vo.MaintReplyForm;
import com.deloitte.platform.api.project.vo.MaintReplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  MaintReply控制器接口
 * @Modified :
 */
public interface MaintReplyClient {

    public static final String path="/project/maint-reply";


    /**
     *  POST---新增
     * @param maintReplyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute MaintReplyForm maintReplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, maintReplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody MaintReplyForm maintReplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<MaintReplyVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   maintReplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<MaintReplyVo>> list(@Valid @RequestBody MaintReplyQueryForm maintReplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  maintReplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<MaintReplyVo>> search(@Valid @RequestBody MaintReplyQueryForm maintReplyQueryForm);
}
