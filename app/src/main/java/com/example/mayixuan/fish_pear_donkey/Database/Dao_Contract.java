package com.example.mayixuan.fish_pear_donkey.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mayixuan on 2018/3/28.
 */

public class Dao_Contract {


    private final DatabaseHelpr helpr;




    public Dao_Contract(Context context){
        //创建数据库
        helpr = new DatabaseHelpr(context);

    }

    public void insert(String contract_name, String contract_content){
        SQLiteDatabase database=helpr.getReadableDatabase();
        String sql="insert into "+Constants.TABLE_NAME+"(contract_id,contract_name,contract_content)" +
                "values(?,?,?)";
        database.execSQL(sql,new Object[]{null,contract_name,contract_content});
        database.close();


//        execSQL()方法可以执行insert、delete、update和CREATE TABLE之类有更改行为的SQL语句
//        rawQuery()方法用于执行select语句。


    }

    public void delete(String contract_name){
        SQLiteDatabase database=helpr.getReadableDatabase();
        String sql="delete from contract where contract_name = ?";
        database.execSQL(sql,new Object[]{contract_name});
        database.close();



    }

    public void updatecontent(String contract_name,String contract_content ){
        SQLiteDatabase database=helpr.getReadableDatabase();
        String sql="update contract set contract_content = ? where contract_name = ? ";
        database.execSQL(sql,new Object[]{contract_name,contract_content});
        database.close();


    }

    public int queryCount(){
        SQLiteDatabase database=helpr.getReadableDatabase();
        String sql="select * from contract";
        Cursor cursor=database.rawQuery(sql,null);
        int count=cursor.getCount();
        cursor.close();
//hou

        return count;

    }
    public Cursor queryContractName(){
        SQLiteDatabase database=helpr.getReadableDatabase();
        String sql="select * from contract order by contract_id desc";
        Cursor cursor=database.rawQuery(sql,null);

        return cursor;

    }

    public Cursor queryContractContent(String name){
        SQLiteDatabase database=helpr.getReadableDatabase();
        String sql="select contract_content from contract where contract_name = ?";
        Cursor cursor=database.rawQuery(sql,new String[]{name});

        return cursor;

    }











}
