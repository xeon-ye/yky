package com.deloitte.platform.api.oaservice.client;

import com.deloitte.platform.api.oaservice.vo.OaMssInfoForm;
import com.deloitte.platform.api.push.form.OaMssSendInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface OaMssInfoClient {
    public static final String path="/oaservice/mss";

    @PostMapping(value = path+"/save/oaMssInfo")
    Result saveOaMssInfo(@Valid @RequestBody OaMssInfoForm oaMssInfo);

    @PostMapping(value = path+"/save/oaMssSendInfo")
    Result saveOaMssSendInfo(@Valid @RequestBody OaMssSendInfoForm oaMssInfo);

}
