package com.deloitte.platform.common.core.common;

import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatFormException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 20:43 17/02/2019
 * @Description :
 * @Modified :
 */
@Slf4j
@ControllerAdvice
public class WholeExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result javaExceptionHandler(Exception ex) {
        String uid = UUIDUtil.getRandom32PK();
        log.error("捕获到Exception异常: UID[" + uid + "]", ex);
        //jack-todo 异常后续处理

        return Result.fail(new PlatFormException(PlatformErrorType.SYSTEM_ERROR), uid, ex.toString());

    }


    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result javaExceptionHandler(MethodArgumentNotValidException ex) {
        String uid = UUIDUtil.getRandom32PK();
        log.error("捕获到MethodArgumentNotValidException异常: UID[" + uid + "]", ex);
        BindingResult bindingResult = ex.getBindingResult();
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            return new Result(PlatformErrorType.ARGUMENT_NOT_VALID.getCode(),allErrors.get(0).getDefaultMessage(),ex.getMessage());
        }
        return Result.fail(new PlatFormException(PlatformErrorType.ARGUMENT_NOT_VALID), uid, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result javaExceptionHandler(AccessDeniedException ex) {
        String uid = UUIDUtil.getRandom32PK();
        log.error("捕获到AccessDeniedException异常: UID[" + uid + "]", ex);
        return Result.fail(new PlatFormException(PlatformErrorType.SYSTEM_NO_PERMISSION), uid, ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public Result javaExceptionHandler(BaseException ex) {
        String uid = UUIDUtil.getRandom32PK();
        log.error("捕获到BaseException异常: UID[" + uid + "]", ex);
        //jack-todo
        return Result.fail(ex, uid, ex.getMessage());
    }

}
