package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccTalentProjectQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTalentProjectForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTalentProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccTalentProject控制器接口
 * @Modified :
 */
public interface GccTalentProjectClient {

    public static final String path="/hr/gcc-talent-project";


    /**
     *  POST---新增
     * @param gccTalentProjectForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccTalentProjectForm gccTalentProjectForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccTalentProjectForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccTalentProjectForm gccTalentProjectForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccTalentProjectVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccTalentProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccTalentProjectVo>> list(@Valid @RequestBody GccTalentProjectQueryForm gccTalentProjectQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccTalentProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccTalentProjectVo>> search(@Valid @RequestBody GccTalentProjectQueryForm gccTalentProjectQueryForm);

    /**
     * post---导出
     * @param  gccTalentProjectQueryForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result exportExcel(@Valid @ModelAttribute GccTalentProjectQueryForm gccTalentProjectQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     *  Delete---批量删除
     * @param  form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);

}
