package com.punuo.sys.app.imgdot.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.punuo.sys.app.imgdot.R;
import com.punuo.sys.app.imgdot.adapter.ImgBrowsePagerAdapter;
import com.punuo.sys.app.imgdot.adapter.VideoListAdapter;
import com.punuo.sys.app.imgdot.bean.ImgSimple;
import com.punuo.sys.app.imgdot.bean.Info;
import com.punuo.sys.app.imgdot.bean.PointSimple;

import java.util.ArrayList;
import java.util.List;

public class GdomainActivity extends AppCompatActivity implements ImgBrowsePagerAdapter.PlayListener{

    private ViewPager viewPager;

    private List<ImgSimple> images;

    private ImgBrowsePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gdomain);

        viewPager = (ViewPager)findViewById(R.id.g_viewpager);
        initData();

        adapter = new ImgBrowsePagerAdapter(GdomainActivity.this, images);
        adapter.setPlayListener(this);
        viewPager.setAdapter(adapter);

    }

    public void initData(){
        images = new ArrayList<>();
        ImgSimple imgSimple1 = new ImgSimple();
        imgSimple1.scale = 1.6f;

        ArrayList<PointSimple> pointSimples = new ArrayList<>();
        PointSimple pointSimple0 = new PointSimple();
        pointSimple0.width_scale = 0.16f;
        pointSimple0.height_scale = 0.18f;

        PointSimple pointSimple1 = new PointSimple();
        pointSimple1.width_scale = 0.16f;
        pointSimple1.height_scale = 0.30f;

        PointSimple pointSimple2 = new PointSimple();
        pointSimple2.width_scale = 0.16f;
        pointSimple2.height_scale = 0.42f;
        pointSimples.add(pointSimple0);
        pointSimples.add(pointSimple1);
        pointSimples.add(pointSimple2);
        imgSimple1.pointSimples = pointSimples;

        images.add(imgSimple1);
    }

    @Override
    public void onPlayListener(int tag) {
        Intent intent = new Intent(this, VideoPlayActivity.class);
        if (tag == 0){
            Info.eqid = "340200-340200200000100005-0001-0001";
        } else if (tag == 1) {
            Info.eqid = "340200-340200200000100008-0001-0001";
        }else if (tag == 2){
            Info.eqid = "340200-340200200000100009-0001-0001";
        }
        startActivity(intent);
    }
}
