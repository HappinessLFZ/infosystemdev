package com.classdesign.infosystemdev.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuditStatusEnum implements BaseEnum{
    UNAUDITED(0, "未审核","info"),
    APPROVE(1, "审核通过","success"),
    REJECT(2, "驳回","danger"),
    CANCEL(3, "撤销","");

    @EnumValue
    private  final   Integer code;

    @JsonValue
    private final   String message;

    private  final  String tagType;
}
