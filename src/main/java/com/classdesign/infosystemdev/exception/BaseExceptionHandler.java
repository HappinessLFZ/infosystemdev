package com.classdesign.infosystemdev.exception;


import com.classdesign.infosystemdev.dto.Response;
import com.classdesign.infosystemdev.dto.ResponseDTO;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {

    private  static  final Logger logger= LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseDTO handle(ServiceException e){
        logger.info(e.getMessage());
        return Response.error(e.getCode(),e.getMessage());
    }
}
