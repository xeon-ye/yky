package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccTalentProjectDepartQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTalentProjectDepartForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTalentProjectDepartVo;
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
 * @Description :  GccTalentProjectDepart控制器接口
 * @Modified :
 */
public interface GccTalentProjectDepartClient {

    public static final String path="/hr/gcc-talent-project-depart";


    /**
     *  POST---新增
     * @param gccTalentProjectDepartForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccTalentProjectDepartForm gccTalentProjectDepartForm) throws Exception;

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);


    /**
     *  Delete---批量删除
     * @param  form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);


    /**
     *  Patch----部分更新
     * @param  id, gccTalentProjectDepartForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccTalentProjectDepartForm gccTalentProjectDepartForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccTalentProjectDepartVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccTalentProjectDepartQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccTalentProjectDepartVo>> list(@Valid @RequestBody GccTalentProjectDepartQueryForm gccTalentProjectDepartQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccTalentProjectDepartQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccTalentProjectDepartVo>> search(@Valid @RequestBody GccTalentProjectDepartQueryForm gccTalentProjectDepartQueryForm);

    /**
     * post---导出
     * @param  gccTalentProjectDepartQueryForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result exportExcel(@Valid @ModelAttribute GccTalentProjectDepartQueryForm gccTalentProjectDepartQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


}
