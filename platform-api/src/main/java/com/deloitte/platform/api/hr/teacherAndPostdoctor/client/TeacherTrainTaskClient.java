package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainTaskExportForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainTaskQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherTrainTaskSubQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description :  TeacherTrainTask控制器接口
 * @Modified :
 */
public interface TeacherTrainTaskClient {

    public static final String path="/hr/teacher-train-task";


    /**
     *  POST---录入作业占比
     *
     * @param teacherTrainTaskForm  录入数据
     * @return
     */
    @PostMapping(value = path+"/add")
    Result add(@Valid @RequestBody TeacherTrainTaskForm teacherTrainTaskForm);

    /**
    *  导入作业占比
     *
    * @param  file  文件
    * @return
    */
    @PostMapping(value = path+"/importTask")
    Result importTask(MultipartFile file);


    /**
    *  获取人员信息ID获取录入作业占比信息
     *
    * @param  teacherTrainId  人员信息ID
    * @return
    */
    @GetMapping(value = path+"/{teacherTrainId}")
    Result<TeacherTrainTaskVo> get(@PathVariable(value="teacherTrainId") long teacherTrainId);

    /**
     *  POST----复杂查询
     * @param  teacherTrainTaskQueryForm
     * @return
     */
    @PostMapping(value = path+"/getTeacherTrainTaskList")
    Result<IPage<TeacherTrainTaskVo>> getTeacherTrainTaskList(@Valid @RequestBody TeacherTrainTaskQueryForm teacherTrainTaskQueryForm);

    /**
     * 导出培训人员作业及结果列表
     *
     * @param teacherTrainTaskExportForm  导出条件
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportTeacherTrainTaskList")
    void exportTeacherTrainTaskList(@ModelAttribute TeacherTrainTaskExportForm teacherTrainTaskExportForm, HttpServletRequest request, HttpServletResponse response);

    /**
     *  计算分数
     *
     * @return
     */
    @PostMapping(value = path+"/computeScale")
    Result computeScale();

    /**
     * 导入结果
     *
     * @param  file  文件
     * @return
     */
    @PostMapping(value = path+"/importGain")
    Result importGain(MultipartFile file);


    /**
     *  分页查询培训人员作业提交管理列表
     *
     * @param  teacherTrainTaskSubQueryForm  搜索条件
     * @return
     */
    @PostMapping(value = path+"/getTeacherTrainTaskSubList")
    Result<IPage<TeacherTrainTaskSubmitVo>> getTeacherTrainTaskSubList(@Valid @RequestBody TeacherTrainTaskSubQueryForm teacherTrainTaskSubQueryForm);

    /**
     *  提交作业
     *
     * @param teacherTrainTaskSubForm  作业数据
     * @return
     */
    @PostMapping(value = path+"/updateTask")
    Result updateTask(@Valid @RequestBody TeacherTrainTaskSubForm teacherTrainTaskSubForm);

    /**
     * 根据人员信息ID获取作业信息
     *
     * @param  teacherTrainId  培训人员信息ID
     * @return
     */
    @GetMapping(value = path+"/getTask")
    Result<TeacherTrainTaskInfoVo> getTask(@RequestParam long teacherTrainId);

    /**
     * 作业评分
     *
     * @param teacherTrainTaskMarkForm 评分成绩
     * @return
     */
    @PostMapping(value = path+"/computeMark")
    Result computeMark(@Valid @RequestBody TeacherTrainTaskMarkForm teacherTrainTaskMarkForm);


    /**
     *  根据条件导出培训人员考勤及成绩单
     *
     * @param teacherTrainTaskExportForm 导出条件
     * @param request
     * @param response
     */
    @GetMapping(value = path+"/exportTaskGain")
    void exportTaskGain(@ModelAttribute TeacherTrainTaskExportForm teacherTrainTaskExportForm, HttpServletRequest request, HttpServletResponse response);

    /**
     *  通知查阅
     *
     * @param id 通知ID
     * @return
     */
    @PostMapping(value = path+"/readMe/{id}")
    Result readMe(@PathVariable long id);
}
