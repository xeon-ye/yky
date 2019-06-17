package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccGroupUserQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccGroupUserForm;
import com.deloitte.platform.api.hr.gcc.vo.GccGroupUserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-08
 * @Description :  GccGroupUser控制器接口
 * @Modified :
 */
public interface GccGroupUserClient {

    public static final String path="/hr/gcc-group-user";


    /**
     *  POST---新增
     * @param gccGroupUserForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccGroupUserForm gccGroupUserForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccGroupUserForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccGroupUserForm gccGroupUserForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccGroupUserVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccGroupUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccGroupUserVo>> list(@Valid @RequestBody GccGroupUserQueryForm gccGroupUserQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccGroupUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccGroupUserVo>> search(@Valid @RequestBody GccGroupUserQueryForm gccGroupUserQueryForm);

    /**
     *  Delete---删除
     * @param  gcctGroupUserDeleteForm
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody GccBaseBatchForm gcctGroupUserDeleteForm);


    /**
     * POST---批量新增
     *
     * @param gccGroupUserForms
     * @return
     */
    @PostMapping(value = path+"/addList")
    Result addList(@Valid @RequestBody List<GccGroupUserForm> gccGroupUserForms);
}
