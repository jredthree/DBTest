package com.jredthree.dbtest.db.action;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.jredthree.dbtest.db.DBHelp;
import com.jredthree.dbtest.db.utils.DBUtils;

import java.util.List;

/**
 * author: smart
 * time: 2016/12/15
 */
public class DBActionImpl implements DBAction {
    private SQLiteDatabase db;
    private Context mContext;
    private DBHelp dbHelp;
    private DBUtils dbUtils;

    public DBActionImpl(Context context) {
        mContext = context;
        dbUtils = new DBUtils();
    }

    @Override
    public void open(String dbName, String[] createTable, String[] updateTavleName,int version) {
        dbHelp = new DBHelp(mContext,dbName,createTable,updateTavleName,version);
        try {
            db = dbHelp.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelp.getReadableDatabase();
        }
    }

    @Override
    public void close() {
        if (null != db) {
            db.close();
            db = null;
        }
    }

    @Override
    public <T> long insert(String tableName, T t) {
        ContentValues cv = new ContentValues();
        cv = dbUtils.getContentValues(t);
        return db.insert(tableName,null,cv);
    }

    @Override
    public long delete(String tableName, String[] whereClause, String[] whereArgs) {
        StringBuffer sb = new StringBuffer();
        if(null != whereClause) {
            for (String s : whereClause) {
                sb = sb.append(s);
            }
        }
        return db.delete(tableName,sb.toString(),whereArgs);
    }

    @Override
    public <T> long update(String tableNamele, T t, String[] whereClause, String[] whereArgs) {
        ContentValues cv = new ContentValues();
        cv = dbUtils.getContentValues(t);
        StringBuffer sb = new StringBuffer();
        if(null != whereClause) {
            for (String s : whereClause) {
                sb = sb.append(s);
            }
        }
        return db.update(tableNamele,cv,sb.toString(),whereArgs);
    }

    @Override
    public <T> List<T> query(String tableNamele, String[] selection, String[] selectionArgs, String orderBy,Class<T> classZ) {
        String[] columns = dbUtils.getTableFieldName(classZ);
        StringBuffer sb = new StringBuffer();
        if(null != selection) {
            for (String s : selection) {
                sb = sb.append(s);
            }
        }
        Cursor cursor = db.query(tableNamele,columns,sb.toString(),selectionArgs,"","",orderBy);
        List<T> list = dbUtils.<T>getModel(cursor,classZ);

        return list;
    }
}
