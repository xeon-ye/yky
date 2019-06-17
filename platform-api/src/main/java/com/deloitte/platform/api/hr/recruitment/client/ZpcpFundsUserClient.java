package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpFundsUserQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpFundsUserForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpFundsUserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-09
 * @Description :  ZpcpFundsUser控制器接口
 * @Modified :
 */
public interface ZpcpFundsUserClient {

    public static final String path="/hr/recruitment/zpcp-funds-user";


    /**
     *  POST---新增
     * @param zpcpFundsUserForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpFundsUserForm zpcpFundsUserForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpFundsUserForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpFundsUserForm zpcpFundsUserForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpFundsUserVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpFundsUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpFundsUserVo>> list(@Valid @RequestBody ZpcpFundsUserQueryForm zpcpFundsUserQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpFundsUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpFundsUserVo>> search(@Valid @RequestBody ZpcpFundsUserQueryForm zpcpFundsUserQueryForm);

    /**
     *
     *  条件导出经费使用情况
     *
     * @param
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path+"/list/exportFundsUserList/{infoId}")
    void exportFundsUserList(@Valid @PathVariable Long infoId, HttpServletRequest request, HttpServletResponse response);



    /**
     *  POST----分页查询人员列表包含基本年薪总额和基本安家费总额
     * @param  zpcpFundsUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/searchPage")
    Result<IPage<ZpcpFundsUserVo>> searchPage(@Valid @RequestBody ZpcpFundsUserQueryForm zpcpFundsUserQueryForm);


}
