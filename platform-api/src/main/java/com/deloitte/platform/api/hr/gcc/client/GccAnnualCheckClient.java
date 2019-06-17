package com.deloitte.platform.api.hr.gcc.client;

import com.deloitte.platform.api.hr.gcc.param.GccAnnualCheckQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAnnualCheckForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAnnualCheckVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccAnnualCheck控制器接口
 * @Modified :
 */
public interface GccAnnualCheckClient {

    public static final String path="/hr/gcc-annual-check";


    /**
     *  POST---新增
     * @param gccAnnualCheckForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccAnnualCheckForm gccAnnualCheckForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccAnnualCheckForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccAnnualCheckForm gccAnnualCheckForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccAnnualCheckVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccAnnualCheckQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccAnnualCheckVo>> list(@Valid @RequestBody GccAnnualCheckQueryForm gccAnnualCheckQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccAnnualCheckQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccAnnualCheckVo>> search(@Valid @RequestBody GccAnnualCheckQueryForm gccAnnualCheckQueryForm);
}
