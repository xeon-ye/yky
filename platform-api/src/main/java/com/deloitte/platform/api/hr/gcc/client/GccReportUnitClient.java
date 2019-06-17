package com.deloitte.platform.api.hr.gcc.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccReportUnitQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccReportUnitForm;
import com.deloitte.platform.api.hr.gcc.vo.GccReportUnitVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-11
 * @Description :  GccReportUnit控制器接口
 * @Modified :
 */
public interface GccReportUnitClient {

    public static final String path="/hr/gcc-report-unit";


    /**
     *  POST---新增
     * @param gccReportUnitForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccReportUnitForm gccReportUnitForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccReportUnitForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccReportUnitForm gccReportUnitForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccReportUnitVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccReportUnitQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccReportUnitVo>> list(@Valid @RequestBody GccReportUnitQueryForm gccReportUnitQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccReportUnitQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccReportUnitVo>> search(@Valid @RequestBody GccReportUnitQueryForm gccReportUnitQueryForm);


    /**
     *  POST----导出
     * @param  queryForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result  export(@Valid @ModelAttribute GccReportUnitQueryForm queryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


}
