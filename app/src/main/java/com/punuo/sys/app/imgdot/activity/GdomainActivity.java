package com.punuo.sys.app.imgdot.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.punuo.sys.app.imgdot.R;
import com.punuo.sys.app.imgdot.adapter.ImgBrowsePagerAdapter;
import com.punuo.sys.app.imgdot.adapter.VideoListAdapter;
import com.punuo.sys.app.imgdot.bean.ImgSimple;
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

        pointSimples.add(pointSimple0);
        imgSimple1.pointSimples = pointSimples;

        images.add(imgSimple1);
    }

    @Override
    public void onPlayListener(int tag) {
        Intent intent = new Intent(this, VideoPlayActivity.class);
        startActivity(intent);
    }
}
