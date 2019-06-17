package com.deloitte.platform.api.bpmservice.param;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskFormStart;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : jackliu
 * @Date : Create in 2019-04-02
 * @Description :   BpmProcessTask查询from对象
 * @Modified :
 */
@ApiModel("审批记录通用查询")
@Data
public class BpmProcessTaskQueryWrapper {

    @ApiModelProperty(value = "查询Wrapper",example="Map<String,String[]> queryMap=new HashMap<>();\n" +
            "        String[] ss={\"between\",\"2019-02-12 12:01:23,2019-04-15 15:33:00\"};\n" +
            "        String[] ss1={\"eq\",String.valueOf(id)};\n" +
            "        String[] ss3={\"like\",\"借款\"};\n" +
            "        String[] ss4={\"in\",\"已批准,待审批\"};\n" +
            "        String[] ss5={\"notIn\",\"请假单,调休单\"};\n" +
            "\n" +
            "        queryMap.put(BpmProcessTaskQueryForm.CREATE_TIME,ss);\n" +
            "        queryMap.put(BpmProcessTaskQueryForm.APPROVER_ACOUNT,ss1);\n" +
            "        queryMap.put(BpmProcessTaskQueryForm.TASK_TITLE,ss3);\n" +
            "        queryMap.put(BpmProcessTaskQueryForm.TASK_STAUTS,ss4);\n" +
            "        queryMap.put(BpmProcessTaskQueryForm.OBJECT_TYPE,ss5);")
    @NotNull
    Map<String,String[]> wrapperMap;

    @NotNull
    @ApiModelProperty(value = "当前页")
    int currentPage=0;

    @ApiModelProperty(value = "分页大小")
    int pageSize=15;
}
