package com.deloitte.platform.common.web.feign;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.ClassUtils;

import javax.xml.transform.Source;

/**
 * @Author : jackliu
 * @Date : Create in 16:51 13/03/2019
 * @Description :
 * @Modified :
 */
public class GdcAllEncompassingFormHttpMessageConverter extends GdcFormHttpMessageConverter {

    private static final boolean jaxb2Present =
            ClassUtils.isPresent("javax.xml.bind.Binder",
                    AllEncompassingFormHttpMessageConverter.class.getClassLoader());

    private static final boolean jackson2Present =
            ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper",
                    AllEncompassingFormHttpMessageConverter.class.getClassLoader()) &&
                    ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator",
                            AllEncompassingFormHttpMessageConverter.class.getClassLoader());

    private static final boolean jackson2XmlPresent =
            ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper",
                    AllEncompassingFormHttpMessageConverter.class.getClassLoader());

    private static final boolean gsonPresent =
            ClassUtils.isPresent("com.google.gson.Gson",
                    AllEncompassingFormHttpMessageConverter.class.getClassLoader());


    public GdcAllEncompassingFormHttpMessageConverter() {
        addPartConverter(new SourceHttpMessageConverter<Source>());

        if (jaxb2Present && !jackson2XmlPresent) {
            addPartConverter(new Jaxb2RootElementHttpMessageConverter());
        }

        if (jackson2Present) {
            addPartConverter(new MappingJackson2HttpMessageConverter());
        }
        else if (gsonPresent) {
            addPartConverter(new GsonHttpMessageConverter());
        }

        if (jackson2XmlPresent) {
            addPartConverter(new MappingJackson2XmlHttpMessageConverter());
        }
    }

}
