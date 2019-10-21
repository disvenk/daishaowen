package com.daishaowen.test.DuXieFenLi;

public class DatabaseContextHolder {
    private static ThreadLocal<String> contextHolder=new ThreadLocal<String>();

    public static void setDataSourceName(String dataSourceName){
                 contextHolder.set(dataSourceName);
             }

    public static String getDataSourceName(){
                 return contextHolder.get();
             }

    public static void clearDbType(){
                 contextHolder.remove();
             }
}
