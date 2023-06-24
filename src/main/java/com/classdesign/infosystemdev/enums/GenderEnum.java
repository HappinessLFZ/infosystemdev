package com.classdesign.infosystemdev.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderEnum  implements  BaseEnum{

    MALE(0,"男"),
    FEMALE(1,"女");

    @EnumValue
    private final Integer code;

    @JsonValue
    private final String message;

}
