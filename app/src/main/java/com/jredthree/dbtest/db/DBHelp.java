package com.jredthree.dbtest.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * author: smart
 * time: 2016/12/15
 */

public class DBHelp extends SQLiteOpenHelper{

    private String[] createTable;
    private String[] updateTableName;

    private DBHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private DBHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DBHelp(Context context,String name,String[] createTable,String[] updateTavleName,int version){
        this(context,name,null,version);
        this.createTable = createTable;
        this.updateTableName = updateTavleName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          for(String s : createTable){
              db.execSQL(s);
          }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        for(String s : updateTableName) {
            db.execSQL("DROP TABLE IF EXISTS " + s);
        }
        onCreate(db);
    }

}
