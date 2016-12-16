package com.jredthree.dbtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jredthree.dbtest.db.DaoHelp;
import com.jredthree.dbtest.db.dao.UserDao;
import com.jredthree.dbtest.db.dao.UserDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   /* public static final String USERTABLE = "users_table";

    public static final String USERDBSQL = "create table if not exists "
            + USERTABLE
            + " (id integer primary key autoincrement,"
            + " name varchar(50),"
            + " old integer,"
            + " sex boolean,"
            + " number varchar(100),"
            + " price varchar(100),"
            + " count varchar(100))";*/

    UserDao dao= new UserDaoImpl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 先初始化model
         */
        List<Class<?>> classes = new ArrayList<>();
        classes.add(User.class);
        DaoHelp.getInstance().initDaoHelp(this,"user",classes,new String[]{},1);
      /*  AnnotationUtils.parserAnnotationDb(User.class);*/

        findViewById(R.id.btnInsert).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user = new User();
                        user.setName("jredthree");
                        user.setOld(13);
                        user.setSex(true);
                        user.setNumber(123);
                        user.setPrice(333.4);
                        user.setCount(100000);
                        dao.save(user);
                    }
                }
        );

        findViewById(R.id.btnDetele).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dao.delete("14");
                    }
                }
        );

        findViewById(R.id.btnUpdate).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user = new User();
                        user.setName("小名");
                        user.setOld(14);
                        user.setSex(false);
                        user.setNumber(12333);
                        user.setPrice(333.43);
                        user.setCount(22222222);
                        dao.update(user);
                    }
                }
        );

        findViewById(R.id.btnQuery).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user = dao.get("15");
                        Log.i("TAG",user.toString());
                    }
                }
        );


    }


}
