package com.jredthree.dbtest;

import java.io.Serializable;

/**
 * author: smart
 * time: 2016/12/14
 */

public class User implements Serializable {
    private String name;
    private int old;
    private boolean sex;
    private float number;
    private double price;
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
