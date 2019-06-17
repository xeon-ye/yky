package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccUnitRecommedQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccUnitRecommedForm;
import com.deloitte.platform.api.hr.gcc.vo.GccUnitRecommedVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccUnitRecommed控制器接口
 * @Modified :
 */
public interface GccUnitRecommedClient {

    public static final String path="/hr/gcc-unit-recommed";


    /**
     *  POST---新增
     * @param gccUnitRecommedForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccUnitRecommedForm gccUnitRecommedForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccUnitRecommedForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccUnitRecommedForm gccUnitRecommedForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccUnitRecommedVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccUnitRecommedQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccUnitRecommedVo>> list(@Valid @RequestBody GccUnitRecommedQueryForm gccUnitRecommedQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccUnitRecommedQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccUnitRecommedVo>> search(@Valid @RequestBody GccUnitRecommedQueryForm gccUnitRecommedQueryForm);

    /**
     *  GET----根据ID获取
     * @param  userId
     * @return
     */
    @GetMapping(value = path+"/getByUserId/{userId}")
    Result<GccUnitRecommedVo> getByUserId(@PathVariable(value = "userId") long userId);

    /**
     *  POST---新增
     * @param gccUnitRecommedForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdateByUserId")
    Result saveOrUpdateByUserId(@Valid @ModelAttribute GccUnitRecommedForm gccUnitRecommedForm);
}
