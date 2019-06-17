package com.deloitte.platform.api.hr.gcc.client;

import com.deloitte.platform.api.hr.gcc.param.GccAnnualCheckUserQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAnnualCheckUserForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAnnualCheckUserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccAnnualCheckUser控制器接口
 * @Modified :
 */
public interface GccAnnualCheckUserClient {

    public static final String path="/hr/gcc-annual-check-user";


    /**
     *  POST---新增
     * @param gccAnnualCheckUserForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccAnnualCheckUserForm gccAnnualCheckUserForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccAnnualCheckUserForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccAnnualCheckUserForm gccAnnualCheckUserForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccAnnualCheckUserVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccAnnualCheckUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccAnnualCheckUserVo>> list(@Valid @RequestBody GccAnnualCheckUserQueryForm gccAnnualCheckUserQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccAnnualCheckUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccAnnualCheckUserVo>> search(@Valid @RequestBody GccAnnualCheckUserQueryForm gccAnnualCheckUserQueryForm);
}
