package com.deloitte.platform.api.hr.vacation.feign;


import com.deloitte.platform.api.hr.vacation.client.CalendarClient;
import com.deloitte.platform.api.hr.vacation.param.CalendarQueryForm;
import com.deloitte.platform.api.hr.vacation.vo.CalendarForm;
import com.deloitte.platform.api.hr.vacation.vo.CalengdaDateForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-12
 * @Description :   Calendar feign客户端
 * @Modified :
 */
@FeignClient(name = "calendar-service", fallbackFactory = CalendarFeignService.HystrixCalendarService.class,primary = false)
public interface CalendarFeignService extends CalendarClient {

    @Component
    @Slf4j
    class HystrixCalendarService implements FallbackFactory<CalendarFeignService> {

        @Override
        public CalendarFeignService create(Throwable throwable) {
            return new CalendarFeignService() {
                @Override
                public Result adds(CalengdaDateForm calendarForm) {
                    return null;
                }

                @Override
                public Result add(@Valid @RequestBody CalendarForm calendarForm) {
                    log.error("CalendarService add服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result delete(@PathVariable long id) {
                    log.error("CalendarService delete服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result update(@PathVariable long id, @Valid @RequestBody CalendarForm calendarForm) {
                    log.error("CalendarService update服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result get(@PathVariable long id) {
                    log.error("CalendarService get服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result list(@Valid @RequestBody CalendarQueryForm calendarQueryForm) {
                    log.error("CalendarService list服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }

                @Override
                public Result search(@Valid @RequestBody CalendarQueryForm calendarQueryForm) {
                    log.error("CalendarService search服务不可用......");
                    throwable.printStackTrace();
                    return Result.fail(PlatformErrorType.GATEWAY_SERVICE_UNABLE,throwable.getMessage());
                }
            };
        }
    }
}