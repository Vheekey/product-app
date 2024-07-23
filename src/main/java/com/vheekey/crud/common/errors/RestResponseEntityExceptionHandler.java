package com.vheekey.crud.common.errors;

import com.vheekey.crud.common.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class) //It is the exception handler of not found exception
    @ResponseBody() //returns response body
    @ResponseStatus(HttpStatus.NOT_FOUND) //with response status of not found

    public ErrorMessage notFoundException(NotFoundException exception) {
        logger.error("Exception occurred: ", exception);

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return errorMessage;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody()
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage genericException(Exception exception) {
        logger.error("Exception occurred: ", exception);

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());

        return errorMessage;
    }
}
