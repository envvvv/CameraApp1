package com.example.cameraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private VideoView videoView;
    private Button button;
    //执行拍照
    private void dispatch(){
        Intent takePicture=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(takePicture.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePicture,0);
        }
    }

    //获取缩略图
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Uri videoUri= getIntent().getData();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //imageView.setImageBitmap();
            videoView.setVideoURI(videoUri);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // imageView=findViewById(R.id.Photo);
        videoView=findViewById(R.id.Photo);
        button=findViewById(R.id.takePhoto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatch();
            }
        });
    }



}