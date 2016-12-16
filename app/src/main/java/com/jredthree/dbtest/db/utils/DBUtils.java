package com.jredthree.dbtest.db.utils;

import android.content.ContentValues;
import android.database.Cursor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * author: smart
 * time: 2016/12/14
 */

public class DBUtils {

    /**
     * 解析数据得到ContentValues
     * @param object
     * @return
     */
    public  ContentValues getContentValues(Object object){
        final ContentValues cv = new ContentValues();
        Class c = object.getClass();
        Field[] fields = c.getDeclaredFields();
        Method[] methods = c.getDeclaredMethods();

        for(Field field : fields) {
            final String fieldName = field.getName();
            String typeName = field.getType().getSimpleName();
            String getMethodName;
            if (typeName.equalsIgnoreCase("boolean")) {
                getMethodName = "is" + fieldName;
            } else {
                getMethodName = "get" + fieldName;
            }

            //找到对应的get方法
            Method method = getMethod(getMethodName, methods);
            //获取方法返回值得类型
            if (null != method) {
                String className = method.getReturnType().getSimpleName();

                try {
                    Object o = method.invoke(object);
                    getResult(className, o, new ValueTypeInterface() {
                        @Override
                        public void getValue(Integer result) {
                            cv.put(fieldName, result);
                        }

                        @Override
                        public void getValue(Boolean result) {
                            cv.put(fieldName, result);
                        }

                        @Override
                        public void getValue(Float result) {
                            cv.put(fieldName, result);
                        }

                        @Override
                        public void getValue(Double result) {
                            cv.put(fieldName, result);
                        }

                        @Override
                        public void getValue(Long result) {
                            cv.put(fieldName, result);
                        }

                        @Override
                        public void getValue(String result) {
                            cv.put(fieldName, result);
                        }

                        @Override
                        public void getValue(Byte result) {
                            cv.put(fieldName, result);
                        }
                    });
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }

        return cv;
    }

    /**
     * 从方法数组中查询得到方法
     * @param methodName
     * @param methods
     * @return
     */
    private Method getMethod(String methodName,Method[] methods){
        Method method = null;
        for(Method m : methods){
            if(methodName.equalsIgnoreCase(m.getName())){
                method = m;
            }
        }
        return method;
    }

    private  <T> T getValue(Object object){

        return (T) object;
    }

    /**
     * 获取模型
     * @param cursor
     * @param classZ
     * @param <T>
     * @return
     */
    public  <T> List<T> getModel(final Cursor cursor,Class<T> classZ){

        int resultCounts = cursor.getCount();

        if (resultCounts == 0) {
            return null;
        }

        Class <T> c = classZ;
        Field[] fields = c.getDeclaredFields();
        Method[] methods = c.getDeclaredMethods();
        List<T> list = new ArrayList<>();
        while(cursor.moveToNext()) {
           T a = getInstance(c);
            for (Field f : fields) {

                String fieldName = f.getName();

                String typeName = f.getType().getSimpleName();

                String setMethodName = "set" + fieldName;

                //找到对应的get方法
                Method method = getMethod(setMethodName, methods);

                final int columnIndex = cursor.getColumnIndex(fieldName);

                final Object[] ot = {new Object()};
                Object oj = null;
                getResult(typeName, oj, new ValueTypeInterface() {
                    @Override
                    public void getValue(Integer result) {
                        ot[0] = cursor.getInt(columnIndex);
                    }

                    @Override
                    public void getValue(Boolean result) {
                        int intresult = cursor.getInt(columnIndex);
                        if(intresult == 0)
                            ot[0] = false;
                        else
                            ot[0] = true;
                    }

                    @Override
                    public void getValue(Float result) {
                        ot[0] = cursor.getFloat(columnIndex);
                    }

                    @Override
                    public void getValue(Double result) {
                        ot[0] = cursor.getDouble(columnIndex);
                    }

                    @Override
                    public void getValue(Long result) {
                        ot[0] = cursor.getLong(columnIndex);
                    }

                    @Override
                    public void getValue(String result) {
                        ot[0] = cursor.getString(columnIndex);
                    }

                    @Override
                    public void getValue(Byte result) {
                        ot[0] = cursor.getBlob(columnIndex);
                    }
                });

                try {
                    method.invoke(a, ot);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
            list.add(a);
        }

        return list;
    }

    /**
     * 通过反射去实例化相应类
     *
     * @param <T> 返回实例的泛型类型
     * @return
     */
    public static <T> T getInstance(Class clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public  String[] getTableFieldName(Class<?> targetClass) {
        Field[] fields = targetClass.getDeclaredFields();
        String[] keys = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            keys[i] = fields[i].getName();
        }
        return keys;
    }

    private void getResult(String type,Object object,ValueTypeInterface vti){
        if(type.equalsIgnoreCase("int") || type.equalsIgnoreCase("Integer")){
            vti.getValue((Integer) getValue(object));
        }else if(type.equalsIgnoreCase("String")){
            vti.getValue((String) getValue(object));
        }else if(type.equalsIgnoreCase("Boolean")){
            vti.getValue((Boolean) getValue(object));
        }else if(type.equalsIgnoreCase("Float")){
            vti.getValue((Float) getValue(object));
        }else if(type.equalsIgnoreCase("Double")){
            vti.getValue((Double) getValue(object));
        }else if(type.equalsIgnoreCase("Long")){
            vti.getValue((Long) getValue(object));
        }else{
            vti.getValue((Byte) getValue(object));
        }
    }

    /**
     * 结果类型接口
     */
    private interface ValueTypeInterface{
        void getValue(Integer result);
        void getValue(Boolean result);
        void getValue(Float result);
        void getValue(Double result);
        void getValue(Long result);
        void getValue(String result);
        void getValue(Byte result);
    }
}
