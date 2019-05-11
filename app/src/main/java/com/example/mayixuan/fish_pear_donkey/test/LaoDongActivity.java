package com.example.mayixuan.fish_pear_donkey.test;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mayixuan.fish_pear_donkey.Database.Dao_Contract;
import com.example.mayixuan.fish_pear_donkey.MainActivity;
import com.example.mayixuan.fish_pear_donkey.R;
import com.example.mayixuan.fish_pear_donkey.word.SpanUtils;

public class LaoDongActivity extends AppCompatActivity {
    /*创建一个Drawerlayout和Toolbar联动的开关*/
    private android.support.v7.widget.Toolbar toolbar;
    private android.support.design.widget.NavigationView navigationview;
    private android.support.v4.widget.DrawerLayout drawerlayout;
    private ActionBarDrawerToggle toggle;
    private EditText mycontent;

    private EditText mycontent_name;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature( Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView( R.layout.activity_laodong);
        mycontent = (EditText) findViewById(R.id.mycontent);
        mycontent_name=findViewById( R.id.mycontent_name );

        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode_lefttoright );
        Transition explode2 = TransitionInflater.from(this).inflateTransition(R.transition.explode_righttoleft );
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode2);
        getWindow().setReenterTransition(explode2);




        //劳动合同
        mycontent.setText(new SpanUtils().appendLine("劳 动 合 同 书").setFontSize(32, true).setAlign(Layout.Alignment.ALIGN_CENTER).setLineHeight(100)
                .append("甲方（单位）：").setBold().setLineHeight(100)
                .appendLine("                     ").setUnderline()
                .append("乙方（个人）：").setBold().setLineHeight(100)
                .appendLine("                     ").setUnderline()
                .appendLine("根据《中华人民共和国劳动法》、《中华人民共和国劳动合同法》和有关法律、法规，甲乙双方等自愿、协商一致签订本合同，共同遵守本合同所列条款。").setLeadingMargin(60, 10)
                .appendLine("一、劳动合同双方当事人基本情况").setBold().setLeadingMargin(60, 10)
                .appendLine("第一条：甲方_____法定代表人_____(主要负责人)或委托代理人____注册地址_________经营地址_________")
                .appendLine("第二条：乙方_____性别__户籍____________类型（非农业、农业） 紧急联系人______居民身份证号码__________________法律文件约定送达地__________家庭住址_____________邮政编码________在京居住地址_________邮政编码______户口所在地____省(市) 区(县)______街道(乡镇)").setLeadingMargin(60, 10)                .appendLine("二、劳动合同期限").setBold().setLineHeight(100)
                .appendLine("第三条：本合同为为固定期限的劳动合同。").setLeadingMargin(60, 10)
                .appendLine("本合同于____年__月__日生效，合同期限自____年__月__日至____年__月__日，其中试用期自____年__月__日至____年__月__日。").setLeadingMargin(60, 10)
                .appendLine("三、工作内容和工作地点").setBold()
                .appendLine("第四条:乙方同意根据甲方工作需要，担任_____工作").setLeadingMargin(60, 10)
                .appendLine("第五条:根据甲方的岗位（工种）作业特点，乙方的工作区域或工作地点为________。").setLeadingMargin(60, 10)
                .appendLine("第六条:乙方工作应达到___________标准。").setLeadingMargin(60, 10)
                .appendLine("四、工作时间和休息休假").setBold()
                .appendLine("第七条：甲方安排乙方执行_____工时制度。").setLeadingMargin(60, 10)
                .appendLine("执行标准工时制度的，乙方每天工作时间不超过8小时，每周工作不超过40小时。每周休息日为_____")
                .appendLine("第八条:甲方对乙方实行的休假制度有________。").setLeadingMargin(60, 10)
                .appendLine("五、劳动报酬").setBold()
                .appendLine("第九条:甲方每月__日前以货币形式支付乙方工资，月工资为____元或按______执行。").setLeadingMargin(60, 10)
                .appendLine("六、社会保险及其他保险福利待遇").setBold()
                .appendLine("第十条:甲乙双方按国家和 市的规定参加社会保险。甲方为乙方办理有关社会保险手续，并承担相应社会保险义务。").setLeadingMargin(60, 10)
                .appendLine("第十一条:乙方患病或非因工负伤的医疗待遇按国家____市有关规定执行。").setLeadingMargin(60, 10)
                .appendLine("第十二条:乙方患职业病或因工负伤的待遇按国家和____市的有关规定执行。").setLeadingMargin(60, 10)
                .appendLine("第十三条:甲方为乙方提供以下福利待遇").setLeadingMargin(60, 10)
                .appendLine("第十四条:甲方根据生产岗位的需要，按照国家有关劳动安全、卫生的规定为乙方配备必要的安全防护措施，发放必要的劳动保护用品。").setLeadingMargin(60, 10)
                .appendLine("第十五条:甲方根据国家有关法律、法规，建立安全生产制度；乙方应当严格遵守甲方的劳动安全制度，严禁违章作业，防止劳动过程中的事故，减少职业危害。").setLeadingMargin(60, 10)
                .appendLine("八、劳动纪律").setBold()
                .appendLine("第十六条:乙方应自觉遵守国家和地方的有关法律法规以及甲方依法制定的员工手册和其他规章制度，服从甲方的管理，按时完成工作任务，保守甲方的商业秘密。甲方有权对乙方执行员工手册和其他规章制度的情况进行检查、监督和奖惩。").setLeadingMargin(60, 10)
                .appendLine("九、劳动合同的解除、终止和经济补偿").setBold()
                .appendLine("第十七条:乙方有下列情形之一的，甲方可以不经提前通知且无需向乙方支付任何经济补偿金而解除本合同：").setLeadingMargin(60, 10)
                .appendLine("1）在试用期间被证明不符合录用条件的；").setLeadingMargin(60, 10)
                .appendLine("2）严重违反甲方的规章制度的；").setLeadingMargin(60, 10)
                .appendLine("3）严重失职，营私舞弊，给甲方造成重大损害的；").setLeadingMargin(60, 10)
                .appendLine("4）乙方同时与其他用人单位建立劳动关系，对完成甲方的工作任务造成严重影响，或者经甲方提出，拒不改正的；").setLeadingMargin(60, 10)
                .appendLine("5）乙方以欺诈、胁迫的手段或者乘人之危，使甲方在违背真实意思的情况下订立或者变更本合同、致使本合同无效的；").setLeadingMargin(60, 10)
                .appendLine("6）每月旷工三天及以上的；").setLeadingMargin(60, 10)
                .appendLine("第十八条:甲乙双方解除、终止劳动合同应当依照《中华人民共和国劳动合同法》和国家及 市有关规定执行。").setLeadingMargin(60, 10)
                .appendLine("第十九条:乙方应当按照双方约定，办理工作交接。应当支付经济补偿的，在办结工作交接时支付。").setLeadingMargin(60, 10)
                .appendLine("十、双方约定的其他内容").setBold()
                .appendLine("第二十条:甲乙双方约定增加包括但不限于以下内容作为本合同附件：以上内容作为劳动合同附件，与劳动合同具有同等法律效力。").setLeadingMargin(60, 10)
                .appendLine("十一、劳动争议处理及其它").setBold()
                .appendLine("第二十一条:双方因履行本合同发生争议，当事人可以向劳动争议调解委员会申请调解；调解不成的，可以向劳动争议仲裁委员会申请仲裁。").setLeadingMargin(60, 10)
                .appendLine("第二十二条:本合同的附件如下").setLeadingMargin(60, 10)
                .appendLine("第二十三条:本合同未尽事宜或与今后国家、 市有关规定相悖的，按有关规定执行。").setLeadingMargin(60, 10)
                .appendLine("第二十四条:本合同一式两份，甲乙双方各执一份。").setLeadingMargin(60, 10)
                .appendLine("甲方：（公章）    ").setAlign(Layout.Alignment.ALIGN_OPPOSITE)
                .appendLine("乙方：法定代表人或代理人：    ").setAlign(Layout.Alignment.ALIGN_OPPOSITE)
                .appendLine("     年 月 日").setAlign(Layout.Alignment.ALIGN_OPPOSITE)
                .create());


        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/PingFang.ttf"  );
        mycontent.setTypeface( typeface );


        /*初始化View*/
        initViews();

        /*隐藏滑动条*/
        hideScrollBar();

        /*设置ActionBar*/
        setActionBar();

         /*设置Drawerlayout开关*/
        setDrawerToggle();

        /*设置监听器*/
        setListener();

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                //使EditText触发一次失去焦点事件
                v.setFocusable(false);
