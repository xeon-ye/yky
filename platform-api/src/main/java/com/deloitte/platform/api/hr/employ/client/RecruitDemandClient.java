package com.deloitte.platform.api.hr.employ.client;

import com.baomidou.mybatisplus.extension.api.R;
import com.deloitte.platform.api.hr.employ.param.EmployCountQueryForm;
import com.deloitte.platform.api.hr.employ.param.RecruitDemandQueryForm;
import com.deloitte.platform.api.hr.employ.vo.RecruitDemandForm;
import com.deloitte.platform.api.hr.employ.vo.RecruitDemandVo;
import com.deloitte.platform.api.hr.employ.vo.RecruitEmpForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-09
 * @Description :  RecruitDemand控制器接口
 * @Modified :
 */
public interface RecruitDemandClient {

    public static final String path="/hr/recruit-demand";

    /**
     * 导出汇总表
     * @param recruitDemandQueryForm
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/list/exportRecruitDemandList")
    void RecruitDemandExport(@Valid @ModelAttribute RecruitDemandQueryForm recruitDemandQueryForm, HttpServletRequest request, HttpServletResponse response);

    /**
     *  POST---新增
     * @param recruitDemandForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute RecruitEmpForm recruitDemandForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, recruitDemandForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RecruitEmpForm recruitDemandForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<RecruitDemandVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   recruitDemandQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<RecruitDemandVo>> list(@Valid @RequestBody RecruitDemandQueryForm recruitDemandQueryForm);


    /**
     *  POST----复杂查询
     * @param  recruitDemandQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<RecruitDemandVo>> search(@Valid @RequestBody RecruitDemandQueryForm recruitDemandQueryForm);
}
