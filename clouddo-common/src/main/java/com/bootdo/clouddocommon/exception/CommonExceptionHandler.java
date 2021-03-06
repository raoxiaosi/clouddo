package com.bootdo.clouddocommon.exception;

import com.bootdo.clouddocommon.utils.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    ApiResult exception(Exception e) {
        logger.error(e.getMessage(), e);
        return ApiResult.error(500, e.getMessage());
    }
}
