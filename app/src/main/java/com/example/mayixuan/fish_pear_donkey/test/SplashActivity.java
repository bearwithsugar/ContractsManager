package com.example.mayixuan.fish_pear_donkey.test;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mayixuan.fish_pear_donkey.MainActivity;
import com.example.mayixuan.fish_pear_donkey.R;

public class SplashActivity extends AppCompatActivity {

    protected Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );

        mHandler.postDelayed( new Runnable() {
            @Override
            public void run() {
                startActivity( new Intent(SplashActivity.this,MainActivity.class) );
            }
        },2000 );

    }
}
