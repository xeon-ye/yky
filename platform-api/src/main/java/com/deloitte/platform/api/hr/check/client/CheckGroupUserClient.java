package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckGroupUserQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckGroupUserForm;
import com.deloitte.platform.api.hr.check.vo.CheckGroupUserInfoVo;
import com.deloitte.platform.api.hr.check.vo.CheckGroupUserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckGroupUser控制器接口
 * @Modified :
 */
public interface CheckGroupUserClient {

    public static final String path="/hr/check-group-user";


    /**
     *  POST---新增
     * @param checkGroupUserForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckGroupUserForm checkGroupUserForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkGroupUserForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckGroupUserForm checkGroupUserForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckGroupUserVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkGroupUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckGroupUserVo>> list(@Valid @RequestBody CheckGroupUserQueryForm checkGroupUserQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkGroupUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckGroupUserVo>> search(@Valid @RequestBody CheckGroupUserQueryForm checkGroupUserQueryForm);

    /**
     *  POST----查看分组人员详细信息
     * @param  checkGroupUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/getCheckGroupUserInfo")
    Result<IPage<CheckGroupUserInfoVo>> getCheckGroupUserInfo(@Valid @RequestBody CheckGroupUserQueryForm checkGroupUserQueryForm);
}
