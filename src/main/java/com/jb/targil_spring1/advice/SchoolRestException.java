package com.jb.targil_spring1.advice;

import com.jb.targil_spring1.exceptions.LoginException;
import com.jb.targil_spring1.exceptions.SchoolException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice //aop->exception
public class SchoolRestException {
    //which exception class was fired
    @ExceptionHandler(value = {SchoolException.class})
    //what to return in response
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleException(Exception e){
        return new ErrorDetail("update error", e.getMessage());
    }


    @ExceptionHandler(value = {LoginException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail loginError(Exception e){
        return new  ErrorDetail("Bad login", e.getMessage());
    }
}
