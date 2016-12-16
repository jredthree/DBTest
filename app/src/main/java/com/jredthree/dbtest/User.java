package com.jredthree.dbtest;

import com.jredthree.dbtest.db.annotation.Column;
import com.jredthree.dbtest.db.annotation.GeneratedValue;
import com.jredthree.dbtest.db.annotation.Id;
import com.jredthree.dbtest.db.annotation.Table;

import java.io.Serializable;

/**
 * author: smart
 * time: 2016/12/14
 */

@Table(name = "user_table")
public class User implements Serializable {
    @Id
    @GeneratedValue(value = GeneratedValue.GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name",length = 10,nullable = false)
    private String name;
    @Column(name = "old",length = 20,nullable = false,insertable = false,updateable = false)
    private int old;
    @Column(name = "sex",nullable = false)
    private boolean sex;
    @Column(name = "number")
    private float number;
    @Column(name = "price")
    private double price;
    @Column(name = "count",nullable = false)
    private long count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", old=" + old +
                ", sex=" + sex +
                ", number=" + number +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
