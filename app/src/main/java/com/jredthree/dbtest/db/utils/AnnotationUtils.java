package com.jredthree.dbtest.db.utils;

import com.jredthree.dbtest.db.annotation.Column;
import com.jredthree.dbtest.db.annotation.GeneratedValue;
import com.jredthree.dbtest.db.annotation.Id;
import com.jredthree.dbtest.db.annotation.Table;
import com.jredthree.dbtest.db.model.ColumnField;
import com.jredthree.dbtest.db.model.TableField;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * author: smart
 * time: 2016/12/16
 */

public class AnnotationUtils {

    public static String parserAnnotationDb(Class<?> classZ){
        Field[] fields = classZ.getDeclaredFields();
        Method[] methods = classZ.getDeclaredMethods();
        Table table = classZ.getAnnotation(Table.class);
        List<ColumnField> columnList = new ArrayList<>();
        for(Field f : fields){
            Column column = f.getAnnotation(Column.class);
            Id id = f.getAnnotation(Id.class);
            GeneratedValue gv = f.getAnnotation(GeneratedValue.class);
            if(null != column){
                String type;
                if(!column.columnDefinition().equals("")){
                    type = column.columnDefinition();
                }else{
                    type = f.getType().getSimpleName();
                }
                ColumnField columnField = new ColumnField(column,f,TypeUtils.toSqliteType(type),id,gv);
                columnList.add(columnField);
            }

        }
        TableField tableField = new TableField(table,columnList);
        return tableField.toString();
    }
}
