package com.deloitte.platform.api.hr.gcc.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccFundsProjectQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccFundsProjectForm;
import com.deloitte.platform.api.hr.gcc.vo.GccFundsProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


/**
 * @Author : LJH
 * @Date : Create in 2019-05-13
 * @Description :  GccFundsProject控制器接口
 * @Modified :
 */
public interface GccFundsProjectClient {

    public static final String path="/hr/gcc-funds-project";


    /**
     *  POST---新增
     * @param gccFundsProjectForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccFundsProjectForm gccFundsProjectForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccFundsProjectForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccFundsProjectForm gccFundsProjectForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccFundsProjectVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccFundsProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccFundsProjectVo>> list(@Valid @RequestBody GccFundsProjectQueryForm gccFundsProjectQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccFundsProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccFundsProjectVo>> search(@Valid @RequestBody GccFundsProjectQueryForm gccFundsProjectQueryForm);

    /**
     *  POST----导入模版
     * @param  file,type
     * @return
     */
    @PostMapping(value = path+"/import")
    Result importExcel(@Valid @ModelAttribute MultipartFile file) throws IOException;

    /**
     *  POST----导出模版
     * @param  gccFundsProjectQueryForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result exportExcel(@Valid @ModelAttribute GccFundsProjectQueryForm gccFundsProjectQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


}
