package com.example.mayixuan.fish_pear_donkey;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mayixuan.fish_pear_donkey.Database.Dao_Contract;
import com.example.mayixuan.fish_pear_donkey.Database.DatabaseHelpr;
import com.example.mayixuan.fish_pear_donkey.fragment.ContentFragment;
import com.example.mayixuan.fish_pear_donkey.test.SecondActivity;
import com.example.mayixuan.interfaces.Resourceble;
import com.example.mayixuan.interfaces.ScreenShotable;
import com.example.mayixuan.model.SlideMenuItem;
import com.example.mayixuan.util.ViewAnimator;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;


public class MainActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    //侧滑菜单效果的控件
    private DrawerLayout drawerLayout;
    //菜单展开和隐藏的监听器
    private ActionBarDrawerToggle drawerToggle;
    //声明左菜单栏数组
    private List<SlideMenuItem> list = new ArrayList<>();
    //实例化fragment
    private ContentFragment contentFragment;
    private ViewAnimator viewAnimator;
    //将图片用一个int变量表示
    private int res = R.drawable.content_music;
    //声明布局，用于点击主界面时菜单栏收起
    private LinearLayout linearLayout;

    private LinearLayout lineartest;
    //声明新建合同所需控件
    private Button storeButton;
    private EditText edit_content;
    private TextView view_name;

    //private Drawable drawable;





    List<Contract> contracts;
    Contract contract;


    Dao_Contract query=new Dao_Contract(this);
    Dao_Contract queryname=new Dao_Contract(this);
    Dao_Contract delete=new Dao_Contract(this);
    Dao_Contract querycontent=new Dao_Contract(this);





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature( Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);


        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode_lefttoright );
        Transition explode2 = TransitionInflater.from(this).inflateTransition(R.transition.explode_righttoleft );
       // getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        getWindow().setReenterTransition(explode);





        RefreshLayout refreshLayout=(RefreshLayout)findViewById( R.id.refreshLayout );
        refreshLayout.setOnRefreshListener( new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        } );
        refreshLayout.setOnLoadmoreListener( new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        } );


        lineartest= (LinearLayout) findViewById(R.id.content_button);
        ui();
        contracts=new ArrayList<Contract>(  );





        //实例化fragment，并把fragment加到界面去
        //newInstance方法传参
        contentFragment = ContentFragment.newInstance(R.drawable.back_white );
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();



        //找到界面并在菜单打开把界面的颜色设置为透明
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        //找到该线性布局并设置点击事件
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });


        //调用下面的上方工具栏方法和左滑出栏内容
        setActionBar();
        createMenuList();
        //5个参数分别为作为主界面的activity、子按钮列表、实现ScreenShotable接口的信息界面、DrawerLayout、实现viewAnimatorListener接口的主界面
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);
    }



    //设置左侧滑出栏内容
    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.BUILDING, R.drawable.myzhuye );
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.BOOK, R.drawable.mycontract );
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.PAINT, R.drawable.yanzheng );
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.CASE, R.drawable.mysignature );
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.SHOP, R.drawable.pleasewait );
        list.add(menuItem5);
//        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.PARTY, R.drawable.icn_6);
//        list.add(menuItem6);
//        SlideMenuItem menuItem7 = new SlideMenuItem(ContentFragment.MOVIE, R.drawable.icn_7);
//        list.add(menuItem7);

    }


    //已完成
    private void setActionBar() {

        //以下两行为toolbar声明部分
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //显示菜单左上角箭头
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //创建抽屉开关，将toolbar和DrawerLayout作为参数传递给他
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {


            //设置动画效果
            //菜单完全关闭时调用此算法
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            //菜单滑动时调用此算法，slideOffset表示滑动幅度（0-1）
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
//                （1）在菜单未完全打开前设置按钮为不可用，同时调用 ViewAnimatorListener 接口中的 disableHomeButton() 方法清空原先存放按钮视图的列表。
//（2）根据传入的按钮个数生成相应个数的按钮 View，并为每个按钮添加点击事件，当事件发生时回调 onSwitch 方法并关闭菜单列表。
//（3）将其添加到存放按钮视图的列表中。调用 AnimatorListener 接口的addViewToContainer(viewMenu)方法（我们要在 Activity 中人为的将其添加到界面布局中）。在菜单打开动画未完成情况下，将其属性设为不可用。调用animateView（）方法使用 FlipAnimation 类来实现动画设置，Handler 实现延时播放。
            }

            /** Called when a drawer has settled in a completely open state. */
            //菜单被完全打开时调度的算法
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        //菜单的展开与隐藏可以被该监听器监听
        //实现效果
        drawerLayout.setDrawerListener(drawerToggle);
    }

    //已完成
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //下面这句话实现toolbar和drawer的联动，没有这句代码箭头不会随着菜单变换
        drawerToggle.syncState();
    }
    //
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //
    //右侧小菜单点击事件
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings1:
                return true;
            case R.id.action_settings3:
