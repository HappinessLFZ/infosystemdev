package com.classdesign.infosystemdev.utils;

import cn.hutool.core.date.DateUtil;

public class DatetimeUtil {
    /**
     * 获取月份的日期字符串数据组入：['20201212']
     * @param month  yyytmm
     * @return
     */
    public static  String[] getMonthDayList(String month){
        //获取月的天数，isLeapYear判断是否为闰年还是平年
        int monthDay= DateUtil.lengthOfMonth(Integer.valueOf(month.substring(4)),DateUtil.isLeapYear(Integer.valueOf(month.substring(0,4))));
        String[] monthDayList=new String [monthDay];
        for (int i = 0; i <monthDay; i++) {
            if(i<9){
                monthDayList[i]=month+"0"+(i+1);
            }else{
                monthDayList[i]=month+""+(i+1);
            }
        }
        return  monthDayList;
    }
}
