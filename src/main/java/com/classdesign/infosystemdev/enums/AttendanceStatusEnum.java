package com.classdesign.infosystemdev.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttendanceStatusEnum  implements BaseEnum{

    NORMAL(0, "正常","success"),
    LATE(1, "迟到",""),
    LEAVE_EARLY(2, "早退","warning"),
    ABSENTEEISM(3, "旷工","danger"),
    LEAVE(4, "休假","info");

    @EnumValue   //存储到数据库中的字段，还需要扫描枚举类的包路径
    private final Integer code;

    @JsonValue    //当枚举类转JSON格式的时候可以使用该注解获得需要的值
    private final String message;

    private final String tagType;
}
