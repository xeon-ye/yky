package com.deloitte.platform.api.srpmp.project.task;

import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.platform.api.srpmp.project.task.vo.ext.SrpmsProjectTaskInnExtVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-11
 * @Description :  SrpmsProjectTaskInn控制器接口
 * @Modified :
 */
public interface SrpmsProjectTaskInnClient {

    public static final String path="/srpmp/project/task/inn";

    /**
     *  POST---保存
     * @param vo
     * @return
     */
    @PostMapping(value = path)
    Result<Map<String, Object>> save(@ModelAttribute SrpmsProjectTaskInnExtVo vo, UserVo user);

    /**
     *  POST---提交
     * @param vo
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result<Map<String, Object>> submit(@Valid @ModelAttribute SrpmsProjectTaskInnExtVo vo, UserVo user);

    /**
     *  GET----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/{id}")
    Result<SrpmsProjectTaskInnExtVo> get(@PathVariable(value = "id") long id);

    /**
     *  GET----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/export/{id}")
    Result export(@PathVariable(value = "id") long id, HttpServletRequest request, HttpServletResponse response);

    /**
     * 导入模板
     */
    @PostMapping(value = path+"/import")
    Result<SrpmsProjectApplyInnPreSubmitVo> importWord(@Valid @ModelAttribute WordImportReqVo reqVo);
}
