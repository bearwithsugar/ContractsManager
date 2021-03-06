package com.example.mayixuan.fish_pear_donkey.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mayixuan on 2018/3/28.
 */

public class DatabaseHelpr extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelpr";

    public DatabaseHelpr(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建时的回调
        Log.d(TAG,"创建数据库。。。");
        String sql="create table "+Constants.TABLE_NAME+"(contract_id integer PRIMARY KEY autoincrement,contract_name varchar,contract_content varchar)";
        //String sql2="create table Signature (contract_id integer PRIMARY KEY autoincrement,contract_name varchar,contract_content varchar)";
        sqLiteDatabase.execSQL(sql);
        //sqLiteDatabase.execSQL(sql2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //升级数据库时的回调
        Log.d(TAG,"升级数据库。。。");

    }
}
