package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpStationQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpStationForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpStationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :  HrZpcpStation控制器接口
 * @Modified :
 */
public interface HrZpcpStationClient {

    public static final String path="/hr/recruitment/hr-zpcp-station";


    /**
     *  POST---新增
     * @param hrZpcpStationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrZpcpStationForm hrZpcpStationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrZpcpStationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrZpcpStationForm hrZpcpStationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrZpcpStationVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrZpcpStationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrZpcpStationVo>> list(@Valid @RequestBody HrZpcpStationQueryForm hrZpcpStationQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrZpcpStationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrZpcpStationVo>> search(@Valid @RequestBody HrZpcpStationQueryForm hrZpcpStationQueryForm);

    /**
     *
     *  条件导出数据列表
     *
     * @param HrZpcpStationQueryForm  条件
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = path+"/list/exportStationList")
    void exportStationList(@Valid @ModelAttribute HrZpcpStationQueryForm HrZpcpStationQueryForm, HttpServletRequest request, HttpServletResponse response);

    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);
}