//                Intent intent=new Intent( SecondActivity.this,ContractactivityActivity.class );
//                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SecondActivity.this).toBundle());
                Intent intent=new Intent( MainActivity.this,SecondActivity.class );
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());




            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition) {
        this.res = this.res == R.drawable.back_white ? R.drawable.back_white : R.drawable.back_white;
        View view = findViewById(R.id.content_frame);
        //获取剪辑圆的最终半径
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        //创建这个剪辑圈开始半径为0
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        //修饰动画效果定义动画变化率，此处为加速
        animator.setInterpolator(new AccelerateInterpolator());
        //设置持续时间
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
        //getBitmap()方法,获取 takeScreenShot 为当前界面创建的 Bitmap，用于 Reveal 效果
        //以括号中为背景填充宽高（自动放大）
        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        //替换fragment
        ContentFragment contentFragment = ContentFragment.newInstance(this.res);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
        return contentFragment;
    }


    private LinearLayout replaceLinearLayout(){

        lineartest.removeAllViews();
        DatabaseHelpr helpr=new DatabaseHelpr(this);
        helpr.getWritableDatabase();
        //加载页面所有合同
        int numbercount=query.queryCount();
        String count=String.valueOf(query.queryCount());
        Cursor cursor=queryname.queryContractName();


        while (cursor.moveToNext()){
            String name=cursor.getString( cursor.getColumnIndex( "contract_name" ) );
            String content=cursor.getString( cursor.getColumnIndex( "contract_content" ) );
            Contract c=new Contract( null,name,content);

            contracts.add( c );
        }
        cursor.close();

        if (numbercount==0){
            Toast.makeText(MainActivity.this,"你还没有合同哦！",Toast.LENGTH_SHORT).show();
        }
        else{

            for(int i=0;i<numbercount;i++){

                final LinearLayout ll=(LinearLayout) LayoutInflater.from(MainActivity.this).inflate( R.layout.swipebutton,null );
                final TextView button=(TextView) ll.findViewById( R.id.sbtn );
                final TextView textView=(TextView)ll.findViewById( R.id.stvw );
                Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/PingFang.ttf"  );
                textView.setText(contracts.get( i ).getName());
                textView.setTypeface( typeface );
                button.setText(contracts.get( i ).getContent());

                lineartest.addView( ll );
                lineartest.setVisibility(View.VISIBLE);
                lineartest.setAnimation( AnimationUtils.makeInAnimation( this,true ) );
                //swipeLayout =(SwipeLayout) findViewById( R.id.swipe);
                //swipeLayout.setShowMode( SwipeLayout.ShowMode.LayDown );
            }

//              swipeLayout.findViewById(R.id.deletebtn).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
////                            delete.delete( stvw.getText().toString() );
////                            replaceLinearLayout();
//
//                             Toast.makeText(SecondActivity.this,"成功",Toast.LENGTH_SHORT).show();
//                        }
//                    });

            //swipeLayout.addDrag( SwipeLayout.DragEdge.Left,swipeLayout.findViewById( R.id.bottom_wrapper2 ) );

//            swipeLayout.findViewById(R.id.deletebtn).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    final TextView textView=(TextView)findViewById( R.id.stvw );
//                    delete.delete( textView.getText().toString() );
//                    replaceLinearLayout();
//
//                    Toast.makeText(SecondActivity.this,"成功",Toast.LENGTH_SHORT).show();
//                }
//            });
        }


//        swipeLayout.addRevealListener(R.id.bottom_wrapper_child1, new SwipeLayout.OnRevealListener() {
//            @Override
//            public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {
//                View star = child.findViewById(R.id.);
//                float d = child.getHeight() / 2 - star.getHeight() / 2;
//                ViewHelper.setTranslationY(star, d * fraction);
//                ViewHelper.setScaleX(star, fraction + 0.6f);
//                ViewHelper.setScaleY(star, fraction + 0.6f);
//                int c = (Integer) evaluate(fraction, Color.parseColor("#dddddd"), Color.parseColor("#4C535B"));
//                child.setBackgroundColor(c);
//            }
//        });






//        swipeLayout.addSwipeListener( new SwipeLayout.SwipeListener() {
//            @Override
//            public void onStartOpen(SwipeLayout layout) {
//
//            }
//
//            @Override
//            public void onOpen(SwipeLayout layout) {
//                ImageView textView=(ImageView) findViewById( R.id.deletebtn );
//                textView.setOnClickListener( new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(SecondActivity.this,"bu成功",Toast.LENGTH_SHORT).show();
//                    }
//                } );
//
//            }
//
//            @Override
//            public void onStartClose(SwipeLayout layout) {
//
//            }
//
//            @Override
//            public void onClose(SwipeLayout layout) {
//
//            }
//
//            @Override
//            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
//
//            }
//
//            @Override
//            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
//
//            }
//        } );

        contracts.clear();
        helpr.close();

        return lineartest;
    }
    private LinearLayout ui(){
        LinearLayout ll=(LinearLayout) LayoutInflater.from(MainActivity.this).inflate( R.layout.ui,null );


        lineartest.addView( ll );
        lineartest.setVisibility(View.VISIBLE);
        lineartest.setAnimation( AnimationUtils.makeInAnimation( this,true ) );
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/PingFang.ttf"  );
        TextView textView1=lineartest.findViewById( R.id.head1 );
        TextView textView2=lineartest.findViewById( R.id.head2 );
        TextView textView3=lineartest.findViewById( R.id.head3 );
        TextView textView4=lineartest.findViewById( R.id.head4 );

        textView1.setTypeface( typeface );
        textView2.setTypeface( typeface );
        textView3.setTypeface( typeface );
        textView4.setTypeface( typeface );
        return lineartest;
    }

    private void button_click(TextView stvw){

        lineartest.setVisibility(View.GONE  );
        lineartest.setAnimation( AnimationUtils.makeOutAnimation( this,true ) );
        lineartest.removeAllViews();


        DatabaseHelpr helpr=new DatabaseHelpr(this);
        helpr.getWritableDatabase();



        final LinearLayout ll=(LinearLayout) LayoutInflater.from(MainActivity.this).inflate( R.layout.changecontract,null );
        lineartest.addView( ll );
        lineartest.setVisibility(View.VISIBLE);
        lineartest.setAnimation( AnimationUtils.makeInAnimation( this,true ) );

        storeButton=(Button) findViewById(R.id.storeButton2);
        view_name= (TextView) findViewById(R.id.view_name);
        edit_content=(EditText) findViewById(R.id.edit_content2);
        view_name.setText(stvw.getText());


        Cursor c=querycontent.queryContractContent(view_name.getText().toString());

        while (c.moveToNext()){
            String contract_content=c.getString( c.getColumnIndex( "contract_content" ) );
            contract=new Contract( null,null,contract_content);

        }
        edit_content.setText(contract.getContent());
        storeButton.setText( "保存" );
        storeButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dao_Contract deleteold=new Dao_Contract(MainActivity.this);
                deleteold.delete( view_name.getText().toString());
                Dao_Contract addnew=new Dao_Contract(MainActivity.this);
                addnew.insert(view_name.getText().toString(),edit_content.getText().toString());
                Toast.makeText(MainActivity.this,"更新中。。。",Toast.LENGTH_SHORT).show();
                //清理界面
                lineartest.removeAllViews();
            }
        } );
        helpr.close();
        c.close();



    }



    //参数为：选中的按钮，当前的信息界面，触摸点的Y坐标，点击菜单切换视图时回调，主要用于实现切换效果的实现
    @SuppressLint("ResourceType")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;
            case ContentFragment.BOOK:
                replaceLinearLayout();
                // Toast.makeText(SecondActivity.this,count,Toast.LENGTH_SHORT).show();
                for (int j=0;j<lineartest.getChildCount();j++){

                    final TextView bt=(TextView) lineartest.getChildAt( j ).findViewById( R.id.sbtn );
                    final TextView stvw=(TextView)lineartest.getChildAt( j ).findViewById( R.id.stvw );
//                    final SwipeLayout swipeLayout1=lineartest.getChildAt( j ).findViewById( R.id.swipe );
                    final ImageView button=lineartest.getChildAt( j ).findViewById( R.id.deletebtn );
                    bt.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button_click( stvw );
                        }
                    } );
                    stvw.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button_click( stvw );
                        }
                    } );

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            delete.delete( stvw.getText().toString() );
                            replaceLinearLayout();

                             Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                return screenShotable;
            case  ContentFragment.BUILDING:
                lineartest.removeAllViews();
                ui();
                Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_SHORT).show();
                return screenShotable;
            case  ContentFragment.PAINT:
                Intent intent=new Intent( MainActivity.this,Yanzheng.class );
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                return screenShotable;

            case   ContentFragment.CASE:
                Intent intent2=new Intent( MainActivity.this,Signature.class );
                startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                return screenShotable;

