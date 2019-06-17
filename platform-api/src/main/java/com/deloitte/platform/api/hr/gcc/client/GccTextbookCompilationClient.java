package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccTextbookCompilationQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTextbookCompilationForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTextbookCompilationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccTextbookCompilation控制器接口
 * @Modified :
 */
public interface GccTextbookCompilationClient {

    public static final String path="/hr/gcc-textbook-compilation";


    /**
     *  POST---新增
     * @param gccTextbookCompilationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccTextbookCompilationForm gccTextbookCompilationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccTextbookCompilationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccTextbookCompilationForm gccTextbookCompilationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccTextbookCompilationVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccTextbookCompilationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccTextbookCompilationVo>> list(@Valid @RequestBody GccTextbookCompilationQueryForm gccTextbookCompilationQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccTextbookCompilationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccTextbookCompilationVo>> search(@Valid @RequestBody GccTextbookCompilationQueryForm gccTextbookCompilationQueryForm);

    /**
     *  POST---批量新增或更新
     * @param gccTextbookCompilationForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccTextbookCompilationForm> gccTextbookCompilationForms);

    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);
}
