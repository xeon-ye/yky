package com.deloitte.platform.api.fssc.advance;

import com.deloitte.platform.api.fssc.advance.param.ContactDetailQueryForm;
import com.deloitte.platform.api.fssc.advance.vo.ContactDetailForm;
import com.deloitte.platform.api.fssc.advance.vo.ContactDetailVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description :  BmContactDetail控制器接口
 * @Modified :
 */
public interface ContactDetailClient {

    public static final String path="/advance/bm-contact-detail";


    /**
     *  POST---新增
     * @param contactDetailForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ContactDetailForm contactDetailForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, contactDetailForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ContactDetailForm contactDetailForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ContactDetailVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   bmContactDetailForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ContactDetailVo>> list(@Valid @RequestBody ContactDetailQueryForm contactDetailQueryForm);


    /**
     *  POST----复杂查询
     * @param  contactDetailQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ContactDetailVo>> search(@Valid @RequestBody ContactDetailQueryForm contactDetailQueryForm);
}
