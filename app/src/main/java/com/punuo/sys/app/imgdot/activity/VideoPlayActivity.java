package com.punuo.sys.app.imgdot.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.punuo.sys.app.imgdot.R;
import com.punuo.sys.app.imgdot.bean.Info;

import java.text.SimpleDateFormat;
import java.util.Date;

import biz.zhidu.zdsdk.RecordInfo;
import biz.zhidu.zdsdk.VideoStreamsView;
import biz.zhidu.zdsdk.ZhiduEye;
import biz.zhidu.zdsdk.ZhiduEyeAgent;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoPlayActivity extends AppCompatActivity {

    @Bind(R.id.up)
    Button up;
    @Bind(R.id.down)
    Button down;
    @Bind(R.id.left)
    Button left;
    @Bind(R.id.right)
    Button right;
    @Bind(R.id.query_recordlist)
    Button queryRecordlist;
    private ZhiduEye eye;

    //private String eqid = "340200-340200200000100005-0001-0001";

    private VideoStreamsView videoView;

    private LinearLayout surfaceLayout;

    private int width;

    private int height;

    private String TAG = "VideoPlayActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate" );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);
        surfaceLayout = (LinearLayout) findViewById(R.id.surfaceView);

        ViewTreeObserver vto = surfaceLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                surfaceLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                width = surfaceLayout.getMeasuredWidth();
                height = surfaceLayout.getMeasuredHeight();
                Log.e("VideoPlay", height + " " + width);
                videoView = eye.createVideoView(new Point(width, height));
                surfaceLayout.addView(videoView);
            }
        });

        init();
    }

    public void init() {
        eye = new ZhiduEye(this);
        Info.eyeAgent = eye.createAgent();

        System.out.println("width " + width + " height " + height);

        Info.eyeAgent.startLogin("114.215.169.7", (short) 5555, "zhidu004@zhidu.biz", "12345");

        Info.eyeAgent.SetMessageCallBack(new ZhiduEyeAgent.MessageCallBack() {
            @Override
            public void OnRecvieMessage(int msgtype, int errorcode) {
                if (msgtype == 1 || msgtype == 13 || msgtype == 18 && errorcode >= 0){
                    Log.i(TAG, "OnRecvieMessage: ");
                    Log.e(TAG, "OnRecvieMessage: " + Info.eqid);
                    Info.eyeAgent.startMonitorChannel(videoView, Info.eqid, (short) 2);
                }else if (msgtype == 1 && errorcode < 0){
                    Toast.makeText(VideoPlayActivity.this, "视频注册失败", Toast.LENGTH_SHORT);
                }
                if (msgtype == 3 && errorcode < 0){
                    Log.e(TAG, "OnRecvieMessage: "+ "设备不在线" );
                    AlertDialog dialog = new AlertDialog.Builder(VideoPlayActivity.this)
                            .setTitle("设备不在线")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    closeVideo();
                                }
                            }).create();
                    dialog.show();
                }
            }
        });
    }


    @OnClick({R.id.up, R.id.down, R.id.left, R.id.right, R.id.query_recordlist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.up:
                Info.eyeAgent.controlCamera(Info.eqid, Info.eyeAgent.CAMERA_CONTROL_ACTION_UP,(byte)0);
                break;
            case R.id.down:
                Info.eyeAgent.controlCamera(Info.eqid, Info.eyeAgent.CAMERA_CONTROL_ACTION_DOWN, (byte)0);
                break;
            case R.id.left:
                Info.eyeAgent.controlCamera(Info.eqid, Info.eyeAgent.CAMERA_CONTROL_ACTION_LEFT,(byte)0);
                break;
            case R.id.right:
                Info.eyeAgent.controlCamera(Info.eqid, Info.eyeAgent.CAMERA_CONTROL_ACTION_RIGHT, (byte)0);
                break;
            case R.id.query_recordlist:
                Info.eyeAgent.startQueryRecord(Info.eqid,false, getDate("2017/08/23 10:33:01"),getDate("2017/08/23 10:34:39"));
                Info.recordList = Info.eyeAgent.queryRecordList();
                System.out.println("----queryRecord " + Info.recordList.length);
                if (Info.recordList == null){
                    Toast.makeText(VideoPlayActivity.this, "没有录像记录", Toast.LENGTH_SHORT);
                }else {
                    Intent intent = new Intent(this,RecordPlayActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("是否结束监控？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        closeVideo();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    public void closeVideo(){
        Info.eyeAgent.stopMonitorChannel(Info.eqid);
        Info.eqid = null;
        finish();
    }

    public static long getDate(String unixDate) {
        long unixTimestamp = 0;
        try {
            Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(unixDate);
            unixTimestamp = date.getTime()/1000;
            System.out.println(unixTimestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unixTimestamp;
    }
}
