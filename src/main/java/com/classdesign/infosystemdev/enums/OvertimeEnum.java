package com.classdesign.infosystemdev.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OvertimeEnum  implements  BaseEnum{

    WORKDAY_OVERTIME(0, "工作日加班",1.5),
    HOLIDAY_OVERTIME(1, "节假日加班",3.0),
    DAY_OFF_OVERTIME(2, "休息日加班",2.0);


      @EnumValue
      private final Integer code;

      @JsonValue
      private final String message;

      private  final Double  lowerLimit;


}
