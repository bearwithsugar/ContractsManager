package com.example.mayixuan.fish_pear_donkey.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.ByteArrayOutputStream;

import static android.content.ContentValues.TAG;

/**
 * Created by mayixuan on 2018/4/17.
 */

public class Dao_Picture {


    private final DatabaseHelpr_Picture helpr;
    private Context context;




    public Dao_Picture(Context context){
        //创建数据库
        helpr = new DatabaseHelpr_Picture(context);

    }

    public void insert(Drawable tmp){
        Bitmap bmp = (((BitmapDrawable)tmp).getBitmap());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
        ContentValues values = new ContentValues();
        values.put("image", os.toByteArray());

        SQLiteDatabase database=helpr.getReadableDatabase();
        String sql="insert into signature(signature_id,signature_name,avatar)" +
                "values(?,?,?)";
        database.execSQL(sql,new Object[]{1,"a",values});
//        database.insert("apps", null, values);

        database.close();





//        execSQL()方法可以执行insert、delete、update和CREATE TABLE之类有更改行为的SQL语句
//        rawQuery()方法用于执行select语句。


    }

    public BitmapDrawable show(String name){
        SQLiteDatabase database=helpr.getReadableDatabase();
        String sql="select avatar from signature where signature_name = ?";
        Cursor cursor=database.rawQuery(sql,new String[]{name});
        byte[] blob = new byte[0];
        if(cursor.moveToFirst()){
            blob = cursor.getBlob(cursor.getColumnIndex("avatar"));
            Log.d(TAG,"cg");

        }else {
            Log.d(TAG,"失败");
        }
        Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
        BitmapDrawable bd = new BitmapDrawable(bmp);
        cursor.close();
        return bd;



    }


}