//            case   ContentFragment.SHOP:
////                saveImage( url );
////                readImage();
//                drawable=getDrawable( R.drawable.pay );
//                DatabaseHelpr_Picture helpr= new DatabaseHelpr_Picture(this);
//                helpr.getWritableDatabase();
//                Dao_Picture dao_picture=new Dao_Picture( this );
//                dao_picture.insert( drawable );
//                return screenShotable;
//
//            case   ContentFragment.PARTY:
//                Dao_Picture showpicture=new Dao_Picture( this );
//                //Drawable drawable=new BitmapDrawable(  );
//                //showpicture.show();
//
//                //imageView.setImageDrawable(showpicture.show("a")  );
//
//                lineartest.removeAllViews();
//                final LinearLayout ll=(LinearLayout) LayoutInflater.from(MainActivity.this).inflate( R.layout.show_signature,null );
//
//                ImageView imageView=findViewById( R.id.show_signature );
//                imageView.setBackgroundDrawable( showpicture.show("a") );
//// imageView=findViewById( R.drawable.laodong );
//                lineartest.addView( ll );
//                return screenShotable;





            default:
                lineartest.setVisibility(View.GONE  );
                lineartest.setAnimation( AnimationUtils.makeOutAnimation( this,true ) );
                return replaceFragment(screenShotable, position);
        }

    }

