package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.TeacherTrainVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-19
 * @Description :  TeacherTrain控制器接口
 * @Modified :
 */
public interface TeacherTrainClient {

    public static final String path="/hr/teacher-train";


    /**
     *  POST---新增
     * @param fileUrl
     * @return
     */
    @PostMapping(value = path+"/add")
    Result add(@RequestParam String fileUrl);

    /**
     *  POST----复杂查询
     *
     * @param  teacherTrainQueryForm  条件搜索
     * @return
     */
    @PostMapping(value = path+"/getTeacherTrainList")
    Result<IPage<TeacherTrainVo>> getTeacherTrainList(@Valid @RequestBody TeacherTrainQueryForm teacherTrainQueryForm);

    /**
     * 汇总提交-审核教师岗前人员信息
     *
     * @param tidList  教师信息ID数组
     * @param subType  汇总提交类型 1所院汇总提交，2院校批量审核通过，3院校批量审核不通过
     * @return
     */
    @PostMapping(value = path+"/submitTeacherTrain")
    Result submitTeacherTrain(String tidList, @RequestParam Integer subType);

    /**
     *  导出教师岗前人员信息
     *
     * @param teacherTrainExportForm  条件搜索
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportTeacherTrainList")
    void exportTeacherTrainList(@Valid @ModelAttribute TeacherTrainExportForm teacherTrainExportForm, HttpServletRequest request, HttpServletResponse response);

    /**
     *  条件搜索下拉框选项初始化
     *
     * @return
     */
    @GetMapping(value = path+"/getTeacherInitTermSearch")
    Result<HashMap<String,Object>> getTeacherInitTermSearch();

    /**
     *  批量删除岗前Teacher
     *
     * @param ids  教师ID[1111,22222]
     * @return
     */
    @PostMapping(value = path+"/delTeacherByIds")
    Result delTeacherByIds(@RequestParam(required = true) String[] ids);
}
