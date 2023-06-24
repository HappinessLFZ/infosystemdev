package com.classdesign.infosystemdev.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LeaveEnum implements  BaseEnum{
    PERSONAL_LEAVE(0, "事假"),
    MATERNITY_LEAVE(1, "产假"),
    SICK_LEAVE(2, "病假"),
    MARRIAGE_LEAVE(3, "婚假"),
    HOME_LEAVE(4, "探亲假"),
    PATERNITY_LEAVE(5, "陪产假");

    @EnumValue
    private final Integer code;

    @JsonValue
    private final String message;



}
