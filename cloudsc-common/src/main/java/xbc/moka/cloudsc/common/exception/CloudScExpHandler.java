package xbc.moka.cloudsc.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xbc.moka.cloudsc.common.enums.CloudScEnum;
import xbc.moka.cloudsc.common.rsp.ResultData;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CloudScExpHandler {

    @ExceptionHandler(CloudScException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultData<String> cloudscExceptionHandler(CloudScException e) {
        log.error("cloudsc 内定义的错误：{}", e);
        return ResultData.fail(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultData<String> exceptionHandler(Exception e) {
        log.error("error: {}", e);
        return ResultData.fail(CloudScEnum.RC200.getStatus(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultData<String> handleBindException(BindException e) {
        return ResultData.fail(CloudScEnum.RC400.getStatus(),
                e.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("; ")));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultData<String> handleValidationException(ConstraintViolationException e) {
        return ResultData.fail(CloudScEnum.RC400.getStatus(),
                e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; ")));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultData<String> handleMethodValidationException(MethodArgumentNotValidException e) {
        return ResultData.fail(CloudScEnum.RC400.getStatus(),
                e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("; ")));
    }
}
