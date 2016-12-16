package com.jredthree.dbtest.db.model;

import com.jredthree.dbtest.db.annotation.Table;

import java.util.List;

/**
 * author: smart
 * time: 2016/12/16
 * 表的辅助类
 */

public class TableField {

    private Table table;
    private List<ColumnField> columnFields;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<ColumnField> getColumnFields() {
        return columnFields;
    }

    public void setColumnFields(List<ColumnField> columnFields) {
        this.columnFields = columnFields;
    }

    public TableField(Table table, List<ColumnField> columnFields) {
        this.table = table;
        this.columnFields = columnFields;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("create table if not exists")
                .append(" ")
                .append(table.name())
                .append(" ")
                .append("(");
        for(int i = 0;i<columnFields.size();i++){
            sb.append(columnFields.get(i).toString());
            if(i == columnFields.size() -1){

            }else{
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
