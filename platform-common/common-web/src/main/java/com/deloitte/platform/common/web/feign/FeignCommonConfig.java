package com.deloitte.platform.common.web.feign;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author : jackliu
 * @Date : Create in 19:34 20/03/2019
 * @Description :
 * @Modified :
 */
@Configuration
public class FeignCommonConfig {


    @Bean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
    }


    @Bean
    public Encoder feignEncoder(){
        return new SpringEncoder(feignHttpMessageConverter());
    }
    public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new GdcMappingJackson2HttpMessageConverter());
        return new ObjectFactory<HttpMessageConverters>() {
            @Override
            public HttpMessageConverters getObject() throws BeansException {
                return httpMessageConverters;
            }
        };
    }

    public class GdcMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        GdcMappingJackson2HttpMessageConverter(){
//            List<MediaType> mediaTypes = new ArrayList<>();
//            mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")); //关键
//            setSupportedMediaTypes(mediaTypes);

            ObjectMapper objectMapper = new ObjectMapper();
            //时间格式化
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            JavaTimeModule timeModule = new JavaTimeModule();
            timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            objectMapper.registerModule(timeModule);
            setObjectMapper(objectMapper);
        }
    }

    @Bean
    Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }

    @Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return feign.Logger.Level.FULL;
    }
}
