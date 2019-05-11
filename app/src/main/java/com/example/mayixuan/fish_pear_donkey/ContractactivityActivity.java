package com.example.mayixuan.fish_pear_donkey;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mayixuan.fish_pear_donkey.Database.Dao_Contract;

public class ContractactivityActivity extends AppCompatActivity {

    private Button storeButton;
    private EditText edit_name,edit_content;
    Dao_Contract insert=new Dao_Contract(this);
    private ImageView drawable;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getWindow().requestFeature( Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView( R.layout.contractactivity );

//        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode_lefttoright );
//        Transition explode2 = TransitionInflater.from(this).inflateTransition(R.transition.explode_righttoleft );
//        getWindow().setExitTransition(explode2);
//        getWindow().setEnterTransition(explode2);
//        getWindow().setReenterTransition(explode);

        storeButton=(Button) findViewById(R.id.storeButton);
        edit_name=(EditText) findViewById(R.id.edit_name);
        edit_content=(EditText) findViewById(R.id.edit_content);
        drawable=(ImageView)findViewById(R.id.returnbutton);

        storeButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=String.valueOf( edit_name.getText() );
                String content=String.valueOf( edit_content.getText() );
                if(name.length()==0) {
                    Toast.makeText(ContractactivityActivity.this,"合同名不能为空。",Toast.LENGTH_SHORT).show();
                }
                else {
                        insert.insert(name,content);
                        Toast.makeText(ContractactivityActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent( ContractactivityActivity.this,MainActivity.class );
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ContractactivityActivity.this).toBundle());


                }


            }
        } );
        drawable.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( ContractactivityActivity.this,MainActivity.class );
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(ContractactivityActivity.this).toBundle());
            }
        } );



    }
}
