package com.jredthree.dbtest.db.utils;

/**
 * author: smart
 * time: 2016/12/16
 */

public class TypeUtils {

    public static String toSqliteType(String type){

        if(type.equalsIgnoreCase("int") || type.equalsIgnoreCase("Integer")){
            return "INTEGER";
        }else if(type.equalsIgnoreCase("String")){
            return "TEXT";
        }else if(type.equalsIgnoreCase("Boolean")){
            return "BOOLEAN";
        }else if(type.equalsIgnoreCase("Float")){
            return "REAL";
        }else if(type.equalsIgnoreCase("Double")){
            return "REAL";
        }else if(type.equalsIgnoreCase("Long")){
            return "NUMERIC";
        }else if(type.equalsIgnoreCase("Byte")){
            return "BLOB";
        }else{
            return type;
        }
    }
}
