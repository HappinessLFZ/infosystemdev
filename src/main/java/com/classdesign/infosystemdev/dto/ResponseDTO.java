package com.classdesign.infosystemdev.dto;

import com.classdesign.infosystemdev.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一设置返回数据样式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "前端数据传输对象",description = "")
public class ResponseDTO {


    /**
     * 返回前端数据和形式可能有多种，所以之后的构造方法会有不同形式的
     */

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("响应消息")
    private String  message;

    @ApiModelProperty("响应数据")
    private Object data;

    @ApiModelProperty("token值")
    private String  token;

    public ResponseDTO(Integer code,String message){
            this.code=code;
            this.message=message;
    }

    public ResponseDTO(Integer code,String message,Object data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public ResponseDTO(BaseEnum e) {
        this.code=e.getCode();
        this.message=e.getMessage();
    }

    public ResponseDTO(BaseEnum e,Object data){
        this.code=e.getCode();
        this.message=e.getMessage();
        this.data=data;
    }

    public ResponseDTO(BaseEnum e,Object data,String token){
        this.code=e.getCode();
        this.message=e.getMessage();
        this.data=data;
        this.token=token;
    }

}
