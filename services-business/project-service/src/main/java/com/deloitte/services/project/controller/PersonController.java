package com.deloitte.services.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.PersonQueryForm;
import com.deloitte.platform.api.project.vo.PersonForm;
import com.deloitte.platform.api.project.vo.PersonVo;
import com.deloitte.platform.api.project.client.PersonClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.project.service.IPersonService;
import com.deloitte.services.project.entity.Person;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-15
 * @Description :   Person控制器实现类
 * @Modified :
 */
@Api(tags = "Person操作接口")
@Slf4j
@RestController
public class PersonController implements PersonClient {

    @Autowired
    public IPersonService  personService;


    @Override
    @ApiOperation(value = "新增Person", notes = "新增一个Person")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="personForm",value="新增Person的form表单",required=true)  PersonForm personForm) {
        log.info("form:",  personForm.toString());
        Person person =new BeanUtils<Person>().copyObj(personForm,Person.class);
        return Result.success( personService.save(person));
    }


    @Override
    @ApiOperation(value = "删除Person", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "PersonID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        personService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Person", notes = "修改指定Person信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Person的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="personForm",value="修改Person的form表单",required=true)  PersonForm personForm) {
        Person person =new BeanUtils<Person>().copyObj(personForm,Person.class);
        person.setId(id);
        personService.saveOrUpdate(person);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Person", notes = "获取指定ID的Person信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Person的ID", required = true, dataType = "long")
    public Result<PersonVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Person person=personService.getById(id);
        PersonVo personVo=new BeanUtils<PersonVo>().copyObj(person,PersonVo.class);
        return new Result<PersonVo>().sucess(personVo);
    }

    @Override
    @ApiOperation(value = "列表查询Person", notes = "根据条件查询Person列表数据")
    public Result<List<PersonVo>> list(@Valid @RequestBody @ApiParam(name="personQueryForm",value="Person查询参数",required=true) PersonQueryForm personQueryForm) {
        log.info("search with personQueryForm:", personQueryForm.toString());
        List<Person> personList=personService.selectList(personQueryForm);
        List<PersonVo> personVoList=new BeanUtils<PersonVo>().copyObjs(personList,PersonVo.class);
        return new Result<List<PersonVo>>().sucess(personVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Person", notes = "根据条件查询Person分页数据")
    public Result<IPage<PersonVo>> search(@Valid @RequestBody @ApiParam(name="personQueryForm",value="Person查询参数",required=true) PersonQueryForm personQueryForm) {
        log.info("search with personQueryForm:", personQueryForm.toString());
        IPage<Person> personPage=personService.selectPage(personQueryForm);
        IPage<PersonVo> personVoPage=new BeanUtils<PersonVo>().copyPageObjs(personPage,PersonVo.class);
        return new Result<IPage<PersonVo>>().sucess(personVoPage);
    }

}



