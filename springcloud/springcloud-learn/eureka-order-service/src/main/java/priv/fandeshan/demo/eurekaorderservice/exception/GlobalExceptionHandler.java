package priv.fandeshan.demo.eurekaorderservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import priv.fandeshan.demo.tools.api.ResultInfo;
import priv.fandeshan.demo.tools.exception.ValidException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultInfo handleException(Exception e, HttpServletRequest request){
        log.info("GlobalExceptionHandler.handleException:{},{}",request.getRequestURI(),e);
        String msg = "系统繁忙" + e.getMessage();
        if (e instanceof ValidException ) {
            msg = e.getMessage();
        }
        return new  ResultInfo.Builder().buildCustomize("999999",msg);
    }
}
