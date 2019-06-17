package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.PersonBakQueryForm;
import com.deloitte.platform.api.project.vo.PersonBakForm;
import com.deloitte.platform.api.project.vo.PersonBakVo;
import com.deloitte.platform.api.project.client.PersonBakClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.project.common.util.CommonUtil;
import com.deloitte.services.project.entity.ProjectsAndApplication;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import com.deloitte.services.project.service.IPersonBakService;
import com.deloitte.services.project.entity.PersonBak;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :   PersonBak控制器实现类
 * @Modified :
 */
@Api(tags = "PersonBak操作接口")
@Slf4j
@RestController
public class PersonBakController implements PersonBakClient {

    @Autowired
    public IPersonBakService  personBakService;


    @Override
    @ApiOperation(value = "新增PersonBak", notes = "新增一个PersonBak")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="personBakForm",value="新增PersonBak的form表单",required=true)  PersonBakForm personBakForm) {
        log.info("form:",  personBakForm.toString());
        PersonBak personBak =new BeanUtils<PersonBak>().copyObj(personBakForm,PersonBak.class);
        return Result.success( personBakService.save(personBak));
    }


    @Override
    @ApiOperation(value = "删除PersonBak", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "PersonBakID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        personBakService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改PersonBak", notes = "修改指定PersonBak信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "PersonBak的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="personBakForm",value="修改PersonBak的form表单",required=true)  PersonBakForm personBakForm) {
        PersonBak personBak =new BeanUtils<PersonBak>().copyObj(personBakForm,PersonBak.class);
        personBak.setId(id);
        personBakService.saveOrUpdate(personBak);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取PersonBak", notes = "获取指定ID的PersonBak信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "PersonBak的ID", required = true, dataType = "long")
    public Result<PersonBakVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        PersonBak personBak=personBakService.getById(id);
        PersonBakVo personBakVo=new BeanUtils<PersonBakVo>().copyObj(personBak,PersonBakVo.class);
        return new Result<PersonBakVo>().sucess(personBakVo);
    }

    @Override
    @ApiOperation(value = "列表查询PersonBak", notes = "根据条件查询PersonBak列表数据")
    public Result<List<PersonBakVo>> list(@Valid @RequestBody @ApiParam(name="personBakQueryForm",value="PersonBak查询参数",required=true) PersonBakQueryForm personBakQueryForm) {
        log.info("search with personBakQueryForm:", personBakQueryForm.toString());
        List<PersonBak> personBakList=personBakService.selectList(personBakQueryForm);
        List<PersonBakVo> personBakVoList=new BeanUtils<PersonBakVo>().copyObjs(personBakList,PersonBakVo.class);
        return new Result<List<PersonBakVo>>().sucess(personBakVoList);
    }


    @Override
    @ApiOperation(value = "分页查询PersonBak", notes = "根据条件查询PersonBak分页数据")
    public Result<IPage<PersonBakVo>> search(@Valid @RequestBody @ApiParam(name="personBakQueryForm",value="PersonBak查询参数",required=true) PersonBakQueryForm personBakQueryForm) {
        log.info("search with personBakQueryForm:", personBakQueryForm.toString());
        IPage<PersonBak> personBakPage=personBakService.selectPage(personBakQueryForm);
        IPage<PersonBakVo> personBakVoPage=new BeanUtils<PersonBakVo>().copyPageObjs(personBakPage,PersonBakVo.class);
        return new Result<IPage<PersonBakVo>>().sucess(personBakVoPage);
    }

    @PostMapping(value = "/pageList/getPersonBak")
    @ApiOperation(value = "人员查询测试", notes = "人员查询测试")
    Result<IPage<PersonBak>> searchReply(@Valid @RequestBody @ApiParam PersonBakVo personBakVo){
        Map<String,Object> map = CommonUtil.objectToMap(personBakVo);
        Page<PersonBak> page = new Page<>(1, 10);
        //流程申报查询
        List<PersonBak> list = personBakService.selectListPage(page, map);
        IPage<PersonBak> userIPage = page.setRecords(list);
        return new Result<IPage<PersonBak>>().sucess(userIPage);
    }


}



