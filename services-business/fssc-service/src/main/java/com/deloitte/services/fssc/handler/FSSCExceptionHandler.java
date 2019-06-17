package com.deloitte.services.fssc.handler;

import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.util.ExceptionUtil;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.BatchExecutorException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Configuration
@RestControllerAdvice
@Order(-1)
public class FSSCExceptionHandler {

    /**
     * {@link MethodArgumentNotValidException}异常处理器
     *
     * @param ex 异常对象
     * @return {@link Result}
     */
    @ExceptionHandler(BindException.class)
    public Result bindExceptionHandler(BindException ex) {
        String errorMessage = "";
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError fieldError:fieldErrors){
            errorMessage += fieldError.getDefaultMessage()+"!";
        }
        log.warn("参数校验失败：{}",errorMessage);
        return Result.fail(new BaseException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(ExceptionUtil.getStackTraceAsString(e));
        return Result.fail(new BaseException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY));
    }

    @ExceptionHandler(FSSCException.class)
    public Result fsscException(FSSCException ex){
        log.error(ExceptionUtil.getStackTraceAsString(ex));
        return Result.fail(new BaseException(ex.getErrorType()));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public Result illegalArgumentException(IllegalArgumentException ex){
        log.error(ExceptionUtil.getStackTraceAsString(ex));
        return Result.fail(ex.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public Result sqlException(SQLException exception){
        String message = exception.getMessage();
        log.error(ExceptionUtil.getStackTraceAsString(exception));
        if(StringUtil.isNotEmpty(message)&&message.contains("ORA-00001")){
            return Result.fail(new BaseException(FsscErrorType.DATA_DUPLICATE));
        }
        return Result.fail(new BaseException(FsscErrorType.SQL_EXCEPTION));
    }


    @ExceptionHandler(BatchExecutorException.class)
    public Result batchUpdateException(BatchExecutorException exception){
        String message = exception.getMessage();
        log.error(ExceptionUtil.getStackTraceAsString(exception));
        if(StringUtil.isNotEmpty(message)&&message.contains("ORA-00001")){
            return Result.fail(new BaseException(FsscErrorType.DATA_DUPLICATE));
        }
        return Result.fail(new BaseException(FsscErrorType.SQL_EXCEPTION));
    }
}