//                v.setFocusable(true); //这里不需要是因为下面一句代码会同时实现这个功能
                v.setFocusableInTouchMode(true);
                return true;
            }
        }
        return false;
    }
    /*初始化View*/
    private void initViews() {
        this.drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.navigationview = (NavigationView) findViewById(R.id.navigation_view);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    /*去掉navigation中的滑动条*/
    private void hideScrollBar() {
        navigationview.getChildAt(0).setVerticalScrollBarEnabled(false);
    }

    /*设置ActionBar*/
    private void setActionBar() {
        setSupportActionBar(toolbar);
        /*显示Home图标*/

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*设置Drawerlayout的开关,并且和Home图标联动*/
    private void setDrawerToggle() {
        toggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, 0, 0);

        drawerlayout.addDrawerListener(toggle);
        /*同步drawerlayout的状态*/
        toggle.syncState();
    }

    /*设置监听器,并做相应处理*/
    private void setListener() {
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.single_1:

                        break;
                    case R.id.single_2:
                        String name=String.valueOf( mycontent_name.getText() );
                        String content=String.valueOf( mycontent.getText() );
                        Dao_Contract insert=new Dao_Contract(LaoDongActivity.this);
                        if(name.length()==0) {
                            Toast.makeText(LaoDongActivity.this,"合同名不能为空。",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            insert.insert(name,content);
                            Toast.makeText(LaoDongActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent( LaoDongActivity.this,MainActivity.class );
                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LaoDongActivity.this).toBundle());


                        }
                        break;
                    case R.id.single_3:
                        break;
                    case R.id.single_4:
                        finish();
                    case R.id.single_5:
                        break;
                    case R.id.single_6:
                        break;
                }
                drawerlayout.closeDrawer( GravityCompat.START);
                return true;
            }
        });
    }
   // @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                drawerlayout.openDrawer(GravityCompat.START);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
