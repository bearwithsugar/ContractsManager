package com.example.mayixuan.fish_pear_donkey.test;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.mayixuan.fish_pear_donkey.MainActivity;
import com.example.mayixuan.fish_pear_donkey.R;


public class SecondActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView button;

    String[] names = new String[]{"空白页","劳动合同","购销合同","加盟合同","经营合同","入股合同"};
    int[] icons = new int[] {R.drawable.kongbai,R.drawable.laodong,R.drawable.gouxiao,R.drawable.jiameng,R.drawable.jingying,R.drawable.rugu};
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature( Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.second_main);

        button=findViewById( R.id.returnbutton );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SecondActivity.this).toBundle());
            }
        } );
//        ActionBar actionBar = this.getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        //activity类中的方法
        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode_lefttoright );
        Transition explode2 = TransitionInflater.from(this).inflateTransition(R.transition.explode_righttoleft );
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode2);
        getWindow().setReenterTransition(explode);


        recyclerView = (RecyclerView) findViewById( R.id.recyclerview);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(SecondActivity.this,names,icons);
        recyclerView.setLayoutManager(new GridLayoutManager(SecondActivity.this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        //startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SecondActivity.this).toBundle());
        int spanCount = 2;
        int spacing = 10;
        boolean includeEdge = false;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
    }

}
