package com.punuo.sys.app.imgdot.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.punuo.sys.app.imgdot.R;
import com.punuo.sys.app.imgdot.adapter.ImgBrowsePagerAdapter;
import com.punuo.sys.app.imgdot.bean.ImgSimple;
import com.punuo.sys.app.imgdot.bean.Info;
import com.punuo.sys.app.imgdot.bean.PointSimple;
import com.punuo.sys.app.imgdot.view.ImageLayout;

import java.util.ArrayList;
import java.util.List;

public class ImageBrowseActivity extends AppCompatActivity implements ImgBrowsePagerAdapter.TagListener{

    private ViewPager viewPagerImgs;// ViewPager用于view之间的滑动

    private List<ImgSimple> imgSimples; //滑动的view

    private ImgBrowsePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_browse);

        viewPagerImgs = (ViewPager) this.findViewById(R.id.viewPagerImgs);// 背景图片
        viewPagerImgs.setOffscreenPageLimit(2);// 两个页面之间来回切换都不会重新加载

        initData();

        adapter = new ImgBrowsePagerAdapter(this, imgSimples);
        viewPagerImgs.setAdapter(adapter);
        adapter.setTagListener(this);

    }

    private void initData() {

        imgSimples = new ArrayList<>();

        ImgSimple imgSimple1 = new ImgSimple();
        imgSimple1.scale = 1.6f;

        ArrayList<PointSimple> pointSimples = new ArrayList<>();
        PointSimple pointSimple0 = new PointSimple();
        pointSimple0.width_scale = 0.40f;
        pointSimple0.height_scale = 0.20f;

        PointSimple pointSimple1 = new PointSimple();
        pointSimple1.width_scale = 0.51f;
        pointSimple1.height_scale = 0.22f;

        PointSimple pointSimple2 = new PointSimple();
        pointSimple2.width_scale = 0.32f;
        pointSimple2.height_scale = 0.33f;


        PointSimple pointSimple3 = new PointSimple();
        pointSimple3.width_scale = 0.276f;
        pointSimple3.height_scale = 0.764f;

        PointSimple pointSimple4 = new PointSimple();
        pointSimple4.width_scale = 0.638f;
        pointSimple4.height_scale = 0.74f;


        PointSimple pointSimple6 = new PointSimple();
        pointSimple6.width_scale = 0.486f;
        pointSimple6.height_scale = 0.364f;

        pointSimples.add(pointSimple0);
        pointSimples.add(pointSimple1);
        pointSimples.add(pointSimple2);
        //pointSimples.add(pointSimple3);
        //pointSimples.add(pointSimple4);

        //pointSimples.add(pointSimple6);

        imgSimple1.pointSimples = pointSimples;

        imgSimples.add(imgSimple1);
    }

    @Override
    public void onTagListener(int tag) {
        if(tag == 1) {
            Intent intent = new Intent(this, FdomainActivity.class);
            startActivity(intent);
        }else if (tag == 0){
            Intent intent = new Intent(this, GdomainActivity.class);
            startActivity(intent);
        }else if (tag == 2){
            Intent intent = new Intent(this,HdomainActivity.class);
            startActivity(intent);
        }
    }
}
