package com.jredthree.dbtest.db.dao;

import java.io.Serializable;
import java.util.List;

/**
 * author: smart
 * time: 2016/12/15
 */

interface RootDao<T, PK extends Serializable> {

    T get(PK id);

    List<T> findAll();

    PK save(T entity);

    void update(T entity);

    void delete(PK id);

}
