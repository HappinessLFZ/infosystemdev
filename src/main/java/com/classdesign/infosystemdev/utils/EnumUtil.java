package com.classdesign.infosystemdev.utils;

import com.classdesign.infosystemdev.enums.BaseEnum;

import java.util.*;
import java.util.stream.Collectors;

public class EnumUtil {
    /**
     * 获取所有枚举项
     * @param enumType
     * @param <T>   BaseEnum
     * @return
     */
    public static <T extends BaseEnum> List<T> getEnumItems(Class<T> enumType){
        return Arrays.asList(enumType.getEnumConstants());
    }

    /**
     * 获取key为code的map
     *
     * @param enumType   enumType 枚举类型
     * @param <T>     BaseEnum
     * @return
     */
    public static  <T extends BaseEnum> Map<Integer,String> getEnumMapCode(Class<T> enumType){
        Map<Integer,String> map=new HashMap<>();
        for(T enumItem : enumType.getEnumConstants()){
            map.put(enumItem.getCode(),enumItem.getMessage());
        }
        return  map;
    }


    /**
     * 获取key为value的map
     *
     * @param enumType   enumType 枚举类型
     * @param <T>     BaseEnum
     * @return
     */
    public static  <T extends BaseEnum> Map<String,Integer> getEnumMapValue(Class<T> enumType){
        Map<String,Integer> map=new HashMap<>();
        for(T enumItem : enumType.getEnumConstants()){
            map.put(enumItem.getMessage(),enumItem.getCode());
        }
        return  map;
    }


    /**
     * 获取枚举所有项的列表
     * @param enumType  枚举类型
     * @param <T>
     * @return
     */
    public static <T extends  BaseEnum> List<Map<String,Object>>  getEnumList(Class<T> enumType){
        List<Map<String,Object>> list=new ArrayList<>();
        if(!enumType.isEnum()){
            return new ArrayList<>();
        }
        T[] enums=enumType.getEnumConstants();
        if(enums == null||enums.length <= 0){
            return new ArrayList<>();
        }
        for(T enumItem :enums){
            Map<String,Object> map=new HashMap<>();
            map.put("code",enumItem.getCode());
            map.put("message",enumItem.getMessage());
            list.add(map);
        }
        return  list;
    }

    /**
     * 获取枚举的所有code值
     * @param enumType
     * @param <T>
     * @return
     */
    public static <T extends  BaseEnum> List<Integer> getEnumCodes(Class<T> enumType){
       //利用Stream方法将原枚举数组的枚举项按列序输出，map方法即将数组中每个元素及逆行getCode的调用，collect是将数组转成List类型
        return  Arrays.stream(enumType.getEnumConstants())
                         .map(BaseEnum::getCode)
                          .collect(Collectors.toList());

    }

    /**
     * 获取枚举的所有Value值
     * @param enumType
     * @param <T>
     * @return
     */
    public  static  <T extends BaseEnum> List<String> getEnumValues(Class<T> enumType){
        return  Arrays.stream(enumType.getEnumConstants())
                .map(BaseEnum::getMessage)
                .collect(Collectors.toList());
    }

    /**
     * 根据Code值查询对应的枚举项
     * @param enumType
     * @param code
     * @param <T>
     * @return
     */
    public static  <T extends BaseEnum> T fromCode(Class<T> enumType,Integer  code){
        if(code == null){
            return null;
        }
        for(T enumItem : enumType.getEnumConstants()){
            if(Objects.equals(code,enumItem.getCode())){
                return enumItem;
            }
        }
        return null;
    }

    /**
     * 根据message获取对应的枚举项
     * @param enumType
     * @param message
     * @param <T>
     * @return
     */
    public static  <T extends BaseEnum> T fromValue(Class<T> enumType,String message){
        if(message == null){
            return  null;
        }
        for(T enumItem : enumType.getEnumConstants()){
            if(Objects.equals(message,enumItem.getMessage())){
                return enumItem;
            }
        }
        return null;
    }



}
