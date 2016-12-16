package com.jredthree.dbtest.db.model;

import com.jredthree.dbtest.db.annotation.Column;
import com.jredthree.dbtest.db.annotation.GeneratedValue;
import com.jredthree.dbtest.db.annotation.Id;

import java.lang.reflect.Field;

/**
 * author: smart
 * time: 2016/12/16
 * 列的辅助类
 */

public class ColumnField {

    private Column column;
    private Field field;
    private String type;
    private Id id;
    private GeneratedValue generatedValue;

    public ColumnField(Column column, Field field,String type) {
        this.column = column;
        this.field = field;
        this.type = type;
    }

    public ColumnField(Column column, Field field, String type, Id id, GeneratedValue generatedValue) {
        this.column = column;
        this.field = field;
        this.type = type;
        this.id = id;
        this.generatedValue = generatedValue;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public GeneratedValue getGeneratedValue() {
        return generatedValue;
    }

    public void setGeneratedValue(GeneratedValue generatedValue) {
        this.generatedValue = generatedValue;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(column.name())
                .append(" ")
                .append(type)
                .append(" ");

        if(type.equalsIgnoreCase("varchar")){
            sb.append("("+column.length()+")");
        }

        if(null != id){
            sb.append("primary key").append(" ");
        }

        if(null != generatedValue && generatedValue.value() == GeneratedValue.GenerationType.AUTO){
            sb.append("autoincrement").append(" ");
        }

        if(!column.nullable()){
            sb.append("NOT NULL").append(" ");
        }

        return sb.toString();
    }
}
