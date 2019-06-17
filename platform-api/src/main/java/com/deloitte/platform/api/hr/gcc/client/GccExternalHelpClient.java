package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccExternalHelpQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccExternalReportQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccExternalHelpForm;
import com.deloitte.platform.api.hr.gcc.vo.GccExternalHelpVo;
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
 * @Description :  GccExternalHelp控制器接口
 * @Modified :
 */
public interface GccExternalHelpClient {

    public static final String path="/hr/gcc-external-help";


    /**
     *  POST---新增
     * @param gccExternalHelpForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccExternalHelpForm gccExternalHelpForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccExternalHelpForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccExternalHelpForm gccExternalHelpForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccExternalHelpVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccExternalHelpQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccExternalHelpVo>> list(@Valid @RequestBody GccExternalHelpQueryForm gccExternalHelpQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccExternalHelpQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccExternalHelpVo>> search(@Valid @RequestBody GccExternalHelpQueryForm gccExternalHelpQueryForm);


    /**
     * post---导入 （西部之光）
     * @param  file
     * @return
     */
    @PostMapping(value = path+"/import")
    Result importExcel(@Valid @RequestBody MultipartFile file) throws IOException;


    /**
     * post---导入（援疆）
     * @param  file
     * @return
     */
    @PostMapping(value = path+"/importYj")
    Result importExcelYj(@Valid @RequestBody MultipartFile file) throws IOException;


    /**
     * post---导入（博士服务团）
     * @param  file
     * @return
     */
    @PostMapping(value = path+"/importBs")
    Result importExcelBs(@Valid @RequestBody MultipartFile file) throws IOException;

    /**
     * post---导出
     * @param  gccExternalHelpQueryForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result exportExcel(@Valid @ModelAttribute GccExternalHelpQueryForm gccExternalHelpQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     * post---统计报表
     * @param  gccExternalReportQueryForm
     * @return
     */
    @PostMapping(value = path+"/reportForms")
    Result<Object> reportForms(@Valid @RequestBody GccExternalReportQueryForm gccExternalReportQueryForm);


    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);
}
