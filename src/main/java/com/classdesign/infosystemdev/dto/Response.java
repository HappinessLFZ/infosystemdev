package com.classdesign.infosystemdev.dto;

import com.classdesign.infosystemdev.enums.BaseEnum;
import com.classdesign.infosystemdev.enums.BusinessStatusEnum;

/**
 * 获取请求响应数据
 */
public class Response {

    /**
     * 响应成功
     * @return
     */
    public static  ResponseDTO success() { return   new  ResponseDTO(BusinessStatusEnum.SUCCESS); }

    public static  ResponseDTO success(String message){
        return   new  ResponseDTO(BusinessStatusEnum.SUCCESS.getCode(),message);
    }

    public static  ResponseDTO success(Object data){ return new ResponseDTO(BusinessStatusEnum.SUCCESS,data);}

    public static  ResponseDTO success(String message, Object data){
        return new ResponseDTO(BusinessStatusEnum.SUCCESS.getCode(),message,data);
    }

    public static  ResponseDTO  success(Object data,String token){
        return  new ResponseDTO(BusinessStatusEnum.SUCCESS,data,token);
    }

    /**
     * 响应异常
     * @return
     */

    public static   ResponseDTO error(){ return new ResponseDTO(BusinessStatusEnum.ERROR); }


    public static  ResponseDTO error(String  message){
        return new ResponseDTO(BusinessStatusEnum.ERROR.getCode(),message);
    }

    public static  ResponseDTO error(BaseEnum e){
        return  new ResponseDTO(e.getCode(),e.getMessage());
    }

    public static  ResponseDTO error(Integer code,String message){
        return  new ResponseDTO(code,message);
    }

}
