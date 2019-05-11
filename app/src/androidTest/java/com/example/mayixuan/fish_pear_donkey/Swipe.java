package com.example.mayixuan.fish_pear_donkey;

import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;

/**
 * Created by mayixuan on 2018/4/9.
 */

public class Swipe extends BaseSwipeAdapter {
    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        SwipeLayout swipeLayout = null;
        swipeLayout.getSurfaceView().setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        } );
        return null;
    }

    @Override
    public void fillValues(int position, View convertView) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
