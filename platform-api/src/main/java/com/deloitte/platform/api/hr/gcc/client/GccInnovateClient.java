package com.deloitte.platform.api.hr.gcc.client;

import com.deloitte.platform.api.hr.gcc.param.GccInnovateQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccInnovateForm;
import com.deloitte.platform.api.hr.gcc.vo.GccInnovateVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccInnovate控制器接口
 * @Modified :
 */
public interface GccInnovateClient {

    public static final String path="/hr/gcc-innovate";


    /**
     *  POST---新增
     * @param gccInnovateForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccInnovateForm gccInnovateForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccInnovateForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccInnovateForm gccInnovateForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccInnovateVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccInnovateQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccInnovateVo>> list(@Valid @RequestBody GccInnovateQueryForm gccInnovateQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccInnovateQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccInnovateVo>> search(@Valid @RequestBody GccInnovateQueryForm gccInnovateQueryForm);
}
