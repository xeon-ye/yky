package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.SubordinateAccountQueryForm;
import com.deloitte.platform.api.isump.vo.SubordinateAccountForm;
import com.deloitte.platform.api.isump.vo.SubordinateAccountVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  SubordinateAccount控制器接口
 * @Modified :
 */
public interface SubordinateAccountClient {

    public static final String path="/isump/subordinate-account";


    /**
     *  POST---新增
     * @param subordinateAccountForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SubordinateAccountForm subordinateAccountForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, subordinateAccountForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody SubordinateAccountForm subordinateAccountForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SubordinateAccountVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   subordinateAccountForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<SubordinateAccountVo>> list(@Valid @RequestBody SubordinateAccountQueryForm subordinateAccountQueryForm);


    /**
     *  POST----复杂查询
     * @param  subordinateAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SubordinateAccountVo>> search(@Valid @RequestBody SubordinateAccountQueryForm subordinateAccountQueryForm);
}
