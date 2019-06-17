package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchStatusForm;
import com.deloitte.platform.api.hr.gcc.param.GccBaseQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccProjectApplicationQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccProjectApplication2Vo;
import com.deloitte.platform.api.hr.gcc.vo.GccProjectApplicationForm;
import com.deloitte.platform.api.hr.gcc.vo.GccProjectApplicationVo;
import com.deloitte.platform.api.hr.gcc.vo.GccReviewOptionBygroupVo;
import com.deloitte.platform.api.hr.recruitment.vo.DeclareBaseVo;
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
 * @Description :  GccProjectApplication控制器接口
 * @Modified :
 */
public interface GccProjectApplicationClient {

    public static final String path="/hr/gcc-project-application";


    /**
     *  POST---新增
     * @param gccProjectApplicationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccProjectApplicationForm gccProjectApplicationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccProjectApplicationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccProjectApplicationForm gccProjectApplicationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccProjectApplicationVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccProjectApplicationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccProjectApplicationVo>> list(@Valid @RequestBody GccProjectApplicationQueryForm gccProjectApplicationQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccProjectApplicationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccProjectApplicationVo>> search(@Valid @RequestBody GccProjectApplicationQueryForm gccProjectApplicationQueryForm);



    /**
     *  POST----列表查询
     * @param   gccProjectApplicationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/listApplication")
    Result<List<GccProjectApplication2Vo>> listApplication(@Valid @RequestBody GccProjectApplicationQueryForm gccProjectApplicationQueryForm);


    /**
     *  POST----列表查询
     * @param   gccProjectApplicationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/searchApplication")
    Result<IPage<GccProjectApplication2Vo>> searchApplication(@Valid @RequestBody GccProjectApplicationQueryForm gccProjectApplicationQueryForm);



    /**
     * post---导出
     * @param  gccProjectApplicationQueryForm
     * @return
     */
    @GetMapping(value = path+"/exportExcel")
    Result exportExcel(@Valid @ModelAttribute GccProjectApplicationQueryForm gccProjectApplicationQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     * 审核接口
     */
    /**
     *  Patch----部分更新
     * @param  id, gccProjectApplicationForm
     * @return
     */
    @GetMapping(value = path+"/{id}/{status}")
    Result updateByStatus(@PathVariable(value = "id") long id,@PathVariable(value = "status") String status );


    /**
     *  根据ID导出申报人详情
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/exportDeclareDetail/{id}")
    Result exportDeclareDetail(@PathVariable(value="id") Long id, HttpServletRequest request, HttpServletResponse response);



    /**
     * GET----员工申报获取申报人基本信息
     */
    @GetMapping(value = path + "/getDeclareDetail/{userId}")
    Result<DeclareBaseVo> getDeclareDetail(@PathVariable(value = "userId") Long userId);



    /**
     *  POST----查询审核结果
     * @param   queryForm
     * @return
     */
    @PostMapping(value = path+"/listByGroup/conditions")
    Result<List<GccReviewOptionBygroupVo>> listByGroup(@Valid @RequestBody GccBaseQueryForm queryForm);


    /**
     *  POST----查询审核结果
     * @param  queryForm
     * @return
     */
    @PostMapping(value = path+"/pageByGroup/conditions")
    Result<IPage<GccReviewOptionBygroupVo>> searchByGroup(@Valid @RequestBody GccBaseQueryForm queryForm);


    /**
     * 批量修改状态
     * @param form
     * @return
     */
    @PostMapping(value = path+"/updateListByStatus")
    Result updateListByStatus(@Valid @RequestBody GccBaseBatchStatusForm form);


    /**
     *  POST----导出一览表
     * @param  queryForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result  export(@Valid @ModelAttribute GccBaseQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;



    /**
     *  POST----导出结果表
     * @param   queryForm
     * @return
     */
    @GetMapping(value = path+"/exportResult")
    Result exportResult(@Valid @ModelAttribute GccBaseQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     *  POST----导出院校评审表
     * @param   queryForm
     * @return
     */
    @GetMapping(value = path+"/exportReview")
    Result exportReview(@Valid @ModelAttribute GccBaseQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     *  POST----通过条件来获取申报人详情
     * @param   queryForm
     * @return
     */
    @PostMapping(value = path+"/getDeclareDetails")
    Result<GccReviewOptionBygroupVo> getDeclareDetails(@Valid @ModelAttribute GccProjectApplicationQueryForm queryForm);



    /**
     *  POST----所院录入结果及归档
     * @param  file,type
     * @return
     */
    @PostMapping(value = path+"/importResults")
    Result importExcel(@Valid @ModelAttribute MultipartFile file) throws IOException;
}
