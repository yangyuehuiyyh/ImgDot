package com.punuo.sys.app.imgdot.activity;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.punuo.sys.app.imgdot.R;
import com.punuo.sys.app.imgdot.bean.Info;

import biz.zhidu.zdsdk.VideoStreamsView;
import biz.zhidu.zdsdk.ZhiduEyeAgent;

public class PlayRecordActivity extends AppCompatActivity {
    private VideoStreamsView streamsView;
    private LinearLayout view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_record);

        view = (LinearLayout)findViewById(R.id.view);
        streamsView = new VideoStreamsView(this, new Point(1920, 1080));
        view.addView(streamsView);

        Intent intent = this.getIntent();
        int position = intent.getIntExtra("record", 0);
        Info.eyeAgent.startPlayback(streamsView, Info.recordList[position], 1, false);
    }
}