//    private void readImage(){
//
//        byte[] imgData=helpr.readImage();
//        if (imgData!=null) {
//            //将字节数组转化为位图
//            Bitmap imagebitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
//            //将位图显示为图片
//            image.setImageBitmap(imagebitmap);
//        }else {
//            image.setBackgroundResource(android.R.drawable.menuitem_background);
//        }
//    }
//
//    private void saveImage(String url){
//        helpr.saveImage();
//    }



    //菜单未完全打开前设置按钮为不可用同时调用下面方法清空原先存放按钮视图的列表
    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    //和上面的方法一样处理控制器状态
    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    //向开发者的MenuLayout上添加Menultem
    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }

//    public Object evaluate(float fraction, Object startValue, Object endValue) {
//        int startInt = (Integer) startValue;
//        int startA = (startInt >> 24) & 0xff;
//        int startR = (startInt >> 16) & 0xff;
//        int startG = (startInt >> 8) & 0xff;
//        int startB = startInt & 0xff;
//
//        int endInt = (Integer) endValue;
//        int endA = (endInt >> 24) & 0xff;
//        int endR = (endInt >> 16) & 0xff;
//        int endG = (endInt >> 8) & 0xff;
//        int endB = endInt & 0xff;
//
//        return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
//                (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
//                (int) ((startG + (int) (fraction * (endG - startG))) << 8) |
//                (int) ((startB + (int) (fraction * (endB - startB))));
//    }


}
