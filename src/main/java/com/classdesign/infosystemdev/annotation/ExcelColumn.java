package com.classdesign.infosystemdev.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明注解类的生命周期
 * RUNTIME表示注解保留在程序运行期间，此时可以通过反射获得定义在某个类上的所有注解。
 */
@Retention(RetentionPolicy.RUNTIME)
//声明作用的元素类型是字段
@Target(ElementType.FIELD)
public @interface ExcelColumn {

    /**
     * 注解值是表格列名
     * @return
     */
        String value()  default  "";
}
