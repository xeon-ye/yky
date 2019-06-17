package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccOnjobInformationQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccOnjobInformationAllVo;
import com.deloitte.platform.api.hr.gcc.vo.GccOnjobInformationForm;
import com.deloitte.platform.api.hr.gcc.vo.GccOnjobInformationVo;
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
 * @Description :  GccOnjobInformation控制器接口
 * @Modified :
 */
public interface GccOnjobInformationClient {

    public static final String path="/hr/gcc-onjob-information";


    /**
     *  POST---新增
     * @param gccOnjobInformationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccOnjobInformationForm gccOnjobInformationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccOnjobInformationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccOnjobInformationForm gccOnjobInformationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccOnjobInformationVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccOnjobInformationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccOnjobInformationVo>> list(@Valid @RequestBody GccOnjobInformationQueryForm gccOnjobInformationQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccOnjobInformationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccOnjobInformationVo>> search(@Valid @RequestBody GccOnjobInformationQueryForm gccOnjobInformationQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccOnjobInformationQueryAllForm
     * @return
     */
    @PostMapping(value = path+"/pageAll/conditions")
    Result<IPage<GccOnjobInformationAllVo>> searchAll(@Valid @RequestBody GccBaseQueryForm gccOnjobInformationQueryAllForm);


    /**
     *  POST----列表查询
     * @param   gccOnjobInformationQueryAllForm
     * @return
     */
    @PostMapping(value = path+"/listAll/conditions")
    Result<List<GccOnjobInformationAllVo>> listAll(@Valid @RequestBody GccBaseQueryForm gccOnjobInformationQueryAllForm);


    /**
     *  POST----导出模版
     * @param  gccOnjobInformationQueryAllForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result exportExcel(@Valid @ModelAttribute GccBaseQueryForm gccOnjobInformationQueryAllForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


}
