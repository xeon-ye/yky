package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccSpecialistGroupQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccSpecialistGroupForm;
import com.deloitte.platform.api.hr.gcc.vo.GccSpecialistGroupVo;
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
 * @Description :  GccSpecialistGroup控制器接口
 * @Modified :
 */
public interface GccSpecialistGroupClient {

    public static final String path="/hr/gcc-specialist-group";


    /**
     *  POST---新增
     * @param gccSpecialistGroupForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccSpecialistGroupForm gccSpecialistGroupForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccSpecialistGroupForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccSpecialistGroupForm gccSpecialistGroupForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccSpecialistGroupVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccSpecialistGroupQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccSpecialistGroupVo>> list(@Valid @RequestBody GccSpecialistGroupQueryForm gccSpecialistGroupQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccSpecialistGroupQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccSpecialistGroupVo>> search(@Valid @RequestBody GccSpecialistGroupQueryForm gccSpecialistGroupQueryForm);

    /**
     * post---导出
     * @param  gccSpecialistGroupQueryForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result exportExcel(@Valid @ModelAttribute GccSpecialistGroupQueryForm gccSpecialistGroupQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     *  Delete---删除
     * @param  gcctGroupUserDeleteForm
     * @return
     */
    @PostMapping(value = path+"/batchdelete")
    Result batchdelete(@Valid @RequestBody GccBaseBatchForm gcctGroupUserDeleteForm);



}
