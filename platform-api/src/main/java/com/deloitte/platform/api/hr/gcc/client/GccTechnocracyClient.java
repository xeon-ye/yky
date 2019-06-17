package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccTechnocracyExportForm;
import com.deloitte.platform.api.hr.gcc.param.GccTechnocracyQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTechnocracyForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTechnocracyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccTechnocracy控制器接口
 * @Modified :
 */
public interface GccTechnocracyClient {

    public static final String path="/hr/gcc-technocracy";


    /**
     *  POST---新增
     * @param gccTechnocracyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccTechnocracyForm gccTechnocracyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccTechnocracyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccTechnocracyForm gccTechnocracyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccTechnocracyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccTechnocracyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccTechnocracyVo>> list(@Valid @RequestBody GccTechnocracyQueryForm gccTechnocracyQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccTechnocracyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccTechnocracyVo>> search(@Valid @RequestBody GccTechnocracyQueryForm gccTechnocracyQueryForm);


    /**
     *  POST----导入模版
     * @param  file,type
     * @return
     */
    @PostMapping(value = path+"/import")
    Result importExcel(@Valid @ModelAttribute  MultipartFile file,@ModelAttribute("type")String type) throws IOException;

    /**
     *  POST----导出模版
     * @param  gccTechnocracyExportForm
     * @return
     */
    @GetMapping(value = path+"/export/selectList")
    Result exportExcel(@Valid @ModelAttribute  GccTechnocracyExportForm gccTechnocracyExportForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     *  POST---新增
     * @param gccTechnocracyForm
     * @return
     */
    @PostMapping(value = path+"/addOrUpdate")
    Result addOrUpdate(@Valid  @RequestBody GccTechnocracyForm gccTechnocracyForm);


    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);
}
