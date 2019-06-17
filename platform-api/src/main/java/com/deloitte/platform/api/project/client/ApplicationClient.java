package com.deloitte.platform.api.project.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.project.param.ApplicationQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-26
 * @Description :  Application控制器接口
 * @Modified :
 */
public interface ApplicationClient {

    public static final String path="/project/application";


    /**
     *  POST---新增
     * @param applicationForm
     * @return
     */
    @PostMapping(value = path + "/add")
    Result add(@Valid @ModelAttribute ApplicationForm applicationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/delete/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, applicationForm
     * @return
     */
    @PatchMapping(value = path+"/update/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ApplicationForm applicationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/get/{id}")
    Result<ApplicationVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   applicationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ApplicationVo>> list(@Valid @RequestBody ApplicationQueryForm applicationQueryForm);


    /**
     *  POST----复杂查询
     * @param  applicationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ApplicationVo>> search(@Valid @RequestBody ApplicationQueryForm applicationQueryForm);

}
