package com.deloitte.platform.common.web.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig {

    @Autowired
    Environment environment;

    /**
     * 定义分隔符
     */
    private static final String SPLITOR = ";";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage("com.deloitte"))
                //.apis(basePackage("com.fssc" + SPLITOR + "com.deloitte"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        try {
            return new ApiInfoBuilder()
                    .title("医科院MacRP项目接口API文档  RESTful APIs")
                    .termsOfServiceUrl("http://" + InetAddress.getLocalHost().getHostAddress() + ":"
                            + environment.getProperty("local.server.port") + "/swagger-ui.html")
                    .contact("deloitte")
                    .version("1.0")
                    .build();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            // log.info("扫描类名:{}", input.getName());
            if (input.getName().contains("proxy")) {
                return false;
            }
            for (String strPackage : basePackage.split(SPLITOR)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        //log.info("ReuestHandler {}", input.declaringClass());
        return Optional.fromNullable(input.declaringClass());
    }

}
