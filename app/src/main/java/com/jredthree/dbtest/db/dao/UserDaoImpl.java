package com.jredthree.dbtest.db.dao;

import android.util.Log;

import com.jredthree.dbtest.User;
import com.jredthree.dbtest.db.DaoHelp;

import java.util.List;

/**
 * author: smart
 * time: 2016/12/15
 */
public class UserDaoImpl implements UserDao {

    private DaoHelp daoHelp = DaoHelp.getInstance();
    private  String USERTABLE = "users_table";
    @Override
    public User get(String id) {
        daoHelp.open();
        List<User> list = daoHelp.getDao().query(USERTABLE,new String[]{"id=?"},new String[]{id},null,User.class);
        daoHelp.close();
        if(null != list && list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    @Override
    public List<User> findAll() {
        daoHelp.open();
        List<User> list = daoHelp.getDao().query(USERTABLE,null,null,null,User.class);
        daoHelp.close();
        Log.i("user","======"+list.size());
        return null;
    }

    @Override
    public String save(User entity) {
        daoHelp.open();
        Long a = daoHelp.getDao().insert(USERTABLE,entity);
        daoHelp.close();
        return String.valueOf(a);
    }

    @Override
    public void update(User entity) {
        daoHelp.open();
        Long a = daoHelp.getDao().update(USERTABLE,entity,new String[]{"id=?"},new String[]{"15"});
        daoHelp.close();
    }

    @Override
    public void delete(String id) {
        daoHelp.open();
        Long a = daoHelp.getDao().delete(USERTABLE,new String[]{"id=?"},new String[]{"14"});
        daoHelp.close();
    }
}
