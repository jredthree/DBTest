package com.jredthree.dbtest.db;

import android.content.Context;

import com.jredthree.dbtest.db.action.DBAction;
import com.jredthree.dbtest.db.action.DBActionImpl;

/**
 * author: smart
 * time: 2016/12/15
 */

public class DaoHelp {
    private static DaoHelp daoHelp ;
    private DBAction dbAction;
    private String dbName;
    private String[] createSql;
    private String[] updateTableName;
    private int version;

    private DaoHelp() {

    }

    public static DaoHelp getInstance(){
        if(daoHelp == null ){
            daoHelp = new DaoHelp();

        }
        return daoHelp ;
    }

    /**
     * 初始化数据操作
     * @param context 环境
     * @param dbName 数据库名字
     * @param createSql 初始化建表语句
     * @param updateTableName 更新表结构语句
     * @param version 数据库版本
     */
    public void initDaoHelp(Context context,String dbName,String[] createSql,String[] updateTableName,int version){
        dbAction = new DBActionImpl(context);
        this.dbName = dbName;
        this.createSql = createSql;
        this.updateTableName = updateTableName;
        this.version = version;
    }

    public void open(){
        dbAction.open(dbName, createSql ,updateTableName,version);
    }

    public void close(){
        dbAction.close();
    }

    public DBAction getDao() {
        return dbAction;
    }

    public void setDao(DBAction dbAction) {
        this.dbAction = dbAction;
    }
}
