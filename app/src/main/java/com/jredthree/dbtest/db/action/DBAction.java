package com.jredthree.dbtest.db.action;

import java.util.List;

/**
 * author: smart
 * time: 2016/12/15
 */

public interface DBAction {
    void open(String dbName,String[] createTable,String[] updateTavleName,int version);

    void close();

    <T> long insert(String tableName,T t);

    long delete(String tableName, String[] whereClause, String[] whereArgs);

    <T> long update(String tableNamele, T t, String[] whereClause, String[] whereArgs);

    <T> List<T> query(String tableNamele, String[] selection, String[] selectionArgs ,String orderBy,Class<T> classZ);

}
