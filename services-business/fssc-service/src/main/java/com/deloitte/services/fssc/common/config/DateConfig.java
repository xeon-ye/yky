package com.deloitte.services.fssc.common.config;

import com.deloitte.platform.common.core.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Configuration
public class DateConfig {
    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * LocalDateTime转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
            }
        };
    }

    /**
     * 解码字符串 防止前端get请求输入特殊字符
     * @return
     */
    @Bean
    public Converter<String, String> stringStringConverter() {
        return new Converter<String, String>() {
            @Override
            public String convert(String source) {
                try {
                    log.info("sourece:{}", URLDecoder.decode(source,"UTF-8"));
                    return URLDecoder.decode(source,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    log.error("解码错误:{}", ExceptionUtil.getStackTraceAsString(e));
                }
                return source;
            }
        };
    }

}
