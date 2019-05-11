package com.example.mayixuan.fish_pear_donkey;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Yanzheng extends AppCompatActivity {

    private ImageButton photoButton;
    private ImageView imageIV;

    private final int CAMERA_REQUEST = 1888;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature( Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.yanzheng);

        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode_lefttoright );
        Transition explode2 = TransitionInflater.from(this).inflateTransition(R.transition.explode_righttoleft );
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode2);
        getWindow().setReenterTransition(explode2);

        photoButton = (ImageButton) findViewById(R.id.imageButton);
        imageIV = (ImageView) findViewById(R.id.imageView2);

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageIV.setImageBitmap(photo);
        }
    }
}
