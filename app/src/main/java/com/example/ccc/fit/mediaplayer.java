package com.example.ccc.fit;

import android.drm.DrmErrorEvent;
import android.drm.DrmInfoEvent;
import android.drm.DrmManagerClient;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;

public class mediaplayer extends AppCompatActivity
{
    private VideoView video;
    private EditText et;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);
        video = (VideoView) findViewById(R.id.video);
        /*
        String path=Environment.getExternalStorageDirectory().getAbsolutePath() +"/big_buck_bunny.mp4";
        video.setVideoPath(path);
        video.start();
        */
        Uri uri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        video.setVideoURI(uri);
        MediaController controller=new MediaController(this);
        video.setMediaController(controller);
        video.start();
        //initView();
    }
        /*
    private void initView() {
        et = (EditText) findViewById(R.id.et1);
        video = (VideoView) findViewById(R.id.video);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String path = "https://www.youtube.com/watch?v=7--cE7_PtXk";//获取视频路径
                //http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4
                //Uri uri = Uri.parse(Environment.getExternalStorageDirectory() +"/big_buck_bunny.mp4");//将路径转换成uri
                // Uri uri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
                本地视频播放


                String path=Environment.getExternalStorageDirectory().getAbsolutePath() +"/big_buck_bunny.mp4";
                video.setVideoPath(path);
                MediaController controller=new MediaController(this);

                //video.setVideoURI(uri);//为视频播放器设置视频路径
               // video.start();

                video.setMediaController(new MediaController(mediaplayer.this));//显示控制栏
                video.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                    @Override
                    public void onPrepared(MediaPlayer mp)
                    {
                        video.start();//开始播放视频
                    }
                });

            }
        });
    }
    */
}
