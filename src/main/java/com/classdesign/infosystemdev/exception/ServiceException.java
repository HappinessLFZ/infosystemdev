package com.classdesign.infosystemdev.exception;

import com.classdesign.infosystemdev.enums.BaseEnum;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException{

    private int code;

    public ServiceException(int code,String message){
        super(message);
        this.code=code;
    }
    public ServiceException(BaseEnum e){
        super(e.getMessage());
        this.code=e.getCode();
    }
}
