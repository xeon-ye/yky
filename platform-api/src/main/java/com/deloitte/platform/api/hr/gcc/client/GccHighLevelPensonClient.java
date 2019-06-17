package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccHighLevelPensonQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccHighLevelPensonQueryForm2;
import com.deloitte.platform.api.hr.gcc.vo.GccHighLevelFundsVo;
import com.deloitte.platform.api.hr.gcc.vo.GccHighLevelPensonForm;
import com.deloitte.platform.api.hr.gcc.vo.GccHighLevelPensonHighVo;
import com.deloitte.platform.api.hr.gcc.vo.GccHighLevelPensonVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-09
 * @Description :  GccHighLevelPenson控制器接口
 * @Modified :
 */
public interface GccHighLevelPensonClient {

    public static final String path="/hr/gcc-high-level-penson";


    /**
     *  POST---新增
     * @param gccHighLevelPensonForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccHighLevelPensonForm gccHighLevelPensonForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccHighLevelPensonForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccHighLevelPensonForm gccHighLevelPensonForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccHighLevelPensonVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccHighLevelPensonQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccHighLevelPensonVo>> list(@Valid @RequestBody GccHighLevelPensonQueryForm gccHighLevelPensonQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccHighLevelPensonQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccHighLevelPensonVo>> search(@Valid @RequestBody GccHighLevelPensonQueryForm gccHighLevelPensonQueryForm);


    /**
     *  POST----导入模版
     * @param  file,type
     * @return
     */
    @PostMapping(value = path+"/import")
    Result importExcel(@Valid @ModelAttribute MultipartFile file) throws IOException;

    /**
     *  POST----导出模版
     * @param  gccHighLevelPensonQueryForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result exportExcel(@Valid @ModelAttribute GccHighLevelPensonQueryForm gccHighLevelPensonQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     *  POST----列表查询
     * @param   gccHighLevelPensonQueryForm
     * @return
     */
    @PostMapping(value = path+"/listHigh/conditions")
    Result<List<GccHighLevelPensonHighVo>> listHigh(@Valid @RequestBody GccHighLevelPensonQueryForm2 gccHighLevelPensonQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccHighLevelPensonQueryForm
     * @return
     */
    @PostMapping(value = path+"/pageHigh/conditions")
    Result<IPage<GccHighLevelPensonHighVo>> searchHigh(@Valid @RequestBody GccHighLevelPensonQueryForm2 gccHighLevelPensonQueryForm);




    /**
     *  POST----高层次人才导出
     * @param  gccHighLevelPensonQueryForm
     * @return
     */
    @GetMapping(value = path+"/exportHigh")
    Result exportHighExcel(@Valid @ModelAttribute GccHighLevelPensonQueryForm2 gccHighLevelPensonQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;



    /**
     *  get----项目人数统计导出
     * @return
     */
    @GetMapping(value = path+"/exportHighNumber")
    Result exportHighNumber( HttpServletRequest request, HttpServletResponse response) throws IOException;



    /**
     *  get----年度单位表导出
     * @return
     */
    @GetMapping(value = path+"/exportYearUnit")
    Object exportYearUnit(HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     *  POST----复杂查询
     * @param  gccHighLevelPensonQueryForm
     * @return
     */
    @PostMapping(value = path+"/searchHighFunds/conditions")
    Result<IPage<GccHighLevelFundsVo>> searchHighFunds(@Valid @RequestBody GccHighLevelPensonQueryForm gccHighLevelPensonQueryForm);

    /**
     *  POST----高层次人才导出
     * @param  gccHighLevelPensonQueryForm
     * @return
     */
    @GetMapping(value = path+"/exportHighFunds")
    Result exportHighFunds(@Valid @ModelAttribute GccHighLevelPensonQueryForm gccHighLevelPensonQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;



    /**
     *  POST----导入模版
     * @param  file,type
     * @return
     */
    @PostMapping(value = path+"/importExcelFunds")
    Result importExcelFunds(@Valid @ModelAttribute MultipartFile file) throws IOException;


    /**
     *  Delete---批量删除
     * @param  form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);


}
