package com.vst.player;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.letv.pp.service.CdeService;
import com.mylove.mvideo.R;

public class MainActivity extends AppCompatActivity {
    VideoView mVideo;
    CdeService cde;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cde = CdeService.get();
        mVideo = (VideoView) findViewById(R.id.video);
        String url = cde.get_HostUrl("http://live.gslb.letv.com/gslb?stream_id=lb_dengchao_1800&tag=live&ext=m3u8");
        mVideo.setVideoPath(url);
        mVideo.start();
    }
}
