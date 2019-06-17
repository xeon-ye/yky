package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpEmployerInfoQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpEmployerInfoForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpEmployerInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :  ZpcpEmployerInfo控制器接口
 * @Modified :
 */
public interface ZpcpEmployerInfoClient {

    public static final String path="/hr/zpcp-employer-info";


    /**
     *  POST---新增
     * @param zpcpEmployerInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpEmployerInfoForm zpcpEmployerInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpEmployerInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpEmployerInfoForm zpcpEmployerInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpEmployerInfoVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpEmployerInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpEmployerInfoVo>> list(@Valid @RequestBody ZpcpEmployerInfoQueryForm zpcpEmployerInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpEmployerInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpEmployerInfoVo>> search(@Valid @RequestBody ZpcpEmployerInfoQueryForm zpcpEmployerInfoQueryForm);

    /**
     *
     *  条件导出数据列表
     *
     * @param queryForm  条件
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path+"/list/exportEmployerInfoList")
    Result exportEmployerInfoList(@Valid @ModelAttribute ZpcpEmployerInfoQueryForm queryForm, HttpServletRequest request, HttpServletResponse response);


}
