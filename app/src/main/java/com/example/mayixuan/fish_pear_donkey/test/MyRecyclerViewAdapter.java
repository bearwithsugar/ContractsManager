package com.example.mayixuan.fish_pear_donkey.test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mayixuan.fish_pear_donkey.R;

import static android.content.ContentValues.TAG;


/**
 * Created by asus on 2018/4/12.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private  Context context;
    private String[] names;


   // private ArrayList<String> names;
    private  int[] icons;
    private  MyItemClickListener onItemClickListener;


    public MyRecyclerViewAdapter(Context context,String[] names, int[] icons) {
        this.context = context;
        this.names = names;
        this.icons = icons;

    }

    /**
     * 相当于于getView方法中创建View和ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==-1){
            Log.e(TAG,"Nothing");
        }
        View itemView = View.inflate(context, R.layout.item_recyclerview,null);
        MyViewHolder vh = new MyViewHolder(itemView,onItemClickListener);
        //return new MyViewHolder(itemView);
        return  vh;
    }

    /**相当于于getView绑定数据部分的代码
     * 数据和View绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //根据位置得到对应的数据
        holder.item_icon.setImageResource(icons[position]);
        holder.item_title.setText(names[position]);


    }

    /**
     * 得到总条数
     * @return
     */
    @Override
    public int getItemCount() {
        return icons.length;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView item_icon;
        private TextView item_title;
        private MyItemClickListener mListener;
        private ImageView returnbutton;


        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public MyViewHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            item_icon = itemView.findViewById(R.id.item_icon);
            item_title = itemView.findViewById(R.id.item_name);
            returnbutton=itemView.findViewById( R.id.returnbutton );
            this.mListener = listener;

//            returnbutton.setOnClickListener( new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, MainActivity.class);
//                    context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity)context).toBundle());
//                }
//            } );





            /**
             * �������
             */

            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    /**
                     * 决定了新Activity的形态属性
                     */
                    int number=getLayoutPosition();
                    switch (number) {
                        case 0:
                            Intent kongbai = new Intent(context,KongBaiActivity.class);
                            // mintent.putExtra("name",strname);
                            context.startActivity(kongbai);
                            break;
                        case 1:
                            Intent laodong = new Intent(context, LaoDongActivity.class);
//                            Activity activity=(Activity) context;
                            context.startActivity(laodong, ActivityOptions.makeSceneTransitionAnimation((Activity)context).toBundle());
                            // mintent.putExtra("name",strname);
                            //context.startActivity(laodong);

                            break;
                        case 2:
                            Intent gouxiao = new Intent(context, GouXiaoActivity.class);
                            //qintent.putExtra("name",strname);
                            context.startActivity(gouxiao);
                            break;
                        case 3:
                            Intent jiameng = new Intent(context, JiaMengActivity.class);
                            //lintent.putExtra("name",strname);
                            context.startActivity(jiameng);
                            break;
                        case 4:
                            Intent jinying = new Intent(context, JinYingActivity.class);
                            // pintent.putExtra("name",strname);
                            context.startActivity(jinying);
                            break;
                        case 5:
                            Intent rugu = new Intent(context, RuGuActivity.class);
                            //cintent.putExtra("name",strname);
                            context.startActivity(rugu);
                            break;
                    }
                    }
            });

        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)


    /**
     * 点击RecyclerView某条的监听
     */
    public interface MyItemClickListener {
        public void onItemClick(View view, int postion);
    }
    public interface OnItemClickListener{

        /**
         * 当RecyclerView某个被点击的时候回调
         * @param view 点击item的视图
         * @param data 点击得到的数据
         */
        public void onItemClick(View view, String data);

    }



    /**
     * 设置RecyclerView某个的监听
     * @param onItemClickListener
     */
    public void setOnItemClickListener(MyItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
