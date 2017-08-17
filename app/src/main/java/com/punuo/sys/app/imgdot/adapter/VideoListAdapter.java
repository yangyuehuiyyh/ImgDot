package com.punuo.sys.app.imgdot.adapter;

import android.app.Activity;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.punuo.sys.app.imgdot.R;
import com.punuo.sys.app.imgdot.bean.ImgSimple;
import com.punuo.sys.app.imgdot.bean.PointSimple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysx on 17/8/15.
 */

public class VideoListAdapter extends PagerAdapter {

    List<ImgSimple> imgSimples;

    List<View> views;

    Activity mContext;

    private int width;

    private ImgBrowsePagerAdapter.TagListener tagListener;

    public VideoListAdapter(Activity context, List<ImgSimple> imgSimples) {

        this.mContext = context;
        this.imgSimples = imgSimples;

        this.views = new ArrayList<>();

        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);// 获取手机屏幕宽高分辨率

        width = dm.widthPixels;

    }
    @Override
    public int getCount() {
        return imgSimples.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_browser, null);

        try {

            String imgUrl = imgSimples.get(position).url;
            float scale = imgSimples.get(position).scale;
            ArrayList<PointSimple> pointSimples = imgSimples.get(position).pointSimples;

            int height = (int) (width * scale);

        } catch (Exception e) {
            e.printStackTrace();
        }

        ((ViewPager) container).addView(view);
        return view;
    }
}
